package api;
/*

 */
import java.util.Comparator;

/**
    * Интефейс List предназначен для создания собсветнной упорядоченной коллекции.<p>
    * Интерфейс контролирует нахождении каждого элемента.<p>
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
    * @param <T> the type of elements in this list
 */
public interface IList<T> {
    /**
     * Добавление элемента в конец списка
     * @param item - елемент который должен быть добавлен в конец листа;
     * @throws IllegalArgumentException - при превишении максимального значения листа
     */
    void add(T item);

    /**
     * Добавление элемента в середину списка, при необходимости размер списка увеличивается в двое,
     * но не болле чем максимальный размен листа;
     * @param index - индекс элементана место которого необходимо добавить элемент;
     * @param item - елемент который должен быть добавлен в конец листа;
     * @throws IllegalArgumentException - при превишении максимального значения листа
     * @throws IndexOutOfBoundsException - усли индекс элемента выходит за пределы листа
     */
    void add(int index, T item);


    T get(int index);

    /**
     * Удаление эдемента по индексу элемента
     * @param index - инжекс элемента который необходимо удалить.
     * @throws IndexOutOfBoundsException - усли индекс элемента выходит за пределы листа
     */
    void remove(int index);

    /**
     * Очистка всей коллекции, удаляет все элементы
     */
    void clear();

    /**
     * Получение колличетво элементов хранящихся в листе
     * @return возращает колличество элементов хранящихся в листе
     */
    int size();

    /**
     * сортирует лист
     * @param comparator {@code Comparator} использует для сравнения элементов
     */
    void sort(Comparator<T> comparator);

    /**
     * Устанавливает максимальное колличество элементов которые могут добавлены в лист
     * @param maxSizeArray - максимальный размер массива, до которого может увеличится коллекция
     * @throws IllegalArgumentException - если максимальное значение быдем меньше 0
     */
    void setMaxSizeArray(int maxSizeArray);

    /**
     * Получение размера до которого может быть раширен массив
     * @return возвращает размер массива до которого может быть раширен массив
     */
    int getMaxSizeArray();
}