package it.unibo.michelito.util.hitbox;

import it.unibo.michelito.util.position.Position;

import java.awt.*;

public interface HitBox {

    public Position getCenter();



    public boolean collision(HitBox hitBox);
}
