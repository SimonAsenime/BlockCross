package block_cross.main;

import block_cross.util.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

// A button made with a much cleaner design in mind than GButton. Used for the Home Screen, Level select, and tutorial.

public class HButton extends JButton implements MouseListener {

    private String display_text;
    private BufferedImage background;
    private Color c = Color.WHITE;
    private int font_size;

    // Uses a custom image in its rendering for the button background.
    public HButton (String text, int x, int y, int fs) {
        display_text = text;
        font_size = fs;
        background = Utilities.load_image("Finished/Button.png");

        setSize(80, 40);
        setLocation(x, y);
        setFocusPainted(false);
        addMouseListener(this);
    }

    // Uses font calculations to position the text in the center of the button.
    // Changes color depending on whether or not the mouse is inside.
    public void paintComponent(Graphics g) {
        int tw = g.getFontMetrics().stringWidth(display_text);
        int th = g.getFontMetrics().getHeight();

        g.setColor(c);
        g.setFont(new Font("ledfont", Font.PLAIN, font_size));
        g.drawImage(background, 0, 0, null);
        g.drawString(display_text, getWidth()/2-tw/2-3, getHeight()/2+th/4);
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
