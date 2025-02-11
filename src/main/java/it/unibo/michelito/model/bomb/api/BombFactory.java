package it.unibo.michelito.model.bomb.api;

import it.unibo.michelito.util.Position;

/**
 * A factory for creating bombs.
 */
public interface BombFactory {
    /**
     * Creates a standard bomb.
     *
     * @param position The position of the bomb.
     * @return The {@link Bomb}.
     */
    Bomb createStandardBomb(Position position);

    /**
     * Creates a nuke bomb.
     *
     * @param position The position of the bomb.
     * @return The {@link Bomb}.
     */
    Bomb createNukeBomb(Position position);

    /**
     * Creates a pass-through bomb.
     *
     * @param position The position of the bomb.
     * @return The {@link Bomb}.
     */
    Bomb createPassThroughBomb(Position position);

    /**
     * Creates a long bomb.
     *
     * @param position The position of the bomb.
     * @return The {@link Bomb}.
     */
    Bomb createLongBomb(Position position);
}
