package block_cross.main;

import block_cross.objects.Player;
import block_cross.util.AnimationBuffer;
import block_cross.util.Input;

import javax.swing.*;
import java.awt.*;

// The block_cross.main class. Runs all the code and coordinates the game.
// Much of this game was hardcoded simply because it was easier and less lines of code than a general solution.
// All art in this game was drawn by me as well as all of the animations.

public class Main {
    public static void main(String[] args) throws InterruptedException {

        // Strings for CardLayout.
        final String home_string = "Card of the HomeScreen.";
        final String level_s_string = "Card of the Level Select Screen.";
        final String tutorial_string = "Card of the Tutorial Screen.";
        final String stage_1_string = "Card of Stage 1.";
        final String stage_2_string = "Card of Stage 2";
        // The currently showing screen.
        Selectable current_screen;

        // Flag for switching.
        Flag flag = new Flag(0);
        // Input class for managing input better than just extending KeyListener.
        Input input = new Input();

        // Frame creation.
        JFrame frame = new JFrame("BlockCross");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,600);

        // The panel that handles the different screens as cards using CardLayout.
        JPanel cards = new JPanel(new CardLayout());
        cards.addKeyListener(input);
        cards.setFocusable(true);
        cards.setPreferredSize(new Dimension( 800, 600));
        frame.add(cards);

        // The Animation Buffer for rendering animations during the game.
        AnimationBuffer ab = new AnimationBuffer();

        // All of the different potentials screens or cards the player will see.
        HomeScreen hs = new HomeScreen(flag);
        LevelScreen ls = new LevelScreen(flag);
        Tutorial t = new Tutorial(flag);
        Stage1 s1 = new Stage1(flag, input, ab);
        Stage2 s2 = new Stage2(flag,input, ab);
        current_screen = hs;

        // The player object. It needs to be given to the stages for certain code run in the stages.
        Player player = new Player(0, 300, 100, 100,
                "Finished/Player.png", ab, input, s1);
        s1.set_player(player);
        s2.set_player(player);

        // Card initialization.
        cards.add(hs, home_string);
        cards.add(ls, level_s_string);
        cards.add(t, tutorial_string);
        cards.add(s1, stage_1_string);
        cards.add(s2, stage_2_string);
        CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, home_string);

        // Show the frame.
        frame.pack();
        frame.setVisible(true);

        // Main loop
        while(true) {
            // Controls switching between cards to show the player different things.
            if (flag.changed == true) {
                switch(flag.n) {
                    case 0:
                        cl.show(cards, home_string);
                        current_screen = hs;
                        flag.changed = false;
                        break;
                    case 1:
                        cl.show(cards, level_s_string);
                        current_screen = ls;
                        flag.changed = false;
                        break;
                    case 2:
                        cl.show(cards, tutorial_string);
                        current_screen = t;
                        flag.changed = false;
                        break;
                    case 3:
                        cl.show(cards, stage_1_string);
                        current_screen = s1;
                        flag.changed = false;
                        player.set_stage(s1);
                        break;
                    case 4:
                        cl.show(cards, stage_2_string);
                        current_screen = s2;
                        flag.changed = false;
                        player.set_stage(s2);
                        break;
                }
            }
            // Main update and rendering.
            current_screen.update();
            frame.repaint();
            // Sleep value for approximately 60 fps at max.
            Thread.sleep(17);
        }
    }
}
