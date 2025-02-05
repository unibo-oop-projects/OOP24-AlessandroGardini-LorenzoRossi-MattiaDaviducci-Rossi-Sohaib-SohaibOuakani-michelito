package it.unibo.michelito.model.player;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPlayer {

    Player player;
    @BeforeEach
    void setUp() {
        Position initialPosition = new Position(0, 0);
        this.player = new PlayerImpl(initialPosition);
    }

    @Test
    void testSetPosition() {
        assertEquals(new Position(0, 0), player.getPosition());
        player.setPosition(new Position(5, 5));
        assertEquals(new Position(5, 5), player.getPosition());
    }

    @Test
    void testGetCommand() {
        assertTrue(player.getCommand().isEmpty());
    }

    @Test
    void testGetLastUpdateTime() {
        Maze maze;
        assertEquals(0, player.getLastUpdateTime());
    }

    @Test
    void testIncreaseSpeed() {
        assertEquals(1, player.getSpeed());
        player.increaseSpeed();
        assertEquals(1.1, player.getSpeed());
    }
    @Test
    void testIncreaseBombLimit() {
        assertEquals(1, player.getBombLimit());
        player.increaseBombLimit();
        assertEquals(2, player.getBombLimit());
        player.increaseBombLimit();
        player.increaseBombLimit();
        player.increaseBombLimit();
        assertEquals(5, player.getBombLimit());
    }
}
