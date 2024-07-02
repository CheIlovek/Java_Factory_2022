package factory.model;

import factory.model.parts.CarPart;

public class CarFactory {
    private final MyThreadPool workersPool;
    private final Warehouse<CarPart>[] productWarehouses;
    private final Warehouse<Car> carWarehouse;
    private int timeToMakeCar;


    public CarFactory(int workers, int timeToMakeCar, Warehouse<Car> carWarehouse, Warehouse<CarPart>... productWarehouses) {
        this.productWarehouses = productWarehouses;
        this.carWarehouse = carWarehouse;
        this.timeToMakeCar = timeToMakeCar;
        workersPool = new MyThreadPool(workers,"FactoryWorker");
    }

    public boolean doHaveTasks() {
        return workersPool.doHaveTasks();
    }

    public void makeCars(int tasks) {
        for (int i = 0; i < tasks; i++) {
            workersPool.execute(new FactoryWorker(timeToMakeCar));
        }
    }

    public void setSleepTime(int timeToMakeCar) {
        this.timeToMakeCar = timeToMakeCar;
    }



    public class FactoryWorker implements Runnable {
        private final int sleepTime;

        public FactoryWorker(int time) {
            sleepTime = time;
        }

        @Override
        public void run() {
            CarPart[] parts = new CarPart[productWarehouses.length];
            for (int i = 0; i < productWarehouses.length; i++)
                parts[i] = productWarehouses[i].get();
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Car resCar = new Car(IdController.getCarId(),parts);
            carWarehouse.put(resCar);
        }
    }
}
