package it.unibo.michelito.model.bomb.api;

public final class BombStaticUtil {
    final static int BASE_RANGE = 1;
    final static int PASS_THROUGH_RANGE = 1;
    final static int LONG_RANGE = 1;
    final static int NUKE_RANGE = 1;

    private BombStaticUtil() {
    }

    public static boolean isFlamePassThrough(final BombType bombType) {
        return switch (bombType) {
            case NUKE, PASS_THROUGH -> true;
            default -> false;
        };
    }

    public static int getFlameRange(final BombType bombType) {
        return switch (bombType) {
            case NUKE -> NUKE_RANGE;
            case LONG -> LONG_RANGE;
            case PASS_THROUGH -> PASS_THROUGH_RANGE;
            default -> BASE_RANGE;
        };
    }
}
