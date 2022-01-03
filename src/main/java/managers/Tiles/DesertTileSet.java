package managers.Tiles;

import utility.ImageIconUtility;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class DesertTileSet extends TileSetBase {

    public DesertTileSet() throws URISyntaxException {
        super();

        URL folderURL = ClassLoader.getSystemResource("sprites/sand");
        File folder = new File(folderURL.toURI());

        for (String path: Objects.requireNonNull(folder.list())) {
            String finalPath = folder.getAbsolutePath() + "\\" + path;
            if (path.startsWith("sand_base"))
                tiles.add(ImageIconUtility.createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("sand_deco"))
                decorations.add(ImageIconUtility.createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("sand_obst"))
                obstacles.add(ImageIconUtility.createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("sand_treasure"))
                treasures.add(ImageIconUtility.createScaledIcon(new ImageIcon(finalPath)));
        }
    }
}
