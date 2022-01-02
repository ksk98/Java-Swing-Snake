package managers;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class DesertTileSetManager extends TileSetManagerBase {

    public DesertTileSetManager() throws URISyntaxException {
        super();

        URL folderURL = ClassLoader.getSystemResource("sprites/sand");
        File folder = new File(folderURL.toURI());

        for (String path: Objects.requireNonNull(folder.list())) {
            String finalPath = folder.getAbsolutePath() + "\\" + path;
            if (path.startsWith("sand_base"))
                tiles.add(createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("sand_deco"))
                decorations.add(createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("sand_obst"))
                obstacles.add(createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("sand_treasure"))
                treasures.add(createScaledIcon(new ImageIcon(finalPath)));
        }
    }
}
