package it.unibo.michelito.model.maze;

import it.unibo.michelito.model.box.impl.BoxImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.model.wall.impl.WallImpl;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TestBox test.
 */
public class TestMaze {

    private Set<MazeObject> setOfObjects;


    @BeforeEach
    public void setUp() {
        setOfObjects = Set.of(
                new WallImpl(new Position(0, 0)),
                new WallImpl(new Position(1, 1)),
                new WallImpl(new Position(2, 2)),
                new WallImpl(new Position(3, 3)),
                new BoxImpl(new Position(0, 0)),
                new BoxImpl(new Position(1, 1)),
                new BoxImpl(new Position(2, 2)),
                new BoxImpl(new Position(3, 3)),
                new PlayerImpl(new Position(0, 0)),
                new PlayerImpl(new Position(1, 1)),
                new PlayerImpl(new Position(2, 2)),
                new PlayerImpl(new Position(3, 3))
        );
    }

    @Test
    public void testAddAndRemove() {
        var mazeObject = new WallImpl(new Position(4, 4));
        Maze maze = new MazeImpl(setOfObjects);
        assertNotNull(maze);
        assertFalse(maze.getAllObjects().isEmpty());
        assertFalse(maze.getAllObjects().contains(mazeObject));
        assertTrue(maze.addMazeObject(mazeObject));
        assertTrue(maze.getWalls().contains(mazeObject));
        assertTrue(maze.getAllObjects().contains(mazeObject));
        assertTrue(maze.removeMazeObject(mazeObject));
        assertFalse(maze.getWalls().contains(mazeObject));
    }

    @Test
    public void testConsistency() {
        Maze maze = new MazeImpl(setOfObjects);
        assertNotNull(maze);
        assertThrows(NullPointerException.class, () -> maze.addMazeObject(null));
        assertThrows(NullPointerException.class, () -> maze.removeMazeObject(null));
        assertFalse(maze.removeMazeObject(new WallImpl(new Position(4, 4))));
    }
}
