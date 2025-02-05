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

    private final Position position;

    /**
     * Initializes a Wall at a specified position.
     *
     * @param position the position of this wall (immutable).
     */
    public WallImpl(final Position position) {
        this.position = position;
    }

    @Override
    public final Position getPosition() {
        return this.position;
    }

    @Override
    public final HitBox getHitBox() {
        return new HitBoxFactoryImpl().squareHitBox(this.position);
    }

    @Override
    public final Type getType() {
        return Type.WALL;
    }
}
