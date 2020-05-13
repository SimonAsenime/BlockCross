package block_cross.main;

import block_cross.objects.DamageHitbox;

// A necessary interface for the stages to use polymorphism.
// Handles resetting the stage as well introducing damaging block_cross.main.objects.

public interface Stage {
    // All of these methods are meant to be called from classes that do not implement this interface.

    // Adds damaging block_cross.main.objects to the list for rendering and collisions.
    void add_damage_hitbox (DamageHitbox dh);
    // Removes damaging block_cross.main.objects from the list. Meant to be called from other classes.
    void remove_damage_hitbox (DamageHitbox dh);
    // Resets the stage.
    void reset ();
}
