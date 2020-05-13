package block_cross.util;

import block_cross.objects.Live_Object;

import java.util.ArrayList;

// A small but vital class to the graphics of this game.

public class AnimationBuffer {

    // This list of animations and their owners.
    private ArrayList<Animation> animations = new ArrayList<>();
    private ArrayList<Live_Object> objects = new ArrayList<>();

    public AnimationBuffer () {}

    // Add and animation at runtime to be rendered.
    // This method prevents an object from having more than one animation queued up at a time to prevent rendering bugs.
    public void add_animation (Live_Object lo, Animation a) {
        if (!objects.contains(lo)) {
            objects.add(lo);
            animations.add(a);
        }
    }

    // This method handles pulling content from the AnimationBuffer to outside classes.
    public void update () {
        for (int i = animations.size()-1; i > -1; i--) {
            if (animations.get(i).is_finished()) {
                animations.get(i).get_id().change_image(animations.get(i).get_id().get_default_image());
                objects.remove(animations.get(i).get_id());
                animations.remove(animations.get(i));

            }
        }
        for (Animation a : animations) {
            a.get_id().change_image(a.update());
        }
    }
}
