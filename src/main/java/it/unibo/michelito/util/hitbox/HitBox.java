package it.unibo.michelito.util.hitbox;

import it.unibo.michelito.util.Position;


public interface HitBox {

    Position getCenter();


    boolean collision(HitBox hitBox);
}
