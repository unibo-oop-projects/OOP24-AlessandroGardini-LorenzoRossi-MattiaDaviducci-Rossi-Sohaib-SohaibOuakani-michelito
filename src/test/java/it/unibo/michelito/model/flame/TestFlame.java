package it.unibo.michelito.model.flame;

import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.flame.api.FlameFactory;
import it.unibo.michelito.model.flame.api.FlamePropagation;
import it.unibo.michelito.model.flame.impl.FlameFactoryImpl;
import it.unibo.michelito.model.flame.impl.FlamePropagationImpl;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.model.enemy.impl.EnemyImpl;
import it.unibo.michelito.model.box.impl.BoxImpl;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.ObjectType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

final class TestFlame {
    private MazeImpl maze;
    private FlameFactory flameFactory;
    private FlamePropagation flamePropagation;

    private static final double X_SPAWN = 6;
    private static final double Y_SPAWN = 6;
    private static final double BLOCK_SIZE = 6;
    private static final long FLAME_LIFETIME = 1000;
    private static final int BOMB_RANGE = 1;

    @BeforeEach
    void setUp() {
        this.maze = new MazeImpl(LevelGenerator.testLevel(), new LevelGenerator(e -> { }));
        this.flameFactory = new FlameFactoryImpl();
        this.flamePropagation = new FlamePropagationImpl(flameFactory);
    }

    @Test
    void testFlameCreation() {
        final Flame flame = flameFactory.createFlame(new Position(X_SPAWN, Y_SPAWN));
        maze.addMazeObject(flame);
        HitBox hitBox = flame.getHitBox();

        assertEquals(new Position(X_SPAWN, Y_SPAWN), flame.position());
        assertFalse(flame.isExtinguished());
        assertEquals(ObjectType.FLAME, flame.getType());
        assertNotNull(hitBox);
    }

    @Test
    void testFlameExtinguishesAfterTime() {
        final Flame flame = flameFactory.createFlame(new Position(X_SPAWN, Y_SPAWN));
        maze.addMazeObject(flame);
        for (long time = 0; time <= FLAME_LIFETIME; time += 100) {
            flame.update(100, maze);
        }

        assertTrue(flame.isExtinguished());
        assertFalse(maze.getAllObjects().contains(flame));
    }

    @Test
    void testFlameKillsMichelito() {
        final PlayerImpl player = new PlayerImpl(new Position(X_SPAWN, Y_SPAWN));
        maze.addMazeObject(player);
        List<Flame> flames = flamePropagation.propagate(
                new Position(X_SPAWN, Y_SPAWN),
                BOMB_RANGE,
                false,
                maze
        );
        flames.forEach(maze::addMazeObject);
        flames.forEach(flame -> flame.update(100, maze));

        assertTrue(maze.isLost());
    }

    @Test
    void testFlameKillsEnemy() {
        final EnemyImpl enemy = new EnemyImpl(new Position(X_SPAWN, Y_SPAWN));
        maze.addMazeObject(enemy);
        List<Flame> flames = flamePropagation.propagate(
                new Position(X_SPAWN, Y_SPAWN),
                BOMB_RANGE,
                false,
                maze
        );
        flames.forEach(maze::addMazeObject);

        assertFalse(maze.getEnemies().contains(enemy));
    }

    @Test
    void testFlameDestroysBox() {
        final BoxImpl box = new BoxImpl(new Position(X_SPAWN + BLOCK_SIZE, Y_SPAWN));
        maze.addMazeObject(box);
        List<Flame> flames = flamePropagation.propagate(
                new Position(X_SPAWN, Y_SPAWN),
                BOMB_RANGE,
                false,
                maze
        );
        flames.forEach(maze::addMazeObject);

        assertFalse(maze.getBoxes().contains(box));
    }
}