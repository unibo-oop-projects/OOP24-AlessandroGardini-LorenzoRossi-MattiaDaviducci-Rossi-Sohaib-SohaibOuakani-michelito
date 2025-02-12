package it.unibo.michelito.controller.gamecontroller.directionbuilder.api;

import it.unibo.michelito.controller.playercommand.impl.MoveCommand;
import it.unibo.michelito.util.Direction;
/**
 * Interface that models a builder for a {@link MoveCommand}
 */
public interface MoveCommandBuilder {
    MoveCommandBuilder addDirection(Direction direction);

    MoveCommand build();
}
