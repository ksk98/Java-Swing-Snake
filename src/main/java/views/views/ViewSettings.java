package views.views;

import entities.data.difficulty.DifficultyRepo;
import entities.data.size.SizeRepo;
import managers.SettingsManager;
import managers.Tiles.TileSetRepo;
import views.components.SettingPanel;

import javax.swing.*;
import java.awt.*;

public class ViewSettings extends ViewBase {
    private SettingsManager settingsManager;
    private JButton goBack;

    public ViewSettings(SettingsManager settingsManager) {
        this.settingsManager = settingsManager;
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        SettingPanel difficultyPanel = new SettingPanel(
                DifficultyRepo.getDifficulties(),
                0,
                "DIFFICULTY: ");
        difficultyPanel.getSelector().addActionListener(actionEvent -> settingsManager.setDifficulty(DifficultyRepo.getDifficulty(difficultyPanel.getSelected())));
        difficultyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(difficultyPanel);

        SettingPanel sizePanel = new SettingPanel(
                SizeRepo.getSizeNames(),
                0,
                "BOARD SIZE: "
        );
        sizePanel.getSelector().addActionListener(actionEvent -> settingsManager.setSize(SizeRepo.getSize(sizePanel.getSelected())));
        sizePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(sizePanel);

        SettingPanel tileSetPanel = new SettingPanel(
                TileSetRepo.getInstance().getSets(),
                0,
                "TILE SET: "
        );
        tileSetPanel.getSelector().addActionListener(actionEvent -> settingsManager.setTileSet(TileSetRepo.getInstance().getTileSet(tileSetPanel.getSelected())));
        tileSetPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(tileSetPanel);

        goBack = new JButton("BACK");
        goBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(goBack);

        revalidate();
        pack();
        centerScreen();
    }

    public JButton getGoBack() {
        return goBack;
    }
}
