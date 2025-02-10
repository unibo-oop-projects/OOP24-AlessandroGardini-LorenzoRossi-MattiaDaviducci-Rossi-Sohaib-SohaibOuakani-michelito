package it.unibo.michelito.model.enemy.api.ai;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;

/**
 *The base interface of movement for enemy.
 */
public interface Movement {
    /**
     * @return the direction where the enemy will try to go.
     */
    void move(final Maze maze, final long time);

    MovementType getMovementType();

    Position getPosition();

    void setPosition(final Position position);
}
