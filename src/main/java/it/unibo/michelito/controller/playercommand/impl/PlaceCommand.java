package it.unibo.michelito.controller.playercommand.impl;

import it.unibo.michelito.controller.playercommand.api.PlayerCommand;
import it.unibo.michelito.model.player.api.Player;

public class PlaceCommand implements PlayerCommand {
    @Override
    public void execute(Player player) {
        player.notifyToPlace();
    }
}
