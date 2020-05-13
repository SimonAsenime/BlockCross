package block_cross.objects;

import block_cross.util.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

// An essential base class to all of the block_cross.main.objects shown on screen in the game.

public class Game_Object {

    protected float x, y;
    protected Hitbox hit;
    private BufferedImage img;

    // Has
    public Game_Object (int x, int y, int width, int height, String path) {
        this.x = x;
        this.y = y;
        hit = new Hitbox(x,y,width,height);
        img = Utilities.load_image(path);
    }


    public int get_x () {
        return (int)x;
    }

    public int get_y () {
        return (int)y;
    }

    public void set_x (int x) {
        this.x = x;
        hit.get_bounds().setLocation(x,(int)y);
    }

    public void set_y (int y) {
        this.y = y;
        hit.get_bounds().setLocation((int)x, y);
    }

    public void update_x (float val) {
        x += val;
        hit.get_bounds().setLocation((int)x,(int)y);
    }

    public void update_y (float val) {
        y += val;
        hit.get_bounds().setLocation((int)x,(int)y);
    }

    public Hitbox get_hit () {
        return hit;
    }

    public void draw (Graphics g) {
        g.drawImage(img, (int)x, (int)y, null);
    }

    public void change_image (BufferedImage bi) {
        img = bi;
    }

    public BufferedImage get_image () {
        return img;
    }
}
