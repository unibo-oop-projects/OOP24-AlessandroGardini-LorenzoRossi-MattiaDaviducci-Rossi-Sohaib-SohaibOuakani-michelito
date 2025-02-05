package it.unibo.michelito.model.box.impl;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

/**
 * Implementation of the {@link Box} interface.
 *
 * @param position is the position that the Box will have.
 */
public record BoxImpl(Position position) implements Box {

    @Override
    public HitBox getHitBox() {
        return new HitBoxFactoryImpl().squareHitBox(this.position);
    }

    @Override
    public Type getType() {
        return Type.BOX;
    }
}
