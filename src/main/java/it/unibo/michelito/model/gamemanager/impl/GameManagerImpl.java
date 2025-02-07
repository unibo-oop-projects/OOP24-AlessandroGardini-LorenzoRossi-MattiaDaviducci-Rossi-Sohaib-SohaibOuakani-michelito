package it.unibo.michelito.model.gamemanager.impl;

import it.unibo.michelito.controller.palyercommand.api.PlayerCommand;
import it.unibo.michelito.model.gamemanager.api.GameManager;
import it.unibo.michelito.model.maze.api.Level;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.util.GameObject;

import java.util.Set;

/**
 * Implementation of the {@link GameManager} interface, responsible for managing
 * levels, lives and current game state.
 */
public final class GameManagerImpl implements GameManager {
    private static final int MAX_MAZE_INDEX = 1;
    private static final int STARTING_LIFE_COUNT = 5;

    private int currentLevelIndex;
    private int currentLifeCount;
    private Level currentLevel;

    private boolean gameOver;
    private boolean gameWon;

    /**
     * Constructs a GameManagerImpl instance.
     */
    public GameManagerImpl() {
        this.currentLevelIndex = 0;
        this.currentLifeCount = STARTING_LIFE_COUNT;
        this.currentLevel = new MazeImpl(0);
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public boolean isGameWon() {
        return gameWon;
    }

    @Override
    public Set<GameObject> getObjects() {
        return this.currentLevel.getGameObjects();
    }

    @Override
    public void setCommand(final PlayerCommand playerCommand) {
        this.currentLevel.setCommand(playerCommand);
    }

    @Override
    public void update(final long currentTime) {
        currentLevel.update(currentTime);
        if (currentLevel.isLost()) {
            this.loseLife();
        }
        if (currentLevel.isWon()) {
            this.wonMaze();
        }
    }

    /**
     * Method to call when michelito lose a life.
     */
    private void loseLife() {
        if (this.currentLifeCount == 0) {
            this.gameOver = true;
        } else {
            this.currentLifeCount--;
            this.currentLevel = new MazeImpl(this.currentLevelIndex);
        }
    }

    /**
     * Method to be called once michelito has won a level.
     */
    private void wonMaze() {
        if (this.currentLevelIndex >= MAX_MAZE_INDEX) {
            this.gameWon = true;
        } else {
            this.currentLevelIndex++;
            this.currentLevel = new MazeImpl(this.currentLevelIndex);
        }
    }
}
