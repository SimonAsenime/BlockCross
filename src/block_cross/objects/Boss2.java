package block_cross.objects;

import block_cross.main.Stage;
import block_cross.util.Animation;
import block_cross.util.AnimationBuffer;
import block_cross.util.SpriteSheet;
import block_cross.util.Utilities;

// This is one of the more complex classes. It is quite hardcoded but there are some general solutions.
// I am quite happy with the A.I. as it meets my standards for being challenging very well.
// The A.I. puts significant pressure on the player and it works fluidly.
// This boss relies on ranged attacks such as a light attack and  heavy attack that are
// fired in variations of duo attacks or single attacks.

public class Boss2 extends Live_Object {
    // Cooldowns and other fields.
    private int attack_cooldown1 = 0;
    private int attack_cooldown2 = 0;
    private Player player;

    // The constructor is not complicated but there are multiple levels of inheritance. Animations are on a SpriteSheet.
    public Boss2 (int x, int y, int width, int height, String path, AnimationBuffer ab, Stage st) {
        super(x, y, width, height, path, ab, st);

        health = 350;
        animations = new SpriteSheet(
                "Finished/Boss2Animations.png", 196, 192);
    }

    // This method controls the A.I., its movements, and its constraints. The A.I. stops taking action if the player dies
    // or the boss dies.
    // This class uses velocity as opposed to translation for the movements to make the game much more fluid and dynamic.
    // This A.I. is not bound by gravity as the other one is and flies in the sky.
    // This class is constantly animated whereas the other boss and the player are not.
    public void update () {
        if (health < 0)
            health = 0;

        if (player.get_health() > 0 && health > 0) {

            ab.add_animation(this, new Animation(animations, 196, 192, 25, 5, this));

            if (motion.get_x() > 0) {
                if (motion.get_x() < .05f)
                    stop_x_motion();
                motion.update_x(-.05f);
            } else if (motion.get_x() < 0) {
                if (motion.get_x() > -.05f)
                    stop_x_motion();
                motion.update_x(.05f);
            }

            if (player.get_y() < 270) {
                if (player.get_x() - (x+97.5) < -70)
                    motion.update_x(-.15f);
                else if (player.get_x() - (x+97.5) > 160) {
                    motion.update_x(.15f);
                }
                if (attack_cooldown2 <= 0) {
                    spawn_single_attack(false, (float)(x+97.5), new Velocity(0, 2f));
                    attack_cooldown2 = 40;
                }
            } else {
                if (Utilities.percent_rand(.5f))
                    motion.update_x(Utilities.rand_float());
                else
                    motion.update_x(-Utilities.rand_float());
                if (attack_cooldown1 <= 0) {
                    attack_cooldown1 = 35;
                    if (Utilities.percent_rand(.5f)) {
                        spawn_single_attack(true, (float) (x + 97.5), new Velocity(0, 2f));
                    } else {
                        if (Utilities.percent_rand(.8f)) {
                            spawn_double_attack(true);
                        } else {
                            spawn_double_attack(false);
                        }
                    }
                }
            }

            update_x(motion.get_x());
            update_y(motion.get_y());
            attack_cooldown2--;
            attack_cooldown1--;
        }

    }

    // Single attack of either light or heavy variety.
    public void spawn_single_attack (boolean swap, float x, Velocity m) {
        if (swap) {
            DamageHitbox dh = new DamageHitbox("Finished/BossProjectile1.png", x, 212,
                    42, 42, this, 4, 90, m);
            stage.add_damage_hitbox(dh);
        } else {
            DamageHitbox dh = new DamageHitbox("Finished/BossProjectile2.png", x, 212,
                    42, 42, this, 8, 90, m);
            stage.add_damage_hitbox(dh);
        }
    }

    // Double attack of either light or heavy variety.
    public void spawn_double_attack (boolean swap) {
        spawn_single_attack(swap, x, new Velocity(-2f, 2.5f));
        spawn_single_attack(swap, x+195, new Velocity(2f, 2.5f));
    }

    public void set_player (Player p) {
        player = p;
    }

    // The two belows functions stop the boss's velocity.
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
        x = 605;
        y = 20;
        health = 350;
    }

}
