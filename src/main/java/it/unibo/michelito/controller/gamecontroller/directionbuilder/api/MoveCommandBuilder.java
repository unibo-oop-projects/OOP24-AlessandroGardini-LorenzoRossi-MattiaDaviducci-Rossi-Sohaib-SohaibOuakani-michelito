package it.unibo.michelito.controller.gamecontroller.directionbuilder.api;

import it.unibo.michelito.controller.playercommand.impl.MoveCommand;
import it.unibo.michelito.util.Direction;

public interface MoveCommandBuilder {
    MoveCommandBuilder addDirection(Direction direction);

    MoveCommand build();
}
