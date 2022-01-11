package managers;

import concurrents.GameThread;
import entities.data.size.Size;
import views.views.ViewGame;
import views.views.ViewMenu;
import views.views.ViewSettings;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.net.URISyntaxException;
import java.util.HashMap;

public class ViewManager {
    enum View { MENU, GAME, SETTINGS, PROFILE }

    private HashMap<View, JFrame> views;
    private View current = View.MENU;
    private SettingsManager settingsManager;
    private GameThread gameThread;


    public ViewManager(SettingsManager settingsManager) throws URISyntaxException {
        views = new HashMap<>();
        this.settingsManager = settingsManager;

        createMenu();
        views.get(View.MENU).setVisible(true);

        createSettings();

        // TODO: profiles
    }

    private void createMenu() throws URISyntaxException {
        ViewMenu menu = new ViewMenu();
        menu.getPlay().addActionListener(actionEvent -> {
            createGame();
            changeViewTo(View.GAME);
        });
        menu.getSettings().addActionListener(actionEvent -> changeViewTo(View.SETTINGS));
        menu.getProfile().addActionListener(actionEvent -> changeViewTo(View.PROFILE));
        menu.getExit().addActionListener(actionEvent -> System.exit(0));

        views.put(View.MENU, menu);
        menu.setVisible(false);
    }

    private void createGame() {
        Size size = settingsManager.getBoardSize();
        ViewGame game = new ViewGame(
                size.getX(), size.getY(),
                settingsManager.getTileSet(),
                settingsManager.getDifficulty()
        );

        game.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                JOptionPane.showMessageDialog(game, "Press ok to start");
                gameThread = new GameThread(game, settingsManager);
                gameThread.start();
                game.startTimer();
            }
        });

        game.getExitButton().addActionListener(actionEvent -> {
            game.stopTimer();
            changeViewTo(View.MENU);
            views.remove(View.GAME);
            gameThread.abort();
            gameThread = null;
        });

        views.put(View.GAME, game);
        game.setVisible(false);
    }

    private void createSettings() {
        ViewSettings settings = new ViewSettings(settingsManager);
        settings.getGoBack().addActionListener(actionEvent -> changeViewTo(View.MENU));

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
