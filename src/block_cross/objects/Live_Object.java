package block_cross.objects;

import block_cross.main.Stage;
import block_cross.util.AnimationBuffer;
import block_cross.util.SpriteSheet;

import java.awt.image.BufferedImage;

// An essential abstract class built on Game_Objects.
// Its used to create the player and bosses with health and provide the background support for the animations.

public abstract class Live_Object extends Game_Object {

    protected int health;
    protected AnimationBuffer ab;
    protected SpriteSheet animations;
    private BufferedImage default_image;
    protected Stage stage;
    protected Velocity motion;

    // Has a default image to go back to after the animation finishes. Provides stage and animation support.
    public Live_Object(int x, int y, int width, int height, String path, AnimationBuffer ab, Stage st) {
        super(x,y,width,height,path);

        health = 0;
        this.ab = ab;
        default_image = get_image();
        motion = new Velocity(0,0);
        stage = st;
    }

    public BufferedImage get_default_image () {
        return default_image;
    }


    public void reduce_health (int val) {
        health -= val;
    }

    public int get_health () {
        return health;
    }

    public void set_stage (Stage st) {
        stage = st;
    }

    // Most important method.
    public abstract void update ();
}
