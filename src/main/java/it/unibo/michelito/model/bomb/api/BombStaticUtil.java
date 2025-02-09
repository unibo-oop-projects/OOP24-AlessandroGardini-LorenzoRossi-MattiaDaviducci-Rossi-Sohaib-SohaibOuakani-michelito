package it.unibo.michelito.model.bomb.api;

public final class BombStaticUtil {
    private BombStaticUtil() {
    }

    public static boolean isFlamePassThrough(BombType bombType) {
        return switch (bombType) {
            case NUKE, PASS_THROUGH -> true;
            default -> false;
        };
    }

    public static int getFlameRange(BombType bombType) {
        return switch (bombType) {
            case NUKE -> 7;
            case LONG -> 5;
            case PASS_THROUGH -> 3;
            default -> 1;
        };
    }
}
