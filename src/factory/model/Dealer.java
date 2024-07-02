package factory.model;

import factory.model.parts.CarPart;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Dealer implements Runnable {
    private final int sleepTime;
    Logger LOGGER = Logger.getLogger(Dealer.class.getName());
    private final Warehouse<Car> warehouse;
    private final boolean doLogSale;
    private final DealersPool pool;
    private final CarWarehouseController controller;

    public Dealer(Warehouse<Car> warehouse, int sleepTime,
                  DealersPool pool,boolean doLogSale,
                  CarWarehouseController controller) {
        this.controller = controller;
        this.doLogSale = doLogSale;
        this.sleepTime = sleepTime;
        this.warehouse = warehouse;
        this.pool = pool;
    }

    public void sellCar() {
        Car car = warehouse.get();
        controller.WarehouseChanged();
        if (doLogSale)
            logCarInfo(car);
        pool.carSold();
    }

    private void logCarInfo(Car car) {
        StringBuilder resString = new StringBuilder();
        resString
                .append(java.time.LocalDateTime.now())
                .append(": Dealer ")
                .append(Thread.currentThread().getName())
                .append(": Auto ")
                .append(car.getId())
                .append(" (");

        for (CarPart product : car.getComponents()) {
            resString
                .append(product.getClass().getSimpleName())
                .append(": ")
                .append(product.getId())
                .append(", ");
        }
        resString.delete(resString.length()-2,resString.length());
        resString.append(")");
        LOGGER.log(Level.INFO, resString.toString());
    }

    @Override
    public void run() {
        sellCar();
        try { Thread.sleep(sleepTime); }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
