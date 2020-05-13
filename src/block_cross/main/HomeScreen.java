package block_cross.main;

import block_cross.util.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

// The very first screen that will be presented to the player.

public class HomeScreen extends JPanel implements ActionListener, Selectable {

    private BufferedImage background;
    private BufferedImage title;
    private BufferedImage sub_title;
    private HButton play;
    private HButton quit;
    Flag flag;

    /*
     Uses a custom background, title, and subtitle in its design.
     Much of this is hardcoded into the class to avoid doing in Main.
     Has no layout.
    */
    public HomeScreen (Flag f) {
        this.setPreferredSize(new Dimension(800,600));
        setLayout(null);

        flag = f;
        // Setting up buttons
        play = new HButton("Play", 360, 350, 16);
        play.setActionCommand("Play");
        quit = new HButton("Quit", 360, 400, 16);
        quit.setActionCommand("Quit");

        this.add(play);
        this.add(quit);

        play.addActionListener(this);
        quit.addActionListener(this);

        // Image loading
        background = Utilities.load_image("Finished/Background.png");
        title = Utilities.load_image("Finished/Title.png");
        sub_title = Utilities.load_image("Finished/SubTitle.png");
    }

    public void paint (Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(title, getWidth()/2-484/2, 0, null);
        g.drawImage(sub_title, getWidth()/2-356/2, 100, null);
        paintChildren(g);
    }

    // The button handler.
    public void actionPerformed(ActionEvent e) {
        if ("Play".equals(e.getActionCommand())) {
            flag.n = 1;
            flag.changed = true;
        }
        if ("Quit".equals(e.getActionCommand())) {
            System.exit(0);
        }
    }

    public void update () {}
}
