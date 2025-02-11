package it.unibo.michelito.model.bomb;

import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestBomb {
    private Maze maze;
    private static final double X_SPAWN = 6;
    private static final double Y_SPAWN = 6;
    private static final long TICK = 20; // Simula un tick del game loop
    private static final long TOTAL_FUSE_TIME = 3000; // Tempo di esplosione della bomba

    @BeforeEach
    void setUp() {
        this.maze = new MazeImpl(LevelGenerator.testLevel(), new LevelGenerator(e -> { }));
    }

    @Test
    void testBombCreation() {
//        final BombImpl bomb = new BombImpl(new Position(X_SPAWN, Y_SPAWN), BombType.STANDARD);
//        assertEquals(new Position(X_SPAWN, Y_SPAWN), bomb.position());
//        assertFalse(bomb.isExploded());
//        assertEquals(BombType.STANDARD, bomb.getBombType());
    }

    @Test
    void testBombExplosionAfterFuseTime() {
//        final BombImpl bomb = new BombImpl(new Position(X_SPAWN, Y_SPAWN), BombType.STANDARD);
//        maze.addMazeObject(bomb);
//        for (long time = 0; time < TOTAL_FUSE_TIME; time += TICK) {
//            bomb.update(TICK, maze);
//        }
//
//        assertTrue(bomb.isExploded());
//        assertFalse(maze.getBombs().contains(bomb));
    }

    @Test
    void testFlamesGeneratedAfterExplosion() {
//        final BombImpl bomb = new BombImpl(new Position(X_SPAWN, Y_SPAWN), BombType.STANDARD);
//        maze.addMazeObject(bomb);
//        for (long time = 0; time < TOTAL_FUSE_TIME; time += TICK) {
//            bomb.update(TICK, maze);
//        }
//        var flames = maze.getAllObjects().stream()
//                .filter(obj -> obj instanceof Flame)
//                .map(obj -> (Flame) obj)
//                .toList();
//
//        assertFalse(flames.isEmpty());
//
//        // Check that at least one flame is at the bomb's position
//        assertTrue(flames.stream().anyMatch(flame -> flame.position().equals(bomb.position())));
    }
}
