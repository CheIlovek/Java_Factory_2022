package factory.ui;

import factory.model.FabricModelController;
import factory.ui.panels.MainPanel;
import factory.ui.panels.settings.SettingsPanel;

import javax.swing.*;
import java.awt.*;

public class FactoryUI {

    private final int SETTINGS_WINDOW_HEIGHT = 320;
    private final int SETTINGS_WINDOW_WIDTH = 410;
    private final int MAIN_WINDOW_HEIGHT = 300;
    private final int MAIN_WINDOW_WIDTH = 300;
    private final int REFRESH_RATE = 50;

    private final FabricModelController modelController;
    private SettingsPanel settingsPanel;
    private MainPanel mainPanel;

    private JFrame settingsFrame;
    private JFrame mainFrame;

    public FactoryUI(FabricModelController modelController) {
        this.modelController = modelController;
        init();
        update();
    }

    private void update() {
        while (true) {
            updateInfo();
            try {
                Thread.sleep(REFRESH_RATE);
            } catch (InterruptedException ignore) {}
        }
    }


    private void init() {
        mainFrame = getFrame(MAIN_WINDOW_WIDTH,MAIN_WINDOW_HEIGHT,true,"Fabric monitor");
        mainPanel =  getMainPanel();
        mainFrame.add(mainPanel);
        settingsFrame = getFrame(SETTINGS_WINDOW_WIDTH,SETTINGS_WINDOW_HEIGHT,false, "Fabric settings");
        settingsPanel = new SettingsPanel(modelController);
        settingsFrame.add(settingsPanel);
        mainFrame.revalidate();
    }

    private void updateInfo() {
        int val = modelController.getEngineWarehouseSize();
        mainPanel.setEngineStorageValue(val);

        val = modelController.getBodyWarehouseSize();
        mainPanel.setBodyStorageValue(val);

        val = modelController.getAccessoryWarehouseSize();
        mainPanel.setAccessoryStorageValue(val);

        val = modelController.getCarWarehouseSize();
        mainPanel.setCarStorageValue(val);

        mainPanel.setSoldCarsLabel(modelController.getCountSoldCars());
    }



    private JFrame getFrame(int w, int h,boolean isMain, String title) {
        JFrame frame = new JFrame();
        frame.setResizable(false);

        if (isMain) {
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }
        else {
            frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            frame.setVisible(false);
        }
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        frame.setBounds(
                dim.width/2 - w/2, dim.height/2 - h/2,
                w, h);
        frame.setTitle(title);
        return frame;
    }

    private MainPanel getMainPanel() {
        int[] maxSizes = new int[] {
                modelController.getEngineWarehouseMaxSize(),
                modelController.getBodyWarehouseMaxSize(),
                modelController.getAccessoryWarehouseMaxSize(),
                modelController.getCarWarehouseMaxSize()
        };
        MainPanel panel = new MainPanel(maxSizes);
        JButton button = panel.getSettingsButton();
        button.addActionListener((e) -> {
            if (!settingsFrame.isVisible()) {
                settingsFrame.setVisible(true);
            }
        });
        return panel;

    }

}
