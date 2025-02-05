package it.unibo.michelito.model.box.impl;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

/**
 * Represents a Box in the Maze.
 */
public record BoxImpl(Position position) implements Box {

    @Override
    public final HitBox getHitBox() {
        return new HitBoxFactoryImpl().squareHitBox(this.position);
    }

    @Override
    public final Type getType() {
        return Type.BOX;
    }
}
