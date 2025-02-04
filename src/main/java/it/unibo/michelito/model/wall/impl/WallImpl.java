package it.unibo.michelito.model.wall.impl;

import it.unibo.michelito.model.wall.api.Wall;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

/**
 * Represents a Wall in the Maze.
 */
public class WallImpl implements Wall {

    final private Position position;

    /**
     * Initializes a Wall at a specified position.
     *
     * @param position the position of this wall (immutable).
     */
    public WallImpl(final Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public HitBox getHitBox() {
        return new HitBoxFactoryImpl().squareHitBox(this.position);
    }

    @Override
    public Type getType() {
        return Type.WALL;
    }
}
