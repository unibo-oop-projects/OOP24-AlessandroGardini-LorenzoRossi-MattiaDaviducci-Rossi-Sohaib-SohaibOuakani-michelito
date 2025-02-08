package it.unibo.michelito.model.bomb.api;

import java.util.Arrays;
import java.util.Random;

public enum BombType {
    STANDARD,
    NUKE,
    PASS_THROUGH,
    LONG;

    public static BombType getRandomType() {
        var types = Arrays.asList(values());
        return types.get(new Random().nextInt(types.size()));
    }
}
