package factory.model.suppliers;

import factory.model.Warehouse;
import factory.model.parts.CarPart;

public abstract class Supplier implements Runnable {
    private int sleepTime;
    private final Warehouse<CarPart> warehouse;

    protected Supplier(Warehouse<CarPart> warehouse, int sleepTime) {
        this.sleepTime = sleepTime;
        this.warehouse = warehouse;
    }

    public void setSleepTime(int val) {
        sleepTime = val;
    }

    public abstract CarPart createProduct();


    public void supply() {
        CarPart carPart = createProduct();
        warehouse.put(carPart);
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            supply();
        }
    }
}
