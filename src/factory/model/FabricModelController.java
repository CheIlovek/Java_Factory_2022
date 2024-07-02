package factory.model;

import factory.model.suppliers.BodySupplier;
import factory.model.suppliers.EngineSupplier;
import factory.model.suppliers.Supplier;
import factory.model.parts.*;

import java.util.Properties;

public class FabricModelController {
    public final static int START_TIME_TO_CREATE = 5000;
    private Warehouse<CarPart> bodyWarehouse;
    private Warehouse<CarPart> engineWarehouse;
    private Warehouse<CarPart> accessoryWarehouse;
    private Warehouse<Car> carWarehouse;
    private Supplier bodySupplier;
    private Supplier engineSupplier;
    private AccessorySuppliersPool accessorySuppliersPool;
    private DealersPool dealersPool;
    private CarFactory carFactory;


    public void initFabric(Properties properties) {
        bodyWarehouse = new Warehouse<>(Integer.parseInt(properties.getProperty("StorageBodySize")));
        engineWarehouse = new Warehouse<>(Integer.parseInt(properties.getProperty("StorageEngineSize")));
        accessoryWarehouse = new Warehouse<>(Integer.parseInt(properties.getProperty("StorageAccessorySize")));
        carWarehouse = new Warehouse<>(Integer.parseInt(properties.getProperty("StorageCarSize")));

        bodySupplier = new BodySupplier(bodyWarehouse,START_TIME_TO_CREATE);
        engineSupplier = new EngineSupplier(engineWarehouse,START_TIME_TO_CREATE);
        accessorySuppliersPool = new AccessorySuppliersPool(
                Integer.parseInt(properties.getProperty("AccessorySuppliers")),
                START_TIME_TO_CREATE, accessoryWarehouse);

        carFactory = new CarFactory(
                Integer.parseInt(properties.getProperty("Workers")),START_TIME_TO_CREATE,
                carWarehouse,bodyWarehouse, engineWarehouse, accessoryWarehouse);

        CarWarehouseController controller = new CarWarehouseController(
                carWarehouse,carFactory,Integer.parseInt(properties.getProperty("Dealers")));

        dealersPool = new DealersPool(
                Integer.parseInt(properties.getProperty("Dealers")),
                START_TIME_TO_CREATE, carWarehouse,
                Boolean.parseBoolean(properties.getProperty("LogSale")),controller);



        Thread thread;

        thread = new Thread(bodySupplier,"BODY SUPPLIER");
        thread.setDaemon(true);thread.start();

        thread = new Thread(engineSupplier, "ENGINE SUPPLIER");
        thread.setDaemon(true);thread.start();

        thread = new Thread(dealersPool, "DEALERS SUPPLIER");
        thread.setDaemon(true);thread.start();

        thread = new Thread(accessorySuppliersPool, "ACCESSORY SUPPLIER");
        thread.setDaemon(true);thread.start();

        controller.WarehouseChanged();
        //thread = new Thread(controller, "CAR FACTORY CONTROLLER");
        //thread.setDaemon(true);thread.start();

    }

    public void setBodyCreationTime(int time) {
        bodySupplier.setSleepTime(time);
    }

    public void setEngineCreationTime(int time) {
        engineSupplier.setSleepTime(time);
    }

    public void setAccessoryCreationTime(int time) {
        accessorySuppliersPool.setSleepTime(time);
    }

    public void setCarAssemblingTime(int time) {
        carFactory.setSleepTime(time);
    }

    public void setDealerSellTime(int time) {
        dealersPool.setSleepTime(time);
    }

    public int getEngineWarehouseMaxSize() {
        return engineWarehouse.maxSize;
    }
    public int getBodyWarehouseMaxSize() {
        return bodyWarehouse.maxSize;
    }
    public int getCarWarehouseMaxSize() {
        return carWarehouse.maxSize;
    }
    public int getAccessoryWarehouseMaxSize() {
        return accessoryWarehouse.maxSize;
    }

    public int getEngineWarehouseSize() {
        return engineWarehouse.getSize();
    }
    public int getBodyWarehouseSize() {
        return bodyWarehouse.getSize();
    }
    public int getAccessoryWarehouseSize() {
        return accessoryWarehouse.getSize();
    }
    public int getCarWarehouseSize() {
        return carWarehouse.getSize();
    }

    public int getCountSoldCars() {
        return dealersPool.getSoldCarsCount();
    }
}