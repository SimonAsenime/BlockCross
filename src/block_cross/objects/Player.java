package block_cross.objects;

import block_cross.main.Stage;
import block_cross.util.Animation;
import block_cross.util.AnimationBuffer;
import block_cross.util.Input;
import block_cross.util.SpriteSheet;

// This class was one of my favorite to write and see come to life on the screen. It was the first game_object I wrote in
// while making the game and I'm quite happy with it. The attacks are all ranged but they are multi-directional
// and highly versatile even within the cooldown constraints.

public class Player extends Live_Object {

    // Cooldowns, Orientation of player, input, and jumping.
    private Input input;
    private int attack_cooldown1 = 0;
    private int attack_cooldown2 = 0;
    private int jumps = 2;
    private int jump_cooldown = 0;
    private int orientation = 0;

    // This class is the only class that makes use of the input besides the stage classes and has custom animations as well.
    public Player(int x, int y, int width, int height, String path, AnimationBuffer ab, Input in, Stage st) {
        super(x, y, width, height, path, ab, st);

        health = 100;
        input = in;
        animations = new SpriteSheet(
                "Finished/PlayerAnimations.png", 100, 100);
    }

    // The player can double jump and move in all directions except down.
    // However, there is a downward orientation for attacks that may be used.
    // The player becomes unable to move if they lose. They can still move if they win.
    public void update() {
        if (health < 0)
            health = 0;

        if (health > 0) {
            motion.update_y(.1f);
            if (motion.get_x() > 0) {
                if (motion.get_x() < .1f)
                    stop_x_motion();
                motion.update_x(-.1f);
            } else if (motion.get_x() < 0) {
                if (motion.get_x() > -.1f)
                    stop_x_motion();
                motion.update_x(.1f);
            }

            update_x(motion.get_x());
            update_y(motion.get_y());

            // Orientation and movement management.
            if (input.is_key_pressed(input.KEY_W) || input.is_key_pressed(input.KEY_UP)) {
                if (jumps > 0 && jump_cooldown <= 0) {
                    motion.update_y(-4);
                    jumps--;
                    jump_cooldown = 1;
                }
                orientation = 0;
            }
            if (input.is_key_down(input.KEY_A) || input.is_key_down(input.KEY_LEFT)) {
                motion.update_x(-.5f);
                orientation = 1;
            }
            if (input.is_key_down(input.KEY_D) || input.is_key_down(input.KEY_RIGHT)) {
                motion.update_x(.5f);
                orientation = 2;
            }
            if (input.is_key_down(input.KEY_S) || input.is_key_down(input.KEY_DOWN))
                orientation = 3;
            // Attacks
            if (input.is_key_pressed(input.KEY_Q)) {
                light_attack();
            }
            if (input.is_key_pressed(input.KEY_E)) {
                heavy_attack();
            }
            // Cooldowns
            jump_cooldown--;
            attack_cooldown1--;
            attack_cooldown2--;
        } else {
            stop_x_motion();
            stop_y_motion();
        }
    }

    // A light attack that uses a custom image of a bullet that is shaped like a missile.
    // This is the block_cross.main use of the animations for this class.
    public void light_attack () {
        if (attack_cooldown1 <= 0) {
            DamageHitbox dh;
            attack_cooldown1 = 20;
            switch (orientation) {
                case 0:
                    ab.add_animation(this, new Animation(animations.get_strip(2),
                            100, 100, 20, 5, this));
                    dh = new DamageHitbox("Finished/PlayerProjectileUp.png",
                            x + 35, y, 42, 30, this, 10, 200, new Velocity(0, -3));
                    stage.add_damage_hitbox(dh);
                    break;
                case 1:
                    ab.add_animation(this, new Animation(animations.get_strip(1),
                            100, 100, 20, 5, this));
                    dh = new DamageHitbox("Finished/PlayerProjectileLEFT.png",
                            x, y + 45, 42, 30, this, 10, 200, new Velocity(-3, 0));
                    stage.add_damage_hitbox(dh);
                    break;
                case 2:
                    ab.add_animation(this, new Animation(animations.get_strip(0),
                            100, 100, 20, 5, this));
                    dh = new DamageHitbox("Finished/PlayerProjectileRight.png",
                            x + 79, y + 45, 42, 30, this, 10, 200, new Velocity(3, 0));
                    stage.add_damage_hitbox(dh);
                    break;
                case 3:
                    ab.add_animation(this, new Animation(animations.get_strip(3),
                            100, 100, 20, 5, this));
                    dh = new DamageHitbox("Finished/PlayerProjectileDown.png",
                            x + 35, y + 79, 42, 30, this, 10, 200, new Velocity(0, 3));
                    stage.add_damage_hitbox(dh);
                    break;
            }
        }
    }

    // A heavy attack that uses a custom image of a rainbow projectile.
    // Uses the same animations as the light attack.
    public void heavy_attack () {
        if (attack_cooldown2 <= 0) {
            DamageHitbox dh;
            attack_cooldown2 = 80;
            switch (orientation) {
                case 0:
                    ab.add_animation(this, new Animation(animations.get_strip(2),
                            100, 100, 20, 5, this));
                    dh = new DamageHitbox("Finished/PlayerProjectile2.png",
                            x + 30, y, 42, 30, this, 30, 200, new Velocity(0, -3));
                    stage.add_damage_hitbox(dh);
                    break;
                case 1:
                    ab.add_animation(this, new Animation(animations.get_strip(1),
                            100, 100, 20, 5, this));
                    dh = new DamageHitbox("Finished/PlayerProjectile2.png",
                            x, y + 45, 42, 30, this, 30, 200, new Velocity(-3, 0));
                    stage.add_damage_hitbox(dh);
                    break;
                case 2:
                    ab.add_animation(this, new Animation(animations.get_strip(0),
                            100, 100, 20, 5, this));
                    dh = new DamageHitbox("Finished/PlayerProjectile2.png",
                            x + 79, y + 45, 42, 30, this, 30, 200, new Velocity(3, 0));
                    stage.add_damage_hitbox(dh);
                    break;
                case 3:
                    ab.add_animation(this, new Animation(animations.get_strip(3),
                            100, 100, 20, 5, this));
                    dh = new DamageHitbox("Finished/PlayerProjectile2.png",
                            x + 30, y + 79, 42, 30, this, 30, 200, new Velocity(0, 3));
                    stage.add_damage_hitbox(dh);
                    break;
            }
        }
    }

    public void stop_y_motion() {
        motion.set_y(0);
    }

    public void stop_x_motion() {
        motion.set_x(0);
    }

    public void refill_jumps() {
        jumps = 2;
    }

    public void reset () {
        stop_x_motion();
        stop_y_motion();
        x = 0;
        y = 300;
        health = 100;
    }
}
