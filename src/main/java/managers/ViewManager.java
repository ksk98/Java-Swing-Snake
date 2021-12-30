package managers;

import entities.size.Size;
import views.views.ViewGame;
import views.views.ViewMenu;
import views.views.ViewSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URISyntaxException;
import java.util.HashMap;

public class ViewManager {
    enum View { MENU, GAME, SETTINGS, PROFILE }

    private HashMap<View, JFrame> views;
    private View current = View.MENU;
    private SettingsManager settingsManager;


    public ViewManager(SettingsManager settingsManager) throws URISyntaxException {
        views = new HashMap<>();
        this.settingsManager = settingsManager;

        createMenu();
        views.get(View.MENU).setVisible(true);

        createSettings();

        // TODO: rest

    }

    private void createMenu() throws URISyntaxException {
        ViewMenu menu = new ViewMenu();
        menu.getPlay().addActionListener(actionEvent -> {
            createGame();
            changeViewTo(View.GAME);
        });

        menu.getSettings().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                changeViewTo(View.SETTINGS);
            }
        });

        menu.getProfile().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                changeViewTo(View.PROFILE);
            }
        });

        menu.getExit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        views.put(View.MENU, menu);
        menu.setVisible(false);
    }

    private void createGame() {
        Size size = settingsManager.getSize();
        ViewGame game = new ViewGame(
                size.getX(), size.getY(),
                settingsManager.getTileSetManager(),
                settingsManager.getDifficulty()
        );

        views.put(View.GAME, game);
        game.setVisible(false);
    }

    private void createSettings() {
        ViewSettings settings = new ViewSettings(settingsManager);
        settings.getGoBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                changeViewTo(View.MENU);
            }
        });

        views.put(View.SETTINGS, settings);
        settings.setVisible(false);
    }

    private void changeViewTo(View view) {
        getCurrentView().setVisible(false);
        current = view;
        getCurrentView().setVisible(true);
    }

    private JFrame getCurrentView() {
        return views.get(current);
    }
}
