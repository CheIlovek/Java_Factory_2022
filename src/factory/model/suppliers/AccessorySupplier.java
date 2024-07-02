package factory.model.suppliers;

import factory.model.IdController;
import factory.model.Warehouse;
import factory.model.parts.Accessory;
import factory.model.parts.CarPart;

public class AccessorySupplier extends Supplier{
    public AccessorySupplier(Warehouse<CarPart> warehouse, int sleepTime) {
        super(warehouse, sleepTime);
    }

    @Override
    public CarPart createProduct() {
        int id = IdController.getPartId();
        return new Accessory(id);
    }

    @Override
    public void run() {
        supply();
    }
}
