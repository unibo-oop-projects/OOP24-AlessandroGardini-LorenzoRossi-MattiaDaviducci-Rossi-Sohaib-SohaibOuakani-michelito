package it.unibo.michelito.model.blanckspace.impl;

import it.unibo.michelito.model.blanckspace.api.BlankSpace;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;

public record BlankSpaceImpl(Position position) implements BlankSpace {
    @Override
    public HitBox getHitBox() {
        return new HitBoxFactoryImpl().squareHitBox(position);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.BLANK_SPACE;
    }
}
