import java.io.Serializable;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Set<Integer>mainSet = new ArraySet<>() {{
            add(1);
            add(2);
            add(null);
        }};

        System.out.println(mainSet.contains(null));
        System.out.println(mainSet.size());
    }


    public static class ArraySet<E> extends AbstractSet<E> implements Set<E>, Cloneable, Serializable {

        private transient E[] mass;

        private int count = 0;

        private boolean icContainsNull = false;

        public ArraySet() {
            mass = (E[])(new Object[100]);
        }

        @Override
        public boolean add(E e) {
            count++;
            if (e == null) {
                icContainsNull = true;
            }

            for (int i = 0; i < this.mass.length; i++) {
                if (mass[i] == null) {
                    mass [i] = e;
                    break;
                }
            }
            return true;
        }

        @Override
        public Iterator<E> iterator() {
            return getIterator(mass);
        }

        @Override
        public int size() {
//            int i = 0;
//            for (E t : mass) {
//                if (t == null) {
//                    return i;
//                }
//                i++;
//            }
            return this.count;
        }

        @Override
        public boolean contains(Object o) {
            if (o == null && icContainsNull == true) {
                return true;
            }
            if (o == null && icContainsNull == false) {
                return false;
            }
            Iterator<E> it = iterator();
            if (o==null) {
                while (it.hasNext())
                    if (it.next()==null)
                        return true;
            } else {
                while (it.hasNext())
                    if (o.equals(it.next()))
                        return true;
            }
            return false;
        }
    }


    private static <E> Iterator<E> getIterator(final E[] array) {
        return new Iterator<E>() {
            private int count = array.length;
            private int index = 0;
            @Override
            public boolean hasNext() {
                return index < count;
            }

            @Override
            public E next() {
                if (index < count) {
                    return array[index++];
                } else {
                    throw new NoSuchElementException("No such element.");
                }
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Cannot remove item from array.");
            }
        };
    }
}
