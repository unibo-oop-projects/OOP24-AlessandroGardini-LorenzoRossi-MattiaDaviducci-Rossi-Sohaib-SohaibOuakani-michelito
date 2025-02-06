package it.unibo.michelito.model.modelutil;

import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.model.maze.api.Maze;

/**
 * Interface representing every object in the {@link Maze}.
 */
public interface MazeObject {
    /**
     * Get the {@link Position} of the MazeObject.
     *
     * @return the {@link Position} of the MazeObject.
     */
    Position position();

    /**
     * Get the {@link HitBox} of the MazeObject.
     *
     * @return the {@link HitBox} of the MazeObject.
     */
    HitBox getHitBox();

    /**
     * Get the {@link Type} of the MazeObject.
     *
     * @return the {@link Type} of the MazeObject.
     */
    Type getType();
}
