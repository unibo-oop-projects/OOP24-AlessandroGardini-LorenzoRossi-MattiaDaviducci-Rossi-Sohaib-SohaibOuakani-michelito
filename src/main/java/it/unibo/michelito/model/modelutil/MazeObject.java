package it.unibo.michelito.model.modelutil;

import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.HitBox;
import it.unibo.michelito.util.Position;

/**
 * Interface representing every object in the Maze.
 */
public interface MazeObject {
    /**
     * Get the position of the MazeObject.
     *
     * @return the position of the MazeObject.
     */
    Position getPosition();

    /**
     * Get the HitBox of the MazeObject.
     *
     * @return the HitBox of the MazeObject.
     */
    HitBox getHitBox();

    /**
     * Get the Type of the MazeObject.
     *
     * @return the Type of the MazeObject.
     */
    Type getType();
}
