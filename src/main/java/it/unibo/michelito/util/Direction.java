package it.unibo.michelito.util;

public enum Direction {
    UP(new Position(0, -1)),

    DOWN(new Position(0, 1)),

    LEFT(new Position(-1, 0)),

    RIGHT(new Position(1, 0)),

    NONE(new Position(0, 0));

    private final Position position;

    Direction(final Position position) {
        this.position = position;
    }

    public Position toPosition() {
        return new Position(position.x(), position.y());
    }
}
