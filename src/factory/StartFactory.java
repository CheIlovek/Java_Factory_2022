package factory;

import factory.model.FabricModelController;
import factory.ui.FactoryUI;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;


public class StartFactory {
    private final static String PROPERTIES_FILE = "factory.properties";

    public static void main(String[] args) {
        Properties properties = getProperty();
        FabricModelController model = new FabricModelController();
        model.initFabric(properties);
        new FactoryUI(model);
    }

    private static Properties getProperty() {

        Properties property = new Properties();

        try (InputStreamReader reader = new InputStreamReader(
                new FileInputStream(PROPERTIES_FILE))) {
            property.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return property;
    }
}
