package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        int index = 0;
        for (int i = 0; i < size(); i++) {
            if (comparator.compare(get(index), get(i)) < 0) {
                index = i;
            }
        }
        return get(index);
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        int index = 0;
        for (int i = 0; i < size(); i++) {
            if (c.compare(get(index), get(i)) < 0) {
                index = i;
            }
        }
        return get(index);
    }

}
