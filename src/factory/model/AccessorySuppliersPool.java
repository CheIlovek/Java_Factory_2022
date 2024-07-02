package factory.model;

import factory.model.parts.CarPart;
import factory.model.suppliers.AccessorySupplier;

public class AccessorySuppliersPool implements Runnable {
    private final MyThreadPool workersPool;
    private final Warehouse<CarPart> accessoryWarehouse;
    private int timeToMakeAccessory;


    public AccessorySuppliersPool(int workers, int timeToMakeAccessory, Warehouse<CarPart> accessoryWarehouse) {
        this.accessoryWarehouse = accessoryWarehouse;
        this.timeToMakeAccessory = timeToMakeAccessory;
        workersPool = new MyThreadPool(workers,"Accessory");
    }

    private void makeAccessory() {
        int len = workersPool.getPoolSize();
        for (int i = 0; i < len; i++) {
            workersPool.execute(new AccessorySupplier(accessoryWarehouse,timeToMakeAccessory));
        }
    }

    public void setSleepTime(int timeToMakeAccessory) {
        this.timeToMakeAccessory = timeToMakeAccessory;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted())
            if (workersPool.getTasksLen() / workersPool.getPoolSize() < 1)
                makeAccessory();
            else
                Thread.yield();
    }

}
