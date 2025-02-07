package it.unibo.michelito.model.door.api;

import it.unibo.michelito.model.modelutil.Updatable;

/**
 * Interface for Door.
 * Every implementation when updated should check if the Player has entered.
 */
public interface Door extends Updatable {
    /**
     * Getter of the door status.
     *
     * @return true if the door is open.
     */
    boolean isOpen();
}
