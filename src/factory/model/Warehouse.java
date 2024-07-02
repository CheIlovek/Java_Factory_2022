package factory.model;

import java.util.LinkedList;

public class Warehouse<T> {
    public final int maxSize;
    private final LinkedList<T> storage;

    public Warehouse(int maxSize) {

        this.maxSize = maxSize;
        storage = new LinkedList<>();
    }

    public synchronized T get() {
        while (storage.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        T obj = storage.pop();
        notify();

        return obj;
    }

    public synchronized void put(T obj) {
        while (storage.size() >= maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        storage.add(obj);
        notify();
    }

    public synchronized int getSize() {
        return storage.size();
    }

    public synchronized double getFullnessPercentage() {
        return (double) storage.size() / maxSize;
    }
}
