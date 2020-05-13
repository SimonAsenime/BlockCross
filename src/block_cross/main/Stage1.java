package block_cross.main;

import block_cross.objects.Boss1;
import block_cross.objects.DamageHitbox;
import block_cross.objects.Game_Object;
import block_cross.objects.Player;
import block_cross.util.AnimationBuffer;
import block_cross.util.Input;
import block_cross.util.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

// The first stage of the game.
// Most of the code is the same in this one as in stage 2, but they have to be kept separate as there are small changes
// in big portions of the code that need to be hardcoded since interfaces cannot have defined methods.

public class Stage1 extends JPanel implements Selectable, Stage {

    // There are many moving parts to this class. It features images, game block_cross.main.objects, and animations.

    private BufferedImage sky;
    private Input input;
    private Menu menu;
    private Game_Object ground;
    private ArrayList<DamageHitbox> attacks = new ArrayList<>();
    private AnimationBuffer ab;
    Player player;
    Boss1 boss;
    Flag flag;


    // This stage uses a layout for the sake of centering the menu.
    // Everything else is rendered using x and y coordinates.
    public Stage1 (Flag f, Input i, AnimationBuffer ab) {
        flag = f;
        input = i;
        this.ab = ab;

        setLayout(new GridBagLayout());

        menu = new Menu(flag, 800, 600, this);
        this.add(menu, new GridBagConstraints());

        ground = new Game_Object(0, 400, 800, 200,
                "Finished/Ground.png");

        sky = Utilities.load_image("Finished/Sky.png");

        // The instantiation of the boss of this stage. Is its own class and has custom animations.
        // The boss of this stage relies on physical attacks.
        boss = new Boss1(640, 253, 160, 147, "Finished/Boss1.png", ab, this);
    }

    public void paint (Graphics g) {
        // Initial Drawing
        g.drawImage(sky, 0, 0, null);
        ground.draw(g);
        boss.draw(g);
        player.draw(g);
        for (DamageHitbox dh : attacks)
            dh.draw(g);

        // Drawing HealthBars
        Graphics2D g2d = (Graphics2D)g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Player Health: "+player.get_health(), 10, 520);
        g2d.setColor(Color.RED);
        g2d.fillRoundRect(10, 530, 200, 50, 10, 10);
        g2d.setColor(Color.GREEN);
        g2d.fillRoundRect(10, 530, player.get_health()*2, 50, 10, 10);
        g2d.setColor(Color.WHITE);
        g2d.drawString("Boss Health: "+boss.get_health(), 700, 520);
        g2d.setColor(Color.RED);
        g2d.fillRoundRect(490, 530, 300, 50, 10, 10);
        g2d.setColor(Color.GREEN);
        g2d.fillRoundRect(490, 530, (int)((float)boss.get_health()/500*300), 50, 10, 10);
        // Win or Lose text.
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Times New Roman", Font.PLAIN, 18));
        if (player.get_health() <= 0) {
            g2d.drawString("YOU LOSE.", 365, 50);
            this.add(menu, new GridBagConstraints());
            menu.setEnabled(true);
        }
        if (boss.get_health() <= 0) {
            g2d.drawString("YOU WIN!!!", 365, 50);
            this.add(menu, new GridBagConstraints());
            menu.setEnabled(true);
        }
        // Painting the menu if it's active.
        paintChildren(g);
    }

    // Add the player object to the stage and boss.
    public void set_player (Player p) {
        player = p;
        boss.set_player(p);
    }

    // See interface
    public void add_damage_hitbox (DamageHitbox dh) {
        attacks.add(dh);
    }

    // See interface.
    public void remove_damage_hitbox (DamageHitbox dh) {
        attacks.remove(dh);
    }

    // Quite Hardcoded due to their being a small number of game_objects.
    // It's less clunky this way than writing general code.
    public void update () {
        // Animation buffer rendering
        ab.update();
        // DamageHitbox management. Delete after lifespan depletes and do collisions.
        for (int i = 0; i < attacks.size(); i++) {
            if (attacks.get(i).is_dead())
                attacks.remove(i);
        }
        for (int i = 0; i < attacks.size(); i++) {
            if (attacks.get(i).get_hit().is_colliding(player.get_hit()))
                if (attacks.get(i).get_id() != player) {
                    player.reduce_health(attacks.get(i).get_damage());
                    attacks.remove(i);
                }
        }
        for (int i = 0; i < attacks.size(); i++) {
            if (attacks.get(i).get_hit().is_colliding(boss.get_hit()))
                if (attacks.get(i).get_id() != boss) {
                    boss.reduce_health(attacks.get(i).get_damage());
                    attacks.remove(i);
                }
        }

        // Player boundaries and Collisions
        if (player.get_hit().is_colliding(ground.get_hit())) {
            player.stop_y_motion();
            player.set_y(300);
            player.refill_jumps();
        }
        if (player.get_x() < 0) {
            player.set_x(0);
            player.stop_x_motion();
        } else if (player.get_x()+100 > 800) {
            player.set_x(700);
            player.stop_x_motion();
        }
        // Boss collisions
        if (boss.get_hit().is_colliding(ground.get_hit())) {
            boss.stop_y_motion();
            boss.set_y(253);
            boss.end_jump();
        }
        // Update player and boss
        player.update();
        boss.update();
        for (DamageHitbox dh : attacks)
            dh.update();

        // Menu management
        if (!menu.isEnabled()) {
            this.remove(menu);
        }
        if (input.is_key_pressed(input.KEY_ESCAPE)) {
            if (menu.isEnabled()) {
                menu.setEnabled(false);
                this.remove(menu);
            }
            else {
                this.add(menu, new GridBagConstraints());
                menu.setEnabled(true);
            }
        }
    }

    // See interface
    public void reset () {
        player.reset();
        boss.reset();
    }
}
