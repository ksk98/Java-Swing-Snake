package views.views;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;
import java.net.URL;

public class ViewMenu extends ViewBase {
    private final int spacingX = 10, spacingY = 5;
    private final JButton play, settings, highscores, exit;

    public ViewMenu() {
        setPreferredSize(new Dimension(500, 300));
        URL folderURL = ClassLoader.getSystemResource("sprites/");
        setContentPane(new JLabel(new ImageIcon(folderURL.getPath() + "background.png")));
        this.play = new JButton("PLAY");
        this.play.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.settings = new JButton("SETTINGS");
        this.settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.highscores = new JButton("HIGHSCORES");
        this.highscores.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.exit = new JButton("EXIT");
        this.exit.setAlignmentX(Component.CENTER_ALIGNMENT);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        create();
        revalidate();
        pack();
        centerScreen();
    }

    private void create() {
        URL folderURL = ClassLoader.getSystemResource("logo.png");
        JLabel logo = new JLabel(new ImageIcon(folderURL));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logo);
        add(Box.createRigidArea(new Dimension(spacingX + logo.getWidth(), spacingY)));
        add(play);
        add(Box.createRigidArea(new Dimension(spacingX, spacingY)));
        add(settings);
        add(Box.createRigidArea(new Dimension(spacingX, spacingY)));
        add(highscores);
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

    public JButton getHighscores() {
        return highscores;
    }

    public JButton getExit() {
        return exit;
    }
}
