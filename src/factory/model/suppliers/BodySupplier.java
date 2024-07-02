package factory.model.suppliers;

import factory.model.IdController;
import factory.model.Warehouse;
import factory.model.parts.Body;
import factory.model.parts.CarPart;

public class BodySupplier extends Supplier {

    public BodySupplier(Warehouse<CarPart> warehouse, int sleepTime) {
        super(warehouse, sleepTime);
    }

    public CarPart createProduct() {
        int id = IdController.getPartId();
        return new Body(id);
    }
}
