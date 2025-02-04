package it.unibo.michelito.util.hitbox;

import it.unibo.michelito.util.position.Position;

import java.util.Set;


public interface HitBox {

    Position getCenter();


    boolean collision(HitBox hitBox);
}
