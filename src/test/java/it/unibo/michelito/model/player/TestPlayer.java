package it.unibo.michelito.model.player;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.model.wall.impl.WallImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TestPlayer {

    Player player;

    @BeforeEach
    void setUp() {
        final Position initialPosition = new Position(0, 0);
        this.player = new PlayerImpl(initialPosition);
    }

    @Test
    void testMovement() {
        int levelNumber = -1;
        final Maze maze = new MazeImpl(levelNumber);

        assertEquals(new Position(0, 0), player.position());
        player.update(10, maze);
        assertEquals(new Position(0, 0), player.position());

        player.setDirection(Direction.RIGHT);
        player.update(20, maze);
        assertEquals(new Position(10, 0), player.position());

        player.update(30, maze);
        assertEquals(new Position(10, 0), player.position());

        player.setDirection(Direction.LEFT);
        player.update(40, maze);
        assertEquals(new Position(0, 0), player.position());

        player.setDirection(Direction.DOWN);
        player.update(50, maze);
        assertEquals(new Position(0, 10), player.position());

        player.setDirection(Direction.UP);
        player.update(60, maze);
        assertEquals(new Position(0, 0), player.position());
    }

    @Test
    void testBumpInToWall() {
        int levelNumber = -1;
        Maze maze = new MazeImpl(levelNumber);

        player.setDirection(Direction.RIGHT);
        player.update(1, maze);
        assertEquals(new Position(0, 0), player.position());

        player.setDirection(Direction.DOWN);
        player.update(2, maze);
        assertEquals(new Position(0, 0), player.position());

        player.setDirection(Direction.UP);
        player.update(3,maze);
        assertEquals(new Position(0, 0), player.position());

        player.setDirection(Direction.RIGHT);
        player.update(4, maze);
        assertEquals(new Position(1, 0), player.position());

        player.setDirection(Direction.RIGHT);
        player.update(13, maze);
        assertEquals(new Position(10, 0), player.position());

    }

    @Test
    void testIncreaseSpeed() {
        int levelNumber = -1;
        Maze maze = new MazeImpl(levelNumber);
        final double speedUpgrade = 0.1;
        player.setDirection(Direction.RIGHT);
        player.update(1, maze);
        assertEquals(new Position(1, 0), player.position());

        player.setDirection(Direction.DOWN);
        player.increaseSpeed(speedUpgrade);
        player.increaseSpeed(speedUpgrade);
        player.increaseSpeed(speedUpgrade);
        player.increaseSpeed(speedUpgrade);
        player.increaseSpeed(speedUpgrade);
        player.update(2, maze);
        assertEquals(new Position(1, 1.5), player.position());
    }
}