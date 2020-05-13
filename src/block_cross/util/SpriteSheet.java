package block_cross.util;

import java.awt.image.BufferedImage;

// The SpriteSheet class. This class is used to house the animations and grab images as necessary.

public class SpriteSheet {

    private BufferedImage sheet;
    private int width, height;
    private int length;


    public SpriteSheet (String path, int w, int h) {
        sheet = Utilities.load_image(path);
        length = (sheet.getWidth()/w)*(sheet.getHeight()/h);
        width = w;
        height = h;
    }

    public SpriteSheet (BufferedImage im, int w, int h) {
        sheet = im;
        length = (sheet.getWidth()/w)*(sheet.getHeight()/h);
        width = w;
        height = h;
    }

    public BufferedImage get_image (int ix, int iy) {
        if (ix*iy <= length-1)
            return sheet.getSubimage(ix*width, iy*height, width, height);
        return null;
    }

    // Get a strip of the spritesheet for one specific animation as the spritesheet will usually contain multiple.
    public SpriteSheet get_strip (int index) {
        if (index < sheet.getHeight()/height)
            return new SpriteSheet(sheet.getSubimage(0, index*height, sheet.getWidth(), height), width, height);
        return null;
    }

    public int get_width () {
        return width;
    }

    public int get_height () {
        return height;
    }

    public int length () {
        return length;
    }
}
