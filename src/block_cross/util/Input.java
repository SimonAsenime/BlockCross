package block_cross.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

// This class manages input. I actually wrote this class for another project but it has come in handy here.

public class Input extends Input_Codes implements KeyListener {

    private ArrayList<Integer> pressed_keys = new ArrayList<>();
    private ArrayList<Integer> down_keys = new ArrayList<>();

    public void keyPressed (KeyEvent e) {
        int key = e.getKeyCode();
        if (down_keys.indexOf(key) == -1) {
            pressed_keys.add(key);
            down_keys.add(key);
        }
    }

    public void keyReleased (KeyEvent e) {
        int key = e.getKeyCode();
        if (pressed_keys.indexOf(key) != -1)
            pressed_keys.remove((Integer) key);
        if (down_keys.indexOf(key) != -1)
            down_keys.remove((Integer) key);
    }

    // UNUSED
    public void keyTyped (KeyEvent e) {}

    // Game Access
    public boolean is_key_pressed (int key) {
        if ((pressed_keys.indexOf(key) != -1)) {
            pressed_keys.remove((Integer) key);
            return true;
        }
        return false;
    }

    public boolean is_key_down (int key) {
        return (down_keys.indexOf(key) != -1);
    }
}