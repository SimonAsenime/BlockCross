package block_cross.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// A button used in the game menu. The design is not particularly good as it was done very early in.

public class GButton extends JButton implements MouseListener {

    private String display_text;
    private int font_size;
    private Color c = Color.WHITE;

    public GButton (String text, int x, int y, int fs) {
        display_text = text;
        font_size = fs;

        setLocation(x,y);
        setSize(80, 40);
        setFocusPainted(false);
        setFocusable(false);
        addMouseListener(this);
    }

    // Has no Background, Color changes depending on whether the mouse is inside the button or not.
    public void paint(Graphics g) {
        g.setColor(c);
        g.setFont(new Font("ledfont", Font.PLAIN, font_size));
        g.drawString(display_text,0,getHeight()/2);
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {
        c = Color.GREEN;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        c = Color.WHITE;
    }
}
