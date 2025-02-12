package it.unibo.michelito.model.bomb;

import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.model.bomb.api.Bomb;
import it.unibo.michelito.model.bomb.impl.BombFactoryImpl;
import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestBomb {
    private Maze maze;
    private static final double X_SPAWN = 6;
    private static final double Y_SPAWN = 6;
    private static final long TICK = 20; // Simula un tick del game loop
    private static final long TOTAL_FUSE_TIME = 3000; // Tempo di esplosione della bomba
    private BombFactoryImpl bombFactory;

    @BeforeEach
    void setUp() {
        this.maze = new MazeImpl(LevelGenerator.testLevel(), new LevelGenerator(e -> { }));
        this.bombFactory = new BombFactoryImpl();
    }

    @Test
    void testBombCreation() {
        Bomb bomb = bombFactory.createStandardBomb(new Position(X_SPAWN, Y_SPAWN));
        assertEquals(new Position(X_SPAWN, Y_SPAWN), bomb.position());
        assertEquals(1, bomb.getRange()); // Standard bomb range
        assertFalse(bomb.isPassThrough());
    }

    @Test
    void testBombExplosionAfterFuseTime() {
        Bomb bomb = bombFactory.createStandardBomb(new Position(X_SPAWN, Y_SPAWN));
        maze.addMazeObject(bomb);
        for (long time = 0; time < TOTAL_FUSE_TIME; time += TICK) {
            bomb.update(TICK, maze);
        }

        assertFalse(maze.getAllObjects().contains(bomb));
    }

    @Test
    void testFlamesGeneratedAfterExplosion() {
        Bomb bomb = bombFactory.createStandardBomb(new Position(X_SPAWN, Y_SPAWN));
        maze.addMazeObject(bomb);
        for (long time = 0; time < TOTAL_FUSE_TIME; time += TICK) {
            bomb.update(TICK, maze);
        }
        Set<Flame> flames = maze.getAllObjects().stream()
                .filter(obj -> obj instanceof Flame)
                .map(obj -> (Flame) obj)
                .collect(java.util.stream.Collectors.toSet());

        assertFalse(flames.isEmpty());
        assertTrue(flames.stream().anyMatch(flame -> flame.position().equals(bomb.position())));
    }
}
