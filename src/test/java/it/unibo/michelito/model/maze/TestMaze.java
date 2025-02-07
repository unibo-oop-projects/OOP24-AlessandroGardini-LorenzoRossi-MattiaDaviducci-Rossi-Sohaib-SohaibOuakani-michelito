package it.unibo.michelito.model.maze;

import it.unibo.michelito.model.box.impl.BoxImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.model.wall.impl.WallImpl;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link MazeImpl}.
 */
final class TestMaze {
    private Maze maze;

    /**
     * Creates a Set of MazeObjects to be used in each test.
     */
    @BeforeEach
    void setUp() {
        final Set<MazeObject> setOfObjects = new HashSet<>(Set.of(
                new WallImpl(new Position(0, 0)),
                new WallImpl(new Position(1, 1)),
                new WallImpl(new Position(2, 2)),
                new WallImpl(new Position(3, 3)),
                new BoxImpl(new Position(0, 0)),
                new BoxImpl(new Position(1, 1)),
                new BoxImpl(new Position(2, 2)),
                new BoxImpl(new Position(3, 3)),
                new PlayerImpl(new Position(0, 0))
        ));
        maze = new MazeImpl(setOfObjects, () -> { }, () -> { });
    }

    /**
     * Tests add and remove feature of maze.
     */
    @Test
    void testAddAndRemove() {
        final Temporary temporaryObject = new BoxImpl(new Position(4, 4));
        assertNotNull(maze);
        assertFalse(maze.getAllObjects().isEmpty());
        assertFalse(maze.getAllObjects().contains(temporaryObject));
        assertTrue(maze.addMazeObject(temporaryObject));
        assertTrue(maze.getBoxes().contains(temporaryObject));
        assertTrue(maze.getAllObjects().contains(temporaryObject));
        assertTrue(maze.removeMazeObject(temporaryObject));
        assertFalse(maze.getBoxes().contains(temporaryObject));
    }

    /**
     * Tests that both add and remove throw exception.
     */
    @Test
    void testConsistency() {
        assertThrows(NullPointerException.class, () -> maze.addMazeObject(null));
        assertThrows(NullPointerException.class, () -> maze.removeMazeObject(null));
        assertFalse(maze.removeMazeObject(new BoxImpl(new Position(4, 4))));
    }

    /**
     * Tests that the filter function.
     */
    @Test
    void testFilter() {
        assertFalse(maze.getAllObjects().isEmpty());
        assertFalse(maze.getWalls().isEmpty());
        assertFalse(maze.getBoxes().isEmpty());
        assertTrue(maze.getPowerUp().isEmpty());
    }

    /**
     * This test verifies that the `Runnable` handlers in {@link MazeImpl} are correctly executed
     * when Michelito dies (`killMichelito()`) or when he completes the maze (`enterTheDoor()`).
     * We use {@link AtomicBoolean} instead of a primitive `boolean` because Java does not allow
     * modifying captured local variables inside lambdas unless they are effectively final.
     */
    @Test
    void testRunnable() {
        final AtomicBoolean deathHandlerExecuted = new AtomicBoolean(false);
        final AtomicBoolean wonMazeExecuted = new AtomicBoolean(false);
        maze = new MazeImpl(new HashSet<>(),
                () -> deathHandlerExecuted.set(true),
                () -> wonMazeExecuted.set(true));
        maze.killMichelito();
        assertTrue(deathHandlerExecuted.get());
        maze.enterTheDoor();
        assertTrue(wonMazeExecuted.get());
    }
}
