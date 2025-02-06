package it.unibo.michelito.util;

public enum Direction {
    /**
     * Up {@link Direction}.
     */
    UP(new Position(0, -1)),

    /**
     * Down {@link Direction}.
     */
    DOWN(new Position(0, 1)),

    /**
     * Left {@link Direction}.
     */
    LEFT(new Position(-1, 0)),

    /**
     * Right {@link Direction}.
     */
    RIGHT(new Position(1, 0)),

    /**
     * A {@link Direction} to represent standing still.
     */
    NONE(new Position(0, 0));

    private final Position position;

    Direction(final Position position) {
        this.position = position;
    }

    /**
     *
     * @return
     */
    public Position toPosition() {
        return new Position(position.x(), position.y());
    }
}
