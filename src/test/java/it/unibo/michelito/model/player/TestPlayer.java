package it.unibo.michelito.model.player;

import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestPlayer {

    private Player player;
    private final Maze maze = new MazeImpl(LevelGenerator.testLevel());
    private static final  double X_SPAWN = 6;
    private static final double Y_SPAWN = 6;
    private static final long TICK = 1;

    @BeforeEach
    void setUp() {
        this.player = maze.getPlayer();
        /*spawns at (6, 6)*/
    }

    @Test
    void testMovement() {

        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());

        player.setDirection(Direction.RIGHT);
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN + 1, Y_SPAWN), player.position());

        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN + 1, Y_SPAWN), player.position());

        player.setDirection(Direction.LEFT);
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());

        player.setDirection(Direction.DOWN);
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN + 1), player.position());

        player.setDirection(Direction.UP);
        player.update(TICK, this.maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());
    }

    @Test
    void testBumpInToWalls() {


        player.setDirection(Direction.LEFT);
        player.update(TICK, maze);
        assertEquals(new Position(X_SPAWN - 1, Y_SPAWN), player.position());

        player.setDirection(Direction.LEFT);
        player.update(TICK, maze);
        assertEquals(new Position(X_SPAWN - 1, Y_SPAWN), player.position());

        player.setDirection(Direction.UP);
        player.update(TICK, maze);
        assertEquals(new Position(X_SPAWN - 1, Y_SPAWN), player.position());

        player.setDirection(Direction.DOWN);
        player.update(TICK, maze);
        assertEquals(new Position(X_SPAWN - 1, Y_SPAWN + 1), player.position());
    }
}
