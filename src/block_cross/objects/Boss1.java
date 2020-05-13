package block_cross.objects;

import block_cross.main.Stage;
import block_cross.util.Animation;
import block_cross.util.AnimationBuffer;
import block_cross.util.SpriteSheet;

// This is one of the more complex classes. It is quite hardcoded but there are some general solutions.
// I am quite happy with the A.I. as it meets my standards for being challenging very well.
// The A.I. puts significant pressure on the player and it works fluidly.
// This boss relies on physical attacks such as a jump smashdown and
// arm swipe(arm swipe does sound not good and the name is a W.I.P.).

public class Boss1 extends Live_Object {
    // Cooldowns and other fields.
    private int attack_cooldown1 = 0;
    private int attack_cooldown2 = 0;
    private Player player;
    private DamageHitbox jump_box;

    // The constructor is not complicated but there are multiple levels of inheritance. Animations are on a SpriteSheet.
    public Boss1 (int x, int y, int width, int height, String path, AnimationBuffer ab, Stage st) {
        super(x, y, width, height, path, ab, st);

        health = 500;
        animations = new SpriteSheet(
                "Finished/Boss1Animations.png", 200, 147);
    }

    // This method controls the A.I., its movements, and its constraints. The A.I. stops taking action if the player dies
    // or the boss dies.
    // This class uses velocity as opposed to translation for the movements to make the game much more fluid and dynamic.
    public void update () {
        if (health < 0)
            health = 0;

        if (player.get_health() > 0 && health > 0) {

            motion.update_y(.05f);

            if (motion.get_x() > 0) {
                if (motion.get_x() < .05f)
                    stop_x_motion();
                motion.update_x(-.05f);
            } else if (motion.get_x() < 0) {
                if (motion.get_x() > -.05f)
                    stop_x_motion();
                motion.update_x(.05f);
            }

            if ((player.get_x()-x < -250 || player.get_x()-x > 250) && attack_cooldown2 <= 0) {
                jump();
                attack_cooldown2 = 500;
            }

            if (player.get_x()-x < -10 && attack_cooldown1 <= 0) {
                left_attack();
                attack_cooldown1 = 30;
            } else if (player.get_x()-x > 10 && attack_cooldown1 <= 0) {
                right_attack();
                attack_cooldown1 = 30;
            }

            if (player.get_x() - x < -70)
                motion.update_x(-.1f);
            else if (player.get_x() - x > 160) {
                motion.update_x(.1f);
            }

            update_x(motion.get_x());
            update_y(motion.get_y());
            if (jump_box != null) {
                jump_box.set_x((int)x);
                jump_box.set_y((int)y);
            }
            attack_cooldown1--;
            attack_cooldown2--;
        } else if (player.get_health() <= 0 || health <= 0 ) {
            stop_y_motion();
            stop_x_motion();
        }
    }

    // One of the arm swipe attacks. Left variety.
    public void left_attack () {
        DamageHitbox dh = new DamageHitbox("Finished/Empty.png", x, y,
                hit.get_bounds().width, hit.get_bounds().height, this, 5,
                10, new Velocity(0,0));
        if (player.get_y() < 250) {
            ab.add_animation(this, new Animation(animations.get_strip(0), 200, 147, 30, 10, this));
        } else {
            ab.add_animation(this, new Animation(animations.get_strip(1), 200, 147, 30, 10, this));
        }
        stage.add_damage_hitbox(dh);
    }

    // One of the arm swipe attacks. Right variety.
    public void right_attack () {
        DamageHitbox dh = new DamageHitbox("Finished/Empty.png", x, y,
                hit.get_bounds().width, hit.get_bounds().height, this, 5,
                10, new Velocity(0,0));
        if (player.get_y() < 250) {
            ab.add_animation(this, new Animation(animations.get_strip(2), 200, 147, 30, 10, this));
        } else {
            ab.add_animation(this, new Animation(animations.get_strip(3), 200, 147, 30, 10, this));
        }
        stage.add_damage_hitbox(dh);
    }

    // The jumping attack.
    public void jump () {
        jump_box = new DamageHitbox("Finished/Empty.png", (float)(x+12.5), (float)(y+12.5),
                hit.get_bounds().width-25, hit.get_bounds().height-25, this, 20,
                500, new Velocity(0,0));
        stage.add_damage_hitbox(jump_box);
        motion.update_y(-5);
    }

    // External helper method for the jump attack.
    public void end_jump () {
        stage.remove_damage_hitbox(jump_box);
        jump_box = null;
    }

    public void set_player (Player p) {
        player = p;
    }

    // The two below functions stop the boss's velocity.
    public void stop_y_motion() {
        motion.set_y(0);
    }

    public void stop_x_motion() {
        motion.set_x(0);
    }

    // Reset the boss after leaving the stage.
    public void reset () {
        stop_x_motion();
        stop_y_motion();
        x = 640;
        y = 253;
        health = 500;
    }
}
