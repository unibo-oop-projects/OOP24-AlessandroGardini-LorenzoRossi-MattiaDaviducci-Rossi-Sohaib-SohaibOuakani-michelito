package it.unibo.michelito.model.player;

import it.unibo.michelito.model.box.impl.BoxImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.model.wall.impl.WallImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testBumpInToBox() {
        int levelNumber = -1;
        Maze maze = new MazeImpl(levelNumber);

        final Temporary box1 = new BoxImpl(new Position(5, 0));
        maze.addMazeObject(box1);
        player.setDirection(Direction.RIGHT);
        player.update(1, maze);
        assertEquals(new Position(0, 0), player.position());
        maze.removeMazeObject(box1);

        final Temporary box2 = new BoxImpl(new Position(0, 6));
        maze.addMazeObject(box2);
        player.setDirection(Direction.DOWN);
        player.update(1, maze);
        assertEquals(new Position(0, 0), player.position());

        player.setDirection(Direction.RIGHT);
        player.update(10, maze);
        assertEquals(new Position(10, 0), player.position());

    }
}