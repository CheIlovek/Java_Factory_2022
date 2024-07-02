package factory.model.parts;

public abstract class CarPart {
    private final int id;

    protected CarPart(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
