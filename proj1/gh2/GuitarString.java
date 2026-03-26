package gh2;

import deque.ArrayDeque;

public class GuitarString {
    private static final int SR = 44100;
    private static final double DECAY = .996;

    private ArrayDeque<Double> buffer;

    public GuitarString(double frequency) {
        buffer = new ArrayDeque<>();
        int capacity = (int) Math.round(SR / frequency);
        for (int i = 0; i < capacity; i++) {
            buffer.addLast((double) 0);
        }
    }

    public void pluck() {
        int size = buffer.size();
        for (int i = 0; i < size; i++) {
            Double d = Math.random() - 0.5;
            buffer.addLast(d);
            buffer.removeFirst();
        }
    }

    public void tic() {
        if (buffer.isEmpty()) {
            return;
        }
        if (buffer.size() < 2) {
            return;
        }
        Double head = buffer.get(0);
        buffer.removeFirst();
        Double newHead = buffer.get(0);
        Double tail = (head + newHead) / 2 * DECAY;
        buffer.addLast(tail);
    }

    public double sample() {
        return buffer.get(0);
    }

}
