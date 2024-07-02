package factory.model.suppliers;

import factory.model.IdController;
import factory.model.Warehouse;
import factory.model.parts.Engine;
import factory.model.parts.CarPart;

public class EngineSupplier extends Supplier {

    public EngineSupplier(Warehouse<CarPart> warehouse, int sleepTime) {
        super(warehouse, sleepTime);
    }

    public CarPart createProduct() {
        int id = IdController.getPartId();
        return new Engine(id);
    }
}

