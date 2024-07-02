package factory.ui.panels.settings;

import factory.model.FabricModelController;

import javax.swing.*;

public class SettingsPanel extends JPanel {

    public SettingsPanel(FabricModelController modelController) {
        Box box = new Box(BoxLayout.X_AXIS);
        int startVal = FabricModelController.START_TIME_TO_CREATE;
        SubSettingPanel storageSettings = new SubSettingPanel("STORAGE",startVal,"ENGINE","BODY","ACCESS.");
        SubSettingPanel factorySettings = new SubSettingPanel("FACTORY",startVal,"CAR");
        SubSettingPanel dealerSettings =  new SubSettingPanel("DEALER",startVal,"SELL");

        JSlider[] sliders = storageSettings.getSliders();

        sliders[0].addChangeListener(e ->
                modelController.setEngineCreationTime(((JSlider) e.getSource()).getValue()));
        sliders[1].addChangeListener(e ->
                modelController.setBodyCreationTime(((JSlider) e.getSource()).getValue()));
        sliders[2].addChangeListener(e ->
                modelController.setAccessoryCreationTime(((JSlider) e.getSource()).getValue()));

        sliders = factorySettings.getSliders();
        sliders[0].addChangeListener(e ->
                modelController.setCarAssemblingTime(((JSlider) e.getSource()).getValue()));

        sliders = dealerSettings.getSliders();
        sliders[0].addChangeListener(e ->
                modelController.setDealerSellTime(((JSlider) e.getSource()).getValue()));

        box.add(storageSettings);
        box.add(factorySettings);
        box.add(dealerSettings);
        add(box);

    }
}
