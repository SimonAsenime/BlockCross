package block_cross.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

// A bit of a random class that fulfills basic utility requirements in some sections of the game.

public class Utilities {

    private static Random rand = new Random(System.currentTimeMillis());

    // Image loading. The block_cross.main use of this class and the most important use of this class.
    public static BufferedImage load_image (String path) {
        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            img = null;
            e.printStackTrace();
        }
        return img;
    }

    // Used for randomly selecting choices.
    public static boolean percent_rand (float chance) {
        if (rand.nextFloat() <= chance)
            return true;
        return false;
    }

    // Generates random float output. Its used in place creating another random object.
    public static float rand_float () {
        return rand.nextFloat();
    }

}
