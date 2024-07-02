package factory.model;

public class IdController {
    public static volatile int partId;
    public static volatile int carId;
    public static synchronized int getPartId() {
        return partId++;
    }
    public static synchronized int getCarId() {
        return carId++;
    }

}
