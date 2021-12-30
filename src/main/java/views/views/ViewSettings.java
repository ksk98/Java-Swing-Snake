package views.views;

import entities.difficulty.DifficultyRepo;
import entities.size.SizeRepo;
import managers.SettingsManager;
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
                DifficultyRepo.getDifficultyNames(),
                settingsManager.getDifficulty().getDisplayName(),
                "DIFFICULTY: ");
        difficultyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(difficultyPanel);

        SettingPanel sizePanel = new SettingPanel(
                SizeRepo.getSizeNames(),
                settingsManager.getSize().getDisplayName(),
                "BOARD SIZE: "
        );
        sizePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(sizePanel);

        // TODO: other tile sets?

        goBack = new JButton("BACK");
        goBack.setAlignmentX(Component.LEFT_ALIGNMENT);
        add(goBack);

        revalidate();
        pack();
    }

    public JButton getGoBack() {
        return goBack;
    }
}
