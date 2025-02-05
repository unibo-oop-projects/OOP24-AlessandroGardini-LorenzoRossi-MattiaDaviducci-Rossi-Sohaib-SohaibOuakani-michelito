package it.unibo.michelito.model.wall.impl;

import it.unibo.michelito.model.wall.api.Wall;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

/**
 * Implementation of the {@link Wall} interface.
 *
 * @param position is the position that the Wall will have.
 */
public record WallImpl(Position position) implements Wall {

    @Override
    public HitBox getHitBox() {
        return new HitBoxFactoryImpl().squareHitBox(this.position);
    }

    @Override
    public Type getType() {
        return Type.WALL;
    }
}
