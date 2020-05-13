package block_cross.main;

import block_cross.util.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class Tutorial extends JPanel implements ActionListener, Selectable {

    private BufferedImage background;
    private BufferedImage title;
    private BufferedImage sub_title;
    private HButton back;
    Flag flag;

    // Much of the constructor code is the same as HomeScreen.
    public Tutorial (Flag f) {
        flag = f;

        this.setPreferredSize(new Dimension(800,600));
        setLayout(null);

        back = new HButton("<", 50, 550, 30);
        back.setActionCommand("Back");
        back.addActionListener(this);

        this.add(back);

        background = Utilities.load_image("Finished/Background.png");
        title = Utilities.load_image("Finished/Title.png");
        sub_title = Utilities.load_image("Finished/SubTitle.png");
    }

    public void paint (Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(title, getWidth()/2-484/2, 0, null);
        g.drawImage(sub_title, getWidth()/2-356/2, 100, null);
        g.setColor(Color.WHITE);
        paintChildren(g);
        draw_text(g);
    }

    // Tutorial text is rendered and tells the player the controls as well as how to play the game and the goal.
    public void draw_text (Graphics g) {
        g.drawString("Controls: ", 300, 260);
        g.drawString("Q: Light Attack", 300, 280);
        g.drawString("WASD: Movement", 300, 300);
        g.drawString("E: Heavy Attack", 300, 320);
        g.drawString("Esc: Open menu", 300, 340);
        g.drawString("Goal:", 300, 360);
        g.drawString("This is a boss rusher game.", 300, 380);
        g.drawString("Challenge Stage 1 and 2 and.", 300, 400);
        g.drawString("put your skills to the test.", 300, 420);
    }


    // Button Handler
    public void actionPerformed (ActionEvent e) {
        if ("Back".equals(e.getActionCommand())) {
            flag.n = 1;
            flag.changed = true;
        }
    }

    public void update() {}
}
