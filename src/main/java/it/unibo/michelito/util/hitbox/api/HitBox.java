package it.unibo.michelito.util.hitbox.api;

import it.unibo.michelito.util.Position;

/**
 *Interface to manage the dimension of the objet in the maze to avoid to overlap in space.
 */
public interface HitBox {
    /**
     * @return the center of the Hitbox.
     */
    Position getCenter();

    /**
     * @return the Hitbox.
     */
    HitBox getHitBox();

    /**
     * @param hitBox the hitbox with which verify collision.
     *
     * @return if two hitbox are colliding eachother.
     */
    boolean collision(HitBox hitBox);

    /**
     * @param position the posizion with which verify overlapping whith the object
     *
     * @return if a position is inside the Hitbox.
     */
    boolean inner(Position position);
}
