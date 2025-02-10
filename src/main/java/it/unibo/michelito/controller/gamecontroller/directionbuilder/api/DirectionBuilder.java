package it.unibo.michelito.controller.gamecontroller.directionbuilder.api;

import it.unibo.michelito.util.Direction;

public interface DirectionBuilder {
    DirectionBuilder addDirection(Direction direction);

    Direction build();
}
