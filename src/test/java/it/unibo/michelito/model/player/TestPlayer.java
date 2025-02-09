package it.unibo.michelito.model.player;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestPlayer {

    Player player;
    int levelNumber = -1;
    final Maze maze = new MazeImpl(levelNumber);

    @BeforeEach
    void setUp() {
        this.player = maze.getPlayer();
        /*spawns at (6, 6)*/
    }

    @Test
    void testMovement() {

        assertEquals(new Position(6, 6), player.position());
        player.update(10, this.maze);
        assertEquals(new Position(6, 6), player.position());

        player.setDirection(Direction.RIGHT);
        player.update(10, this.maze);
        assertEquals(new Position(16, 6), player.position());

        player.update(10, this.maze);
        assertEquals(new Position(16, 6), player.position());

        player.setDirection(Direction.LEFT);
        player.update(10, this.maze);
        assertEquals(new Position(6, 6), player.position());

        player.setDirection(Direction.DOWN);
        player.update(10, this.maze);
        assertEquals(new Position(6, 16), player.position());

        player.setDirection(Direction.UP);
        player.update(10, this.maze);
        assertEquals(new Position(6, 6), player.position());
    }

    @Test
    void testBumpInToWalls() {


        player.setDirection(Direction.LEFT);
        player.update(1, maze);
        assertEquals(new Position(5, 6), player.position());

        player.setDirection(Direction.LEFT);
        player.update(1, maze);
        assertEquals(new Position(5, 6), player.position());

        player.setDirection(Direction.UP);
        player.update(1, maze);
        assertEquals(new Position(5, 6), player.position());

        player.setDirection(Direction.DOWN);
        player.update(1, maze);
        assertEquals(new Position(5, 7), player.position());
    }
}