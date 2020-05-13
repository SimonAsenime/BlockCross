package block_cross.util;

import block_cross.objects.Live_Object;

import java.awt.image.BufferedImage;

// The animation class. This is part of the backbone of the graphics of this project.
// This class has all the information about a particular animation.

public class Animation {

    // The length of the animation in frames.
    private int length;
    // The frame gap between switching images.
    private int frame_gap;
    // How many frames have passed since the start of the animation.
    private int frame_count = 0;
    // Which image in the animation are we at.
    private int image_pos = 1;
    // Has the animation ended.
    private boolean finished = false;
    // The owner of the animation.
    private Live_Object id;
    // The list of images.
    private SpriteSheet frames;

    public Animation (SpriteSheet ss, int w, int h, int l, int f, Live_Object lo) {
        frames = ss;
        length = l;
        frame_gap = f;
        id = lo;
    }

    // This method handles reporting the correct image to the AnimationBuffer for rendering and figuring out when
    // the animation has finished.
    public BufferedImage update () {
        if (frame_count < length) {
            frame_count++;
            if (frame_count >= frame_gap*image_pos && image_pos < frames.length()) {
                image_pos++;
            }
        }
        if (frame_count == length)
            finished = true;
       return frames.get_image(image_pos-1, 0);
    }

    public boolean is_finished () {
        return finished;
    }

    public Live_Object get_id () {
        return id;
    }
}
