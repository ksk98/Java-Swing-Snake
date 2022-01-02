package views.components;

import javax.swing.*;
import java.awt.*;

public class SettingPanel extends JPanel {
    private JComboBox<String> selector;

    public SettingPanel(String[] options, int selected, String labelText) {
        setLayout(new FlowLayout());
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JLabel label = new JLabel(labelText);

        selector = new JComboBox<>(options);
        selector.setSelectedIndex(selected);

        add(label);
        add(selector);
    }

    public JComboBox<String> getSelector() {
        return selector;
    }

    public String getSelected() {
        return (String) selector.getSelectedItem();
    }
}
