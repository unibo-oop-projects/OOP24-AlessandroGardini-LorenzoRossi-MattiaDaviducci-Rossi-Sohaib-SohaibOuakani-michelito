package it.unibo.michelito.model.player.impl;

import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.api.PlayerCommand;
import it.unibo.michelito.util.Direction;

public class MoveCommand implements PlayerCommand {
    private final Direction direction;

    public MoveCommand(final Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(Player player) {

    }
}
