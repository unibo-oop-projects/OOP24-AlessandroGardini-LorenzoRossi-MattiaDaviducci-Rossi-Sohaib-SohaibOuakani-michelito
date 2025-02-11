package it.unibo.michelito.model.enemy;

import it.unibo.michelito.model.enemy.api.Enemy;
import it.unibo.michelito.model.enemy.api.ai.MoodAI;
import it.unibo.michelito.model.enemy.api.ai.MoodType;
import it.unibo.michelito.model.enemy.impl.EnemyImpl;
import it.unibo.michelito.model.enemy.impl.ai.MoodAIImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBoxFactory;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for {@link EnemyImpl}.
 */
class TestEnemy {
    private static final long SLEEP_TIME = 100_000;
    private static final Position BASE_POSITION = new Position(1000, 1000);
    private static final Position ENEMY_TO_TRY1 = new Position(20, 20);
    private static final Position ENEMY_TO_TRY2 = new Position(20, 10);
    private static final long TESTDELTA1 = 10;
    private static final long TESTDELTA2 = 5;


    private Enemy enemy;
    private Maze maze;

    @BeforeEach
    void setUp() {
        final int levelNumber = -1;
        maze = new MazeImpl(levelNumber);
        maze.addMazeObject(new EnemyImpl(ENEMY_TO_TRY1));
        maze.addMazeObject(new EnemyImpl(ENEMY_TO_TRY2));
        final Position initalPosition = new Position(1000, 1000);
        this.enemy = new EnemyImpl(initalPosition);
    }

    @Test
    public void testMovement() {
        final HitBoxFactory testHitBoxFact = new HitBoxFactoryImpl();
        final HitBox testHitBox = testHitBoxFact.entityeHitBox(BASE_POSITION);
        assertEquals(testHitBox, this.enemy.getHitBox());
        enemy.update(SLEEP_TIME, maze);
        assertTrue(maze.removeMazeObject(new EnemyImpl(ENEMY_TO_TRY1)));
        assertTrue(maze.removeMazeObject(new EnemyImpl(ENEMY_TO_TRY2)));
        enemy.update(TESTDELTA1, maze);
        assertNotEquals(BASE_POSITION, enemy.position());
        final Position newPosition = enemy.position();
        enemy.update(TESTDELTA2, maze);
        assertNotEquals(newPosition, enemy.position());
    }

    @Test
    public void testAI() {
        final MoodAI moodAI = new MoodAIImpl(maze);
        assertEquals(MoodType.SLEEPING, moodAI.getMood());
        assertEquals(Direction.NONE, moodAI.getDirection());
        moodAI.update(SLEEP_TIME);
        assertEquals(MoodType.CHILLING, moodAI.getMood());
        moodAI.setMood(MoodType.SEARCHING);
        assertEquals(MoodType.SEARCHING, moodAI.getMood());
        assertNotEquals(Direction.NONE, moodAI.getDirection());
    }

}
