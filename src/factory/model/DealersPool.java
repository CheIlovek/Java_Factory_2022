package factory.model;


public class DealersPool implements Runnable {
    private final MyThreadPool workersPool;
    private int timeToSellCar;
    private final Warehouse<Car> carWarehouse;
    private final boolean doLogSale;
    private int countSoldCars;
    private final CarWarehouseController controller;

    public DealersPool(int workers, int timeToSellCar,
                       Warehouse<Car> carWarehouse, boolean doLogSale,
                       CarWarehouseController controller) {
        this.controller = controller;
        this.doLogSale = doLogSale;
        this.carWarehouse = carWarehouse;
        this.timeToSellCar = timeToSellCar;
        workersPool = new MyThreadPool(workers,"Dealer");
    }

    private void createTasks() {
        int len = workersPool.getPoolSize();
        for (int i = 0; i < len; i++) {
            workersPool.execute(new Dealer(carWarehouse,timeToSellCar,this,doLogSale,controller));
        }
    }
    public synchronized void carSold() {
        countSoldCars++;
    }

    public int getSoldCarsCount() {
        return countSoldCars;
    }

    public void setSleepTime(int timeToMakeAccessory) {
        this.timeToSellCar = timeToMakeAccessory;
    }


    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted())
            if (workersPool.getTasksLen() / workersPool.getPoolSize() < 1)
                createTasks();
            else
                Thread.yield();
    }
}