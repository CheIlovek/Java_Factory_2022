package factory.model;

import factory.model.parts.CarPart;

public class Car {
    private final CarPart[] components;
    private final int id;
    public Car(int id, CarPart[] components) {
        this.id = id;
        this.components = components;
    }

    public CarPart[] getComponents() {
        return components;
    }

    public int getId() {
        return id;
    }
}
