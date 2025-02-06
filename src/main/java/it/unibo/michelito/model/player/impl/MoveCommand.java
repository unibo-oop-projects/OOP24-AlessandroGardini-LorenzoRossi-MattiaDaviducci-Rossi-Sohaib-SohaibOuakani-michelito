package it.unibo.michelito.model.player.impl;

import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.api.PlayerCommand;
import it.unibo.michelito.util.Direction;

/**
 * Class that implements {@link PlayerCommand} to set the {@link Direction} of the next move of the {@link Player}.
 */
public class MoveCommand implements PlayerCommand {
    private final Direction direction;

    /**
     * Constructor for MoveCommand.
     * @param direction is the {@link Direction} of the next player move.
     */
    public MoveCommand(final Direction direction) {
        this.direction = direction;
    }

    @Override
    public final void execute(final Player player) {
        player.setDirection(this.direction);
    }
}
