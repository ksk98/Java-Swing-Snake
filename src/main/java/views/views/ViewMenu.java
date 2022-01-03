package views.views;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;
import java.net.URL;

public class ViewMenu extends ViewBase {
    private final int spacingX = 10, spacingY = 5;
    private final JButton play, settings, profile, exit;

    public ViewMenu() throws URISyntaxException {
        this.play = new JButton("PLAY");
        this.play.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.settings = new JButton("SETTINGS");
        this.settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.profile = new JButton("PROFILE");
        this.profile.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.exit = new JButton("EXIT");
        this.exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        create();
        revalidate();
        pack();
        centerScreen();
    }

    private void create() throws URISyntaxException {
        URL folderURL = ClassLoader.getSystemResource("logo.png");
        JLabel logo = new JLabel(new ImageIcon(folderURL));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logo);
        add(Box.createRigidArea(new Dimension(spacingX + logo.getWidth(), spacingY)));
        add(play);
        add(Box.createRigidArea(new Dimension(spacingX, spacingY)));
        add(settings);
        add(Box.createRigidArea(new Dimension(spacingX, spacingY)));
        add(profile);
        add(Box.createRigidArea(new Dimension(spacingX, spacingY)));
        add(exit);
        add(Box.createRigidArea(new Dimension(spacingX, spacingY)));
    }

    public JButton getPlay() {
        return play;
    }

    public JButton getSettings() {
        return settings;
    }

    public JButton getProfile() {
        return profile;
    }

    public JButton getExit() {
        return exit;
    }
}
