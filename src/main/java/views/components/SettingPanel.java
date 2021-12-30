package views.components;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class SettingPanel extends JPanel {
    private JComboBox<String> selector;

    public SettingPanel(Set<String> options, String selected, String labelText) {
        setLayout(new FlowLayout());
        setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        JLabel label = new JLabel(labelText);

        selector = new JComboBox<>();
        int selectedIndex = 0;
        String[] names = options.toArray(new String[0]);
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if (selected.equals(name))
                selectedIndex = i;

            selector.addItem(name);
        }
        selector.setSelectedIndex(selectedIndex);

        add(label);
        add(selector);
    }

    public JComboBox<String> getSelector() {
        return selector;
    }
}
