package block_cross.main;

// A necessary interface for polymorphism of cards to access update method after switching without excessive if statements.
public interface Selectable {
    // This method is found in all the classes that implement this interface but only the stages
    // actually give a definition to this function.
    void update ();
}
