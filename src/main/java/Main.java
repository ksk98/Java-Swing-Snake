import managers.SettingsManager;
import managers.ViewManager;

import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws URISyntaxException{
        SettingsManager settingsManager = new SettingsManager();
        ViewManager viewManager = new ViewManager(settingsManager);
    }
}
