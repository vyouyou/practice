package effectivejava;

import lombok.Getter;
import lombok.extern.java.Log;

import java.math.BigDecimal;
import java.util.*;

/**
 * 复合优先于继承
 */
@Log
public class cp16 {
    public static void main(String[] args) {
        InstrumentedHashSet<Integer> integers = new InstrumentedHashSet<>();
        integers.addAll(Arrays.asList(1, 2, 3));
        log.info(integers.getAddCount() + "");
    }

    public static class InstrumentedHashSet<E> extends HashSet<E> {
        @Getter
        private int addCount = 0;

        public InstrumentedHashSet() {

        }

        /**
         * 由于它重写了add方法
         * 而在 {@link java.util.AbstractCollection} 的 addall方法中
         * 调用了抽象方法add，所以，addall的过程中，add也被调用
         *
         * @param e
         * @return
         */
        @Override
        public boolean add(E e) {
            addCount++;
            return super.add(e);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            addCount += c.size();
            return super.addAll(c);
        }
    }


    public static class InstrumentedSet<E> extends ForwardingSet<E>{
        @Getter
        private int addCount = 0;

        public InstrumentedSet(Set s){
            super(s);
        }

        @Override
        public boolean add(E e) {
            addCount++;
            return super.add(e);
        }

        /**
         * 由于它调用了转发类的 addAll方法
         * 所以不会调用到抽象方法产生副作用
         * addAll不会再调用到add
         * @param c
         * @return
         */
        @Override
        public boolean addAll(Collection<? extends E> c) {
            addCount+=c.size();
            return super.addAll(c);
        }
    }

    /**
     * 转发类实现接口类所有方法，
     * 但是他只是转发set实现类的方法，
     * 如 hashset的方法
     * @param <E>
     */
    public static class ForwardingSet<E> implements Set<E> {
        private final Set<E> s;

        public ForwardingSet(Set s) {
            this.s = s;
        }


        @Override
        public int size() {
            return s.size();
        }

        @Override
        public boolean isEmpty() {
            return s.isEmpty();
        }

        @Override
        public boolean contains(Object o) {
            return s.contains(o);
        }

        @Override
        public Iterator<E> iterator() {
            return s.iterator();
        }

        @Override
        public Object[] toArray() {
            return s.toArray();
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return s.toArray(a);
        }

        @Override
        public boolean add(E e) {
            return s.add(e);
        }

        @Override
        public boolean remove(Object o) {
            return s.remove(o);
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return s.containsAll(c);
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return s.addAll(c);
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return s.containsAll(c);
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return s.removeAll(c);
        }

        @Override
        public void clear() {
            s.clear();
        }
    }
}
