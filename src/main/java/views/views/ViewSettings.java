package views.views;

import entities.data.difficulty.DifficultyRepo;
import entities.data.size.SizeRepo;
import managers.SettingsManager;
import views.components.SettingPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        difficultyPanel.getSelector().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                settingsManager.setDifficulty(DifficultyRepo.getDifficulty(difficultyPanel.getSelected()));
            }
        });
        difficultyPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(difficultyPanel);

        SettingPanel sizePanel = new SettingPanel(
                SizeRepo.getSizeNames(),
                0,
                "BOARD SIZE: "
        );
        sizePanel.getSelector().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                settingsManager.setSize(SizeRepo.getSize(sizePanel.getSelected()));
            }
        });
        sizePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(sizePanel);

        // TODO: other tile sets?

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
