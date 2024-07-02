package factory.model;

public class CarWarehouseController {
    private final Warehouse<Car> warehouse;
    private final CarFactory factory;
    private final int dealersCount;

    public CarWarehouseController(Warehouse<Car> warehouse,CarFactory factory,int dealersCount) {
        this.factory = factory;
        this.warehouse = warehouse;
        this.dealersCount = dealersCount;
    }

    public void WarehouseChanged() {
        if (!factory.doHaveTasks() && warehouse.getSize() < dealersCount) {
            factory.makeCars((dealersCount * 2) - warehouse.getSize());
        }
    }
}
