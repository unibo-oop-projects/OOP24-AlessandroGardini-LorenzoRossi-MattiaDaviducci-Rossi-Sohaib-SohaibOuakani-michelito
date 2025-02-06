package it.unibo.michelito.util.hitbox.api;

import it.unibo.michelito.util.Position;

/**
 *Interface to manage the dimension of the objet in the maze to avoid to overlap in space.
 */
public interface HitBox {
    /**
     * @return the center of the hitbox.
     */
    Position getCenter();

    /**
     * @return the hitbox.
     */
    HitBox getHitBox();

    /**
     * @param hitBox the hitbox with which verify collision.
     *
     * @return if two hitbox are colliding each other.
     */
    boolean collision(HitBox hitBox);

    /**
     *
     * @return
     */
    double getWidth();

    /**
     *
     * @return
     */
    double getHeight();
}
