package it.unibo.michelito.model.box.impl;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

/**
 * Represents a Box in the Maze.
 */
public class BoxImpl implements Box {

    final private Position position;

    /**
     * Initializes a Box at a specified position.
     *
     * @param position the position of this box (immutable).
     */
    public BoxImpl(final Position position) {
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
        return Type.BOX;
    }
}
