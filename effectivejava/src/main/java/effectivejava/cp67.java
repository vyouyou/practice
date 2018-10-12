package effectivejava;

import java.util.*;

/**
 * @author qisy01
 * @create 18-10-11
 * @since 1.0.0
 */
public class cp67 {
    public interface SetObserver<E> {
        void added(ObservableSet<E> set, E element);
    }

    public static class ObservableSet<E> extends cp16.ForwardingSet<E> {

        public ObservableSet(Set s) {
            super(s);
        }

        private final List<SetObserver<E>> observers
                = new ArrayList<>();

        public void addObserver(SetObserver<E> observer) {
            synchronized (observers) {
                observers.add(observer);
            }
        }

        public boolean removeObserver(SetObserver<E> observer) {
            synchronized (observers) {
                return observers.remove(observer);
            }
        }

        private void notifyElementAdded(E element) {
            synchronized (observers) {
                for (SetObserver<E> observer : observers) {
                    observer.added(this, element);
                }
            }
        }

        @Override
        public boolean add(E e) {
            boolean added = super.add(e);
            if (added) {
                notifyElementAdded(e);
            }
            return added;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            boolean result = false;
            for (E e : c) {
                result |= add(e);
            }
            return result;
        }
    }

    public static void main(String[] args) {
        ObservableSet<Integer> set
                = new ObservableSet<>(new HashSet<Integer>());
        set.addObserver(new SetObserver<Integer>() {
            @Override
            public void added(ObservableSet<Integer> set, Integer element) {
                System.out.println(element);
                if (element == 23) {
                    set.removeObserver(this);
                }
            }
        });

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }
}
