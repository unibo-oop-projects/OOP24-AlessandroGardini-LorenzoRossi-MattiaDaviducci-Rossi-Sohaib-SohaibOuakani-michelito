package it.unibo.michelito.model.maze;

import it.unibo.michelito.model.box.impl.BoxImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.wall.impl.WallImpl;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.Set;

/**
 * TestBox test.
 */
public class TestMaze {

    private Maze maze;

    private Position randomPositionGenerator() {
        Random random = new Random();
        return new Position(random.nextInt(10), random.nextInt(10) );
    }

    @BeforeEach
    public void setUp() {
        var position = new Position(0, 0);
        Set<MazeObject> setOfObjects = Set.of(
                new WallImpl(randomPositionGenerator()),
                new WallImpl(randomPositionGenerator()),
                new WallImpl(randomPositionGenerator()),
                new WallImpl(randomPositionGenerator()),
                new BoxImpl(randomPositionGenerator()),
                new BoxImpl(randomPositionGenerator()),
                new BoxImpl(randomPositionGenerator()),
                new BoxImpl(randomPositionGenerator())
        );
        maze = new MazeImpl(setOfObjects);

    }

    @Test
    public void testMaze() {

    }
}
