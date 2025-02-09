package it.unibo.michelito.model.powerup;

import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.model.powerups.impl.BombLimitPowerUp;
import it.unibo.michelito.model.powerups.impl.BombTypePowerUp;
import it.unibo.michelito.model.powerups.impl.SpeedPowerUp;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

final class TestPowerUp {
    Player player;
    Maze maze;

    @BeforeEach
    void setUp() {
        int levelNumber = -1;
        this.maze = new MazeImpl(levelNumber);
    }

    @Test
    void testSpeedPowerUp() {
        final Position position = new Position(0, 0);
        final PowerUp powerUp = new SpeedPowerUp(position);
        final PlayerImpl player = new PlayerImpl(maze.getPlayer().position()); /*(6, 6)*/
        powerUp.applyEffect(player);

        player.setDirection(Direction.RIGHT);
        player.update(1, new MazeImpl(-1));
        assertEquals(new Position(7.1, 6), player.position());
    }

    @Test
    void testBombLimitPowerUp() {
        final Position position = new Position(6, 6);
        final PowerUp powerUp = new BombLimitPowerUp(position);
        final PlayerImpl player = new PlayerImpl(position);
        powerUp.applyEffect(player);
        Maze maze = new MazeImpl(-1);

        player.notifyToPlace();
        player.update(1, maze);
        player.notifyToPlace();
        player.update(1, maze);
        assertEquals(2, maze.getBombs().size());
    }

    @Test
    void testBombTypePowerUp() {
        final PlayerImpl player = new PlayerImpl(new Position(0, 0));
        final PowerUp bombTypePowerUp = new BombTypePowerUp(new Position(0, 0));

        assertEquals(BombType.STANDARD, player.getBombType());
        bombTypePowerUp.applyEffect(player);
        assertNotEquals(BombType.STANDARD, player.getBombType());
    }
}
