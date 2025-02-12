package it.unibo.michelito.model.bomb.api;

import java.util.Arrays;
import java.util.Random;

public enum BombType {
    STANDARD,
    NUKE,
    PASS_THROUGH,
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
