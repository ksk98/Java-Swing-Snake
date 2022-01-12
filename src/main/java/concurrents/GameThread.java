package concurrents;

import logic.Game;
import managers.HighScoreManager;
import managers.SettingsManager;
import views.views.ViewGame;

import javax.swing.*;
import java.sql.SQLException;

public class GameThread extends Thread {
    private ViewGame game;
    private SettingsManager settingsManager;
    private Game gameInstance;
    private TimeCounterThread timeCounterThread;

    public GameThread(ViewGame game, SettingsManager settingsManager, TimeCounterThread timeCounterThread) {
        this.game = game;
        this.settingsManager = settingsManager;
        this.timeCounterThread = timeCounterThread;
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
        timeCounterThread.stopRunning();
        String name = JOptionPane.showInputDialog(game, "Game over! Name?");
        try {
            HighScoreManager.getInstance().appendScore(name, gameInstance.getScore());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
