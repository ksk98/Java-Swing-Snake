package concurrents;

import logic.Game;
import managers.SettingsManager;
import views.views.ViewGame;

public class GameThread extends Thread {
    private ViewGame game;
    private SettingsManager settingsManager;
    private Game gameInstance;

    public GameThread(ViewGame game, SettingsManager settingsManager) {
        this.game = game;
        this.settingsManager = settingsManager;
    }

    public void abort() {
        gameInstance.isRunning = false;
    }

    @Override
    public void run() {
        try {
            gameInstance = new Game(game, game.getBoardView(), game.getBoardPanel(), settingsManager);
            gameInstance.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
