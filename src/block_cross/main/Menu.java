package block_cross.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// The menu used in the actual stages of the game. Its a custom made swing component.

public class Menu extends JComponent implements ActionListener {

    // GButtons
    private GButton b1;
    private GButton b2;
    private Flag flag;
    private Stage parent;

    // Does not utilize a layout.
    public Menu (Flag f, int x, int y, Stage p) {
        this.setPreferredSize(new Dimension(80, 80));
        this.setEnabled(false);
        this.setLocation(x, y);
        setLayout(null);

        flag = f;
        parent = p;
        b1 = new GButton("Continue", 0, 0, 16);
        b2 = new GButton("Quit", 0, 40, 16);

        this.add(b1);
        this.add(b2);

        b1.setActionCommand("Continue");
        b2.setActionCommand("Quit");
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    // The background is a slightly transparent black square.
    public void paint(Graphics g) {
        g.setColor(new Color(0, 0, 0, 120));
        g.fillRect(0, 0, getWidth(), getHeight());
        paintChildren(g);
    }

    // Button Handler
    public void actionPerformed(ActionEvent e) {
        if (isEnabled()) {
            if ("Quit".equals(e.getActionCommand())) {
                flag.n = 1;
                flag.changed = true;
                parent.reset();
            }
            this.setEnabled(false);
        }
    }
}
