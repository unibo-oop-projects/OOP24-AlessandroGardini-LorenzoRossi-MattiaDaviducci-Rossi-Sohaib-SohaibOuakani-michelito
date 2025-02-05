package it.unibo.michelito.model.player.api;

/**
 * interfce for commands that can be executed by the player.
 */
public interface PlayerCommand {
    /**
     * method that executes the command.
     * @param player the player who has to execute the command.
     */
    void execute(Player player);
}
