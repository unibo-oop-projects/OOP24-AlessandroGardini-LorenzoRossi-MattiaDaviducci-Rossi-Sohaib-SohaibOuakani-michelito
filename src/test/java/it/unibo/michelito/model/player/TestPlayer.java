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
        final Maze maze = new MazeImpl(Set.of(), () -> { }, () -> { });

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
        player.setDirection(Direction.RIGHT);
        player.update(1, new MazeImpl(Set.of(new WallImpl(new Position(5, 0))), () -> { }, () -> { }));
        assertEquals(new Position(0, 0), player.position());

        player.setDirection(Direction.DOWN);
        player.update(6, new MazeImpl(Set.of(new WallImpl(new Position(0, 5))), () -> { }, () -> { }));
        assertEquals(new Position(0, 0), player.position());

        player.setDirection(Direction.UP);
        player.update(7, new MazeImpl(Set.of(new WallImpl(new Position(0, -5))), () -> { }, () -> { }));
        assertEquals(new Position(0, 0), player.position());

        player.setDirection(Direction.RIGHT);
        player.update(17, new MazeImpl(Set.of(new WallImpl(new Position(100, 0))), () -> { }, () -> { }));
        assertEquals(new Position(10, 0), player.position());
    }

    @Test
    void testIncreaseSpeed() {
        player.setDirection(Direction.RIGHT);
        player.update(1, new MazeImpl(Set.of(), () -> { }, () -> { }));
        assertEquals(new Position(1, 0), player.position());

        player.setDirection(Direction.DOWN);
        player.increaseSpeed();
        player.increaseSpeed();
        player.increaseSpeed();
        player.increaseSpeed();
        player.increaseSpeed();
        player.update(2, new MazeImpl(Set.of(), () -> { }, () -> { }));
        assertEquals(new Position(1, 1.5), player.position());
    }
}