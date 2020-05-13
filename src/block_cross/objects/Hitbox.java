package block_cross.objects;

import java.awt.*;

// This class handles all of the game's collisions.
// Uses rectangles and the .intersects() method.

public class Hitbox {
    private Rectangle bounds;

    public Hitbox (int x, int y, int width, int height) {
        bounds = new Rectangle(x,y,width,height);
    }

    public boolean is_colliding (Hitbox h) {
        return bounds.intersects(h.get_bounds());
    }

    public Rectangle get_bounds () {
        return bounds;
    }
}
