package factory.ui.panels.settings;

import factory.ui.panels.Stylization;

import javax.swing.*;

class SubSettingPanel extends JPanel {

    private final JSlider[] sliders;

    public SubSettingPanel(String header, int startVal, String... sliderNames) {
        Box boxIn = new Box(BoxLayout.X_AXIS);
        Box boxOut = new Box(BoxLayout.Y_AXIS);

        int len = sliderNames.length;
        sliders = new JSlider[len];
        for (int i = 0; i < len; i++) {
            Box sliderBox = new Box(BoxLayout.Y_AXIS);
            JLabel sliderLabel =    Stylization.getLabel(sliderNames[i]);
            sliders[i] =            Stylization.getSlider(startVal);
            sliderLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
            sliders[i].setAlignmentX(JSlider.CENTER_ALIGNMENT);
            sliderBox.add(sliderLabel);
            sliderBox.add(sliders[i]);
            boxIn.add(sliderBox);
            boxIn.add(Box.createHorizontalStrut(10));
        }

        JLabel headerLabel = Stylization.getLabel(header);
        headerLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        boxOut.add(headerLabel);
        boxOut.add(boxIn);
        add(boxOut);
    }

    public JSlider[] getSliders() {
        return sliders;
    }
}
