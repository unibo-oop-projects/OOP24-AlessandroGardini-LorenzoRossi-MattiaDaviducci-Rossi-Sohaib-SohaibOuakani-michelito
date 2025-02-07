package it.unibo.michelito.model.door;

import it.unibo.michelito.model.door.api.Door;
import it.unibo.michelito.model.door.impl.DoorImpl;
import it.unibo.michelito.model.enemy.api.Enemy;
import it.unibo.michelito.model.enemy.impl.EnemyImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link DoorImpl}.
 */
final class TestDoor {
    private Door door;
    private Position position;

    /**
     * Creates a Door to be used in each test.
     */
    @BeforeEach
    void setUp() {
        position = new Position(0, 0);
        door = new DoorImpl(position);
    }

    /**
     * Tests {@link Position} and {@link HitBox}.
     */
    @Test
    void testPosition() {
        assertNotNull(door);
        assertEquals(door.getHitBox(), new HitBoxFactoryImpl().squareHitBox(position));
        assertEquals(door.position(), position);
    }

    /**
     * Test not having player throw exception.
     */
    @Test
    void testPlayer() {
        final Maze maze = new MazeImpl(0);
        assertThrows(IllegalStateException.class, () -> door.update(0, maze));
    }

    /**
     * Test opening.
     */
    @Test
    void testOpening(){
        final var enemy = new EnemyImpl(new Position(10, 10));
        final var player = new PlayerImpl(new Position(10, 10));
        final Maze maze = new MazeImpl(0);
        final int time = 0;
        assertFalse(door.isOpen());
        door.update(time, maze);
        assertFalse(door.isOpen());
        maze.removeMazeObject(enemy);
        door.update(time, maze);
        assertTrue(door.isOpen());
    }
}
