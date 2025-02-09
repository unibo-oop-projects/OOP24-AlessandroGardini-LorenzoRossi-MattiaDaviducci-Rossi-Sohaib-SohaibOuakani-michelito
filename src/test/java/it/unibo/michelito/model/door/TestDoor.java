package it.unibo.michelito.model.door;

import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.model.door.api.Door;
import it.unibo.michelito.model.door.impl.DoorImpl;
import it.unibo.michelito.model.enemy.api.Enemy;
import it.unibo.michelito.model.enemy.impl.EnemyImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for the {@link DoorImpl} class.
 */
final class TestDoor {
    private Door door;
    private Position position;

    /**
     * Sets up the test environment before each test.
     * Initializes a new {@link DoorImpl} instance with a predefined position.
     */
    @BeforeEach
    void setUp() {
        this.position = new Position(0, 4);
        this.door = new DoorImpl(position);
    }

    /**
     * Tests the {@code getHitBox} method to ensure it returns the correct {@link HitBox}.
     */
    @Test
    void testGetHitBox() {
        HitBox expectedHitBox = new HitBoxFactoryImpl().squareHitBox(this.position);
        assertEquals(expectedHitBox, this.door.getHitBox(), "HitBox should be squareHitBox");
    }

    /**
     * Tests the {@code getType} method to ensure it returns the correct {@link ObjectType}.
     */
    @Test
    void testGetType() {
        assertEquals(ObjectType.DOOR, this.door.getType(), "Type should be DOOR");
    }

    /**
     * Tests the behavior of a door opening when all {@link Enemy}s are removed from the {@link Maze}.
     */
    @Test
    void testOpening() {
        final var enemy = new EnemyImpl(new Position(10, 10));
        final Maze maze = new MazeImpl(LevelGenerator.testLevel());
        final int time = 0; //The time is irrelevant when updating a door
        assertFalse(this.door.isOpen());
        maze.addMazeObject(enemy);
        this.door.update(time, maze);
        assertFalse(this.door.isOpen());
        maze.removeMazeObject(enemy);
        this.door.update(time, maze);
        assertTrue(this.door.isOpen());
    }

    /**
     * Tests the behavior of a door setting the {@link Maze} as won
     * when it's open and the player is over it.
     */
    @Test
    void testMazeWin() {
        final MazeImpl maze = new MazeImpl(LevelGenerator.testLevel()); //Test Maze
        Door doorUnderPlayer = new DoorImpl(maze.getPlayer().position());
        assertFalse(maze.isWon());
        doorUnderPlayer.update(5, maze);
        assertTrue(doorUnderPlayer.isOpen());
        assertTrue(maze.isWon());
    }
}
