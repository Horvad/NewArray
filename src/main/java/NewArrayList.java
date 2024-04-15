import api.IList;

import java.util.Comparator;

/**
 * Реализация интерфеса {@code IList} с расширяемым размером массива
 * Реализация не синхронизирована
 * Пользователь может получить доступ к элементу по индексу и искать элементы в списке.<p>
 * Допускается дублирование элементов.<p>
 * Допускается элементы равные null.<p>
 * Интерфейс предтсявляет меотды:
 * добавление в конец списка,
 * добавление в середину,
 * получение элемента по индексу
 * удаление элемента по индексу
 * очистка всего листа
 * получение колличество элементов листа
 * сортировка элементов с использованием интерфейса Comparator
 * установка максимального колличества элементов, которые может принять в себя коллекция
 * получение максимального колличества элементов, которые может принять в себя коллекция
 * @param <T> - тип элементов в списке
 */
public class NewArrayList<T> implements IList<T>{

    /**
     * Начальные размер массива
     */
    private int sizeArray;

    /**
     * Элементы массива
     */
    private Object[] data = {};

    /**
     * Текущей размер массива
     */
    private int size;

    /**
     * Размер до которого может быть расширен массив
     */
    private int maxSizeArray = 67108864;

    public NewArrayList() {
        this.sizeArray = 2;
        this.size = 0;
    }

    /**
     * Добавляет элемент в конец листа
     * @param item - елемент который должен быть добавлен в конец листа;
     */
    @Override
    public void add(T item) {
        if(size+1>sizeArray){
            increaseArray();
        }
        data[size] = item;
        size++;
    }

    /**
     * Добавление элемента в середину листа
     * @param index - индекс элементана место которого необходимо добавить элемент;
     * @param item - елемент который должен быть добавлен в конец листа;
     * @throws IndexOutOfBoundsException - при индексо меньше 0 или больше текущего размера
     */
    @Override
    public void add(int index, T item) {
        if(index>size||index<0){
            throw new IndexOutOfBoundsException("Элемент выходит за пределы массива");
        }
        if(size+1==sizeArray){
            increaseArray();
        }
        if(index!=size){
            shiftRight(index);
            size++;
            data[index] = item;
        } else {
            add(item);
        }
    }

    /**
     * Полечение элемента по индексу
     * @param index - эндекс необходимого элемента
     * @return вэлемент массива
     * @throws IndexOutOfBoundsException - при введении несуществующего индекса
     */
    @Override
    public T get(int index) {
        if(index>=size||index<0){
            throw new IndexOutOfBoundsException("Такого индекса не существует");
        }
            return (T) data[index];
    }

    /**
     * Удаление элемента по индексу. Если колличество элементов меньше в два раза чем размен массива,
     * то размер массива уменьшается вдое
     * @param index - инжекс элемента который необходимо удалить.
     */
    @Override
    public void remove(int index) {
        if(index>=size||index<0){
            throw new IllegalArgumentException("Такого индекса не существует");
        }
        shiftLeft(index);
        if(size*2<sizeArray){
            decreasingArray();
        }
        size--;
    }

    /**
     * Очистка массива, удаление всех элементов
     */
    @Override
    public void clear() {
        size = 0;
        sizeArray = 16;
        data = new Object[16];
    }

    @Override
    public int size() {
        return this.size;
    }

    /**
     * Реализует QuickSort
     * @param comparator {@code Comparator} использует для сравнения элементов
     * @throws IllegalArgumentException - при нулевом компараторе
     */
    @Override
    public void sort(Comparator<T> comparator){
        if(comparator==null){
            throw new IllegalArgumentException("Не возможно остсортировать лист");
        }
        Object[] arr = new Object[sizeArray];
        System.arraycopy(data,0,arr,0,size);
        quickSort(arr,0,size-1,comparator);
        data = arr;
    }

    /**
     * Устанавливает максимальное колличество элементов которые могут добавлены в лист
     * @param maxSizeArray - максимальный размер массива, до которого может увеличится коллекция
     * @throws IllegalArgumentException - если максимальное значение быдем меньше 0
    */
    @Override
    public void setMaxSizeArray(int maxSizeArray) {
        if(maxSizeArray<0){
            throw new IllegalArgumentException("Максимальное колличесов элементов не может быть меньше 0");
        }
        this.maxSizeArray = maxSizeArray;
    }

    /**
     * Получение размера до которого может быть раширен массив
     * @return возвращает размер массива до которого может быть раширен массив
     */
    @Override
    public int getMaxSizeArray() {
        return maxSizeArray;
    }

    public void quickSort(Object[] arr, int low, int high, Comparator<T> comparator){
        if(low<high){
            int pi = partition(arr,low,high, comparator);
            quickSort(arr,low,pi-1, comparator);
            quickSort(arr,pi+1,high, comparator);
        }
    }

    private int partition(Object[] arr, int low, int high, Comparator<T> comparator){
        Object pivot = arr[high];
        int i = low-1;
        for(int j = low;j<high;j++){
            if(comparator.compare((T)arr[j],(T)pivot)>0){
                i++;
                Object temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Object temp = arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }
    private void increaseArray(){
        if(maxSizeArray<sizeArray*2&&size==maxSizeArray){
            throw new IllegalArgumentException("Лист расширяется больше заданного значения");
        } else {
            if(maxSizeArray<sizeArray*2){
                sizeArray=maxSizeArray;
            } else {
                sizeArray = sizeArray*2;
            }
        }
        Object[] arr = new Object[sizeArray];
        System.arraycopy(data,0,arr,0,size);
        data = arr;
    }

    private void decreasingArray(){
        Object[] arr = new Object[sizeArray/2];
        System.arraycopy(data,0,arr,0,size);
        sizeArray = sizeArray/2;
    }

    private void shiftRight(int index){
        Object[] arr = new Object[size+1];
        System.arraycopy(data,0,arr,0,size);
        for(int i = size;i>index;i--){
            arr[i] = arr[i-1];
        }
        data = arr;
    }

    private void shiftLeft(int index){
        Object[] arr = new Object[size];
        System.arraycopy(data,0,arr,0,size);
        for(int i = index;i<size-1;i++){
            arr[i] = arr[i+1];
        }
        data = arr;
    }
}
