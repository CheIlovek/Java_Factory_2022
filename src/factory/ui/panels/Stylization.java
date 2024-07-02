package factory.ui.panels;

import javax.swing.*;
import java.awt.*;

public class Stylization {

    static Font font = new Font("Lucida Sans Unicode", Font.PLAIN,14);
    public static JButton getButton(String label) {
        JButton button = new JButton(label);
        button.setFont(font);
        button.setBackground(new Color(0x78DCB1));
        button.setForeground(new Color(0x083B28));
        button.setMinimumSize(new Dimension(200,40));
        button.setMaximumSize(new Dimension(200,40));
        return button;
    }

    public static JLabel getLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setForeground(Color.BLACK);
        label.setMinimumSize(new Dimension(100,50));
        label.setMaximumSize(new Dimension(400,50));

        return label;
    }

    public static JSlider getSlider(int startVal) {
        JSlider slider = new JSlider(JSlider.VERTICAL,0, 50000, startVal);
        slider.setMinimumSize(new Dimension(10,200));
        slider.setMaximumSize(new Dimension(100,200));
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(5000);
        return slider;
    }

    public static JProgressBar getProgressBar(int max) {
        JProgressBar bar = new JProgressBar(0,max);
        bar.setStringPainted(true);
        bar.setMinimumSize(new Dimension(100,50));
        bar.setMaximumSize(new Dimension(300,50));
        return bar;
    }
}
