package block_cross.main;

import block_cross.util.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

// The Level Select screen that gives access to the tutorial, as well as the rest of the stages.

public class LevelScreen extends JPanel implements ActionListener, Selectable {

    private BufferedImage background;
    private BufferedImage title;
    private BufferedImage sub_title;
    private HButton back;
    private HButton tutorial;
    private HButton stage_1;
    private HButton stage_2;
    Flag flag;

    // Uses the same background and titling as HomeScreen with more HButtons.
    // Much of the constructor code is the same as HomeScreen.
    public LevelScreen (Flag f) {
        flag = f;
        this.setPreferredSize(new Dimension(800,600));
        setLayout(null);

        back = new HButton("<", 50, 400, 30);
        tutorial = new HButton("Tutorial", 100, 150, 16);
        stage_1 = new HButton("Stage 1", 200, 150, 16);
        stage_2 = new HButton("Stage 2", 300, 150, 16);

        back.setActionCommand("Back");
        tutorial.setActionCommand("Tutorial");
        stage_1.setActionCommand("Stage 1");
        stage_2.setActionCommand("Stage 2");

        back.addActionListener(this);
        tutorial.addActionListener(this);
        stage_1.addActionListener(this);
        stage_2.addActionListener(this);

        this.add(back);
        this.add(tutorial);
        this.add(stage_1);
        this.add(stage_2);

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

    // Button Handler
    public void actionPerformed (ActionEvent e) {
        if ("Back".equals(e.getActionCommand())) {
            flag.n = 0;
            flag.changed = true;
        } else if ("Tutorial".equals(e.getActionCommand())) {
            flag.n = 2;
            flag.changed = true;
        } else if ("Stage 1".equals(e.getActionCommand())) {
            flag.n = 3;
            flag.changed = true;
        } else if ("Stage 2".equals(e.getActionCommand())) {
            flag.n = 4;
            flag.changed = true;
        }
    }

    public void update () {}

}
