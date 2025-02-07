package it.unibo.michelito.model.gamemanager;

import it.unibo.michelito.model.gamemanager.impl.GameManagerImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * Test class for {@link GameManagerImpl}.
 */
class TestGameManager {
    private GameManagerImpl gameManager;
    private AtomicBoolean gameWon; //these two AtomicBoolean are used for player command evaluation.
    private AtomicBoolean gameOver;

    @BeforeEach
    void setUp() {
        gameWon = new AtomicBoolean(false);
        gameOver = new AtomicBoolean(false);
        gameManager = new GameManagerImpl(() -> gameWon.set(true), () -> gameOver.set(true));
    }

    /**
     * Tests that the game starts with the correct initial conditions.
     */
    @Test
    void testGameStartsCorrectly() {
        assertNotNull(gameManager);
        assertFalse(gameWon.get());
        assertFalse(gameOver.get());
    }
}
