import api.IList;
import core.ListObjects.TestObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Comparator;

public class TestArray {

//Проверка граниченого добавления в лист
    @Test
    public void addElements(){
        NewArrayList<Long> list = new NewArrayList<>();
        for(int i = 0;i<67108864;i++){
            list.add(Long.reverse(i));
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(),67108864);
        list.clear();
    }

//
    @Test
    public void addElementsMaxException(){
        IList<String> list = new NewArrayList<>();
        for(int i = 0; i<67108864;i++){
            list.add(String.valueOf(i));
        }
        Assertions.assertThrows(IllegalArgumentException.class, () -> list.add("1"));
    }

    @Test
    public void addElementWithIndex(){
        IList<TestObject> list = new NewArrayList<>();
        for(int i = 0; i<1000;i++){
            list.add(i,new TestObject(i,String.valueOf(i)));
        }
        for(int i = 0;i<1000;i++){
            Assert.assertEquals(list.get(i),new TestObject(i,String.valueOf(i)));
        }
        Assert.assertNotNull(list);
        Assert.assertEquals(list.size(),1000);
    }

    @Test
    public void addElementInCenter(){
        IList<Integer> list = new NewArrayList<>();
        for(int i = 0;i<1000;i++){
            list.add(i);
        }
        list.add(345,-1);
        Assert.assertEquals(list.size(),1001);
        for(int i = 0;i<1001;i++){
            if(i<345) Assert.assertEquals(list.get(i),Integer.valueOf(i));
            if(i==345) Assert.assertEquals(list.get(i),Integer.valueOf(-1));
            if(i>345) Assert.assertEquals(list.get(i),Integer.valueOf(i-1));
        }
    }

    @Test
    public void get(){
        IList<TestObject> list = new NewArrayList<>();
        TestObject object = new TestObject(521,"test");
        for(int i = 0;i<1000;i++){
            if(i == 521)
                list.add(object);
                else list.add(new TestObject(i,String.valueOf(i)+String.valueOf(i)));
        }
        Assert.assertEquals(object,list.get(521));
    }

    @Test
    public void getException(){
        IList<TestObject> list = new NewArrayList<>();
        for(int i = 0;i<1000;i++){
                list.add(new TestObject(i,String.valueOf(i)));
        }
        Assertions.assertThrows(IndexOutOfBoundsException.class,()->list.get(1000));
        Assertions.assertThrows(IndexOutOfBoundsException.class,()->list.get(-1));
    }

    @Test
    public void remove(){
        IList<TestObject> list = new NewArrayList<>();
        for(int i = 0;i<1000;i++){
            list.add(new TestObject(i,String.valueOf(i)));
        }
        list.remove(222);
        Assert.assertEquals(list.size(),999);
        for(int i = 0;i<999;i++){
            if(i<222){
                Assert.assertEquals(list.get(i),new TestObject(i,String.valueOf(i)));
            } else {
                Assert.assertEquals(list.get(i),new TestObject(i+1,String.valueOf(i+1)));
            }
        }
    }

    @Test
    public void clear(){
        IList<Integer> list = new NewArrayList<>();
        for(int i = 0;i<1000;i++){
            list.add(i);
        }
        list.clear();
        Assert.assertEquals(list.size(),0);
        for(int i = 0;i<10;i++){
            list.add(i);
        }
        for(int i = 0;i<10;i++){
            Assert.assertEquals(Integer.valueOf(i),list.get(i));
        }
    }

    @Test
    public void sortComparator(){
        IList<Double> list = new NewArrayList<>();
        for(int i = 0;i<10000;i++){
            list.add(Math.random()*100000);
        }
        list.sort(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                if(o1>o2)
                    return -1;
                if(o1<o2)
                    return 1;
                return 0;
            }
        });
        boolean sort = true;
        for(int i = 1;i<10000;i++){
            if(list.get(i-1)>list.get(i)){
                sort = false;
                break;
            }
        }
        Assert.assertTrue(sort);
    }

    @Test
    public void setMaxSizeArray(){
        IList<Integer> list = new NewArrayList<>();
        list.setMaxSizeArray(10);
        for(int i = 0;i<10;i++){
            list.add(i);
        }
        Assertions.assertThrows(IllegalArgumentException.class,()->list.add(20));
    }
}
