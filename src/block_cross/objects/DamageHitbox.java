package block_cross.objects;

import block_cross.util.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

// This class is the block_cross.main used in the attacks that damage both the bosses and the player.

public class DamageHitbox {

    private BufferedImage img;
    private Hitbox hit;
    private int damage;
    private Live_Object id;
    private Velocity motion;
    private float x,y;
    private int lifespan;
    private int count = 0;
    private boolean dead = false;


    // Utilizes an id tag to ensure the owner does not get damaged.
    public DamageHitbox (String path, float x, float y, int width, int height, Live_Object lo, int d, int ls, Velocity m) {
        img = Utilities.load_image(path);
        this.x = x;
        this.y = y;
        hit = new Hitbox((int)x,(int)y,width,height);
        damage = d;
        id = lo;
        lifespan = ls;
        motion = m;
    }

    public void set_x (int val) {
        x = val;
        hit.get_bounds().x = val;
    }

    public void set_y (int val) {
        y = val;
        hit.get_bounds().y = val;
    }

    // Update method to be called through block_cross.main loop.
    public void update () {
        if (count < lifespan)
            count++;
        else {
            dead = true;
        }
        x += motion.get_x();
        y += motion.get_y();
        hit.get_bounds().x = (int)x;
        hit.get_bounds().y = (int)y;
    }

    // Id providing
    public Live_Object get_id () {
        return id;
    }

    public int get_damage () {
        return damage;
    }

    public boolean is_dead () {
        return dead;
    }

    public Hitbox get_hit () {
        return hit;
    }

    public void draw (Graphics g) {
        g.drawImage(img, hit.get_bounds().x, hit.get_bounds().y, null);
    }
}
