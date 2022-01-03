package managers.Body;

import utility.ImageIconUtility;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class RegularBodySet extends BodySetBase {

    public RegularBodySet() throws URISyntaxException {
        super();

        URL folderURL = ClassLoader.getSystemResource("sprites/snake/regular");
        File folder = new File(folderURL.toURI());

        for (String path: Objects.requireNonNull(folder.list())) {
            String finalPath = folder.getAbsolutePath() + "\\" + path;
            if (path.startsWith("head"))
                this.head = (ImageIconUtility.createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("head_left"))
                this.headLeft = (ImageIconUtility.createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("head_right"))
                this.headRight = (ImageIconUtility.createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("body_straight"))
                this.body = (ImageIconUtility.createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("body_curved"))
                this.bodyCurved = (ImageIconUtility.createScaledIcon(new ImageIcon(finalPath)));
            else if (path.startsWith("tail"))
                this.tail = (ImageIconUtility.createScaledIcon(new ImageIcon(finalPath)));
        }
    }
}
