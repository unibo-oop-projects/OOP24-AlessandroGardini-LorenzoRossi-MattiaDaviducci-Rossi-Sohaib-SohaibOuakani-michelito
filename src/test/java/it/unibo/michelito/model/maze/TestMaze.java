package it.unibo.michelito.model.maze;

import it.unibo.michelito.model.box.impl.BoxImpl;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test for the {@link MazeImpl} class.
 */
final class TestMaze {
    private MazeImpl maze;

    /**
     * Sets up the test environment before each test.
     * Initializes a new BaseMaze.
     */
    @BeforeEach
    void setUp() {
        maze = new MazeImpl(-1);
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
        assertTrue(maze.getBoxes().isEmpty());
        assertTrue(maze.getPowerUp().isEmpty());
    }

    /**
     * Tests the maze won and lost states.
     */
    @Test
    void testWinAndLost() {
        assertFalse(maze.isWon());
        assertFalse(maze.isLost());
        maze.killMichelito();
        assertTrue(maze.isLost());
        maze.enterTheDoor();
        assertTrue(maze.isWon());
    }
}
