package it.unibo.michelito.model.bomb.api;

import java.util.Arrays;
import java.util.Random;

/**
 * Represents the type of a bomb.
 */
public enum BombType {
    /**
     * A standard bomb.
     */
    STANDARD,

    /**
     * A bomb that explodes in a cross shape.
     */
    NUKE,

    /**
     * A bomb that explodes in a cross shape and has a longer range.
     */
    PASS_THROUGH,

    /**
     * A bomb that explodes in a cross shape and has a longer range.
     */
    LONG;

    /**
     * Get a random bomb type.
     *
     * @return A random bomb type.
     */
    public static BombType getRandomType() {
        final var types = Arrays.asList(values());
        return types.get(new Random().nextInt(types.size()));
    }
}
