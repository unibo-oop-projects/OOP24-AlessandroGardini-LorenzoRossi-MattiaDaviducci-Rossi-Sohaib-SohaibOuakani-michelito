package it.unibo.michelito.model.powerups.impl;

import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.api.HitBoxFactory;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

abstract class AbsPowerUp implements PowerUp {
    private final Position position;
    private final HitBox hitBox;

    AbsPowerUp(final Position position) {
        this.position = position;
        this.hitBox = createHitBox();
    }

    private HitBox createHitBox() {
        final HitBoxFactory factory = new HitBoxFactoryImpl();
        return factory.squareHitBox(this.position);
    }

   @Override
   public HitBox getHitBox() {
        return this.hitBox;
   }

   @Override
    public Position position() {
        return this.position;
   }

   @Override
    public Type getType() {
        return Type.POWERUP;
   }

   @Override
    public abstract void applyEffect(Player player);
}
