package factory.ui.panels;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private final JButton settingsButton;
    JProgressBar engineStorageBar;
    JProgressBar bodyStorageBar;
    JProgressBar accessoryStorageBar;
    JProgressBar carStorageBar;

    JLabel soldCarsLabel;

    public MainPanel(int... storageMaxValues) {
        Box box = new Box(BoxLayout.Y_AXIS);
        box.setAlignmentX(Box.CENTER_ALIGNMENT);
        add(box);

        engineStorageBar =      Stylization.getProgressBar(storageMaxValues[0]);
        bodyStorageBar =        Stylization.getProgressBar(storageMaxValues[1]);
        accessoryStorageBar =   Stylization.getProgressBar(storageMaxValues[2]);
        carStorageBar =         Stylization.getProgressBar(storageMaxValues[3]);

        JLabel[] labels = new JLabel[4];
        labels[0] = Stylization.getLabel("ENGINE STORAGE:");
        labels[1] = Stylization.getLabel("BODY STORAGE:");
        labels[2] = Stylization.getLabel("ACCESSORY STORAGE:");
        labels[2].setPreferredSize(new Dimension(171,30));
        labels[3] = Stylization.getLabel("CAR STORAGE:");
        for (JLabel label : labels) {
            label.setAlignmentX(JLabel.LEFT);
            label.setHorizontalAlignment(JLabel.LEFT);
        }

        box.add(labels[0]);
        box.add(engineStorageBar);

        box.add(labels[1]);
        box.add(bodyStorageBar);


        box.add(labels[2]);
        box.add(accessoryStorageBar);
        box.add(labels[3]);
        box.add(carStorageBar);


        soldCarsLabel = Stylization.getLabel("CAR SOLD: 0");
        soldCarsLabel.setAlignmentX(JLabel.LEFT);
        soldCarsLabel.setHorizontalAlignment(JLabel.LEFT);
        box.add(soldCarsLabel);

        settingsButton = Stylization.getButton("settings");
        settingsButton.setAlignmentX(JButton.LEFT);
        add(settingsButton);

    }

    public void setAccessoryStorageValue(int val) {
        accessoryStorageBar.setValue(val);
        accessoryStorageBar.repaint();
    }
    public void setEngineStorageValue(int val) {
        engineStorageBar.setValue(val);
        engineStorageBar.repaint();
    }
    public void setBodyStorageValue(int val) {
        bodyStorageBar.setValue(val);
        bodyStorageBar.repaint();
    }
    public void setCarStorageValue(int val) {
        carStorageBar.setValue(val);
        carStorageBar.repaint();
    }

    public void setSoldCarsLabel(int val) {
        soldCarsLabel.setText("CAR SOLD: " + val);
        soldCarsLabel.repaint();
    }

    public JButton getSettingsButton() {
        return settingsButton;
    }
}
