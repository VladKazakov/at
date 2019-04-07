import java.io.Serializable;
import java.util.AbstractSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Set<Integer> mainSet = new ArraySet<>(){{
            add(1);
            add(2);
        }};

        System.out.println(mainSet.contains(2));
    }


    public static class ArraySet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {

        private transient HashMap<E,Object> map;

        // Dummy value to associate with an Object in the backing Map
        private static final Object PRESENT = new Object();

        public ArraySet() {
            map = new HashMap<>();
        }

        @Override
        public boolean add(E e) {
            return map.put(e, PRESENT)==null;
        }

        @Override
        public Iterator<E> iterator() {
            return map.keySet().iterator();
        }

        @Override
        public int size() {
            return map.size();
        }
    }
}
