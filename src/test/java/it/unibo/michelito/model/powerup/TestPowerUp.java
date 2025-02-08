package it.unibo.michelito.model.powerup;

import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.model.powerups.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.model.powerups.impl.PowerUpFactoryImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

final class TestPowerUp {
    private PowerUpFactory factory;
    @BeforeEach
    void setUp() {
        this.factory = new PowerUpFactoryImpl();
    }

    @Test
    void testSpeedPowerUp() {
        int levelNumber = -1;
        final MazeImpl maze = new MazeImpl(levelNumber);
        final Player player = new PlayerImpl(new Position(0, 0));
        final PowerUp speed = this.factory.generateSpeedPowerUp(new Position(5, 0));
        maze.addMazeObject(speed);
        maze.addMazeObject(player);


        player.setDirection(Direction.RIGHT);
        player.update(1, maze);

        player.setDirection(Direction.RIGHT);
        player.update(2, maze);
        assertEquals(new Position(2.1, 0), player.position());
    }

    @Test
    void testRandomPowerUp() {
        final Optional<PowerUp> powerUp = this.factory.generateRandomPowerUp(new Position(3, 3));
        assertInstanceOf(Optional.class, powerUp);

        if (powerUp.isPresent()) {
            System.out.println("powerUp is dropped");
            assertInstanceOf(PowerUp.class, powerUp.get());
        }
    }
}
