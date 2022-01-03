package utility;

import javax.swing.*;
import java.awt.*;

public abstract class ImageIconUtility {
    public static ImageIcon createScaledIcon(ImageIcon icon) {
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(64, 64,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }
}
