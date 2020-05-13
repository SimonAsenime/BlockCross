package block_cross.objects;

// A very useful class that provides the support that creates a feeling of dynamic movements that are not simply translations.
// Is a 2D vector (the math kind not the cs kind)
// All in all its a class that boosts the graphics of the game significantly.

public class Velocity {
    private float x, y;

    public Velocity (float x, float y) {
        this.x = x;
        this.y = y;
    }

    // Max speed is 5 pixels per frame in any direction.

    public void update_x (float val) {
        if (x+val < 5 && x+val > -5)
            x += val;
        else if (x+val < -5)
            x = -5;
        else if (x+val > 5)
            x = 5;
    }

    public void update_y (float val) {
        if (y+val < 5 && y+val > -5)
            y += val;
        else if (y+val < -5)
            y = -5;
        else if (y+val > 5)
            y = 5;
    }

    public void set_x (int x) {
        this.x = x;
    }

    public void set_y (int y) {
        this.y = y;
    }

    public float get_x () {
        return x;
    }

    public float get_y () {
        return y;
    }
}
