package it.unibo.michelito.model.gamemanager.impl;

import it.unibo.michelito.controller.palyercommand.api.PlayerCommand;
import it.unibo.michelito.model.gamegenerator.api.GameGenerator;
import it.unibo.michelito.model.gamegenerator.impl.GameGeneratorImpl;
import it.unibo.michelito.model.gamemanager.api.GameManager;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;

/**
 * Implementation of the {@link GameManager} interface, responsible for managing the game state,
 * levels, lives, and interactions between the player and the maze.
 */
public final class GameManagerImpl implements GameManager {
    private static final int MAX_MAZE_INDEX = 1;
    private static final int STARTING_LIFE_COUNT = 5;
    private int currentLevel;
    private int currentLifeCount;
    private Maze currentMaze;
    private PlayerCommand playerCommand;
    private final Runnable onGameWin;
    private final Runnable onGameOver;

    /**
     * Constructs a GameManagerImpl instance.
     *
     * @param onGameWin  Callback for when the game is won.
     * @param onGameOver Callback for when the game is over.
     */
    public GameManagerImpl(final Runnable onGameWin, final Runnable onGameOver) {
        this.currentLifeCount = STARTING_LIFE_COUNT;
        this.currentLevel = 0;
        this.onGameWin = onGameWin;
        this.onGameOver = onGameOver;
        this.startGame();
    }

    @Override
    public void setCommand(final PlayerCommand playerCommand) {
        this.playerCommand = playerCommand;
    }

    @Override
    public void update(final long currentTime) {
        this.consumePlayerCommand();
        this.currentMaze.getUpdatable().forEach(e -> e.update(currentTime, this.currentMaze));
    }

    /**
     * Starts a new game by resetting the level and generating the first maze.
     */
    private void startGame() {
        this.currentLevel = 0;
        this.currentMaze = this.generateMaze(this.currentLevel);
    }

    /**
     * Reduces the player's life count and regenerates the current maze.
     * If no lives remain, the game over action is triggered.
     */
    private void loseLife() {
        if (this.currentLifeCount == 0) {
            this.onGameOver.run();
        } else {
            this.currentLifeCount--;
            this.currentMaze = this.generateMaze(this.currentLevel);
        }
    }

    /**
     * Advances to the next maze if available, or triggers the win action if the last level is completed.
     */
    private void wonMaze() {
        if (this.currentLevel + 1 > MAX_MAZE_INDEX) {
            this.onGameWin.run();
        } else {
            this.currentLevel++;
            this.currentMaze = this.generateMaze(this.currentLevel);
        }
    }

    /**
     * Generates a new maze for the specified level.
     *
     * @param level The level for which the maze should be generated.
     * @return The generated maze.
     */
    private Maze generateMaze(final int level) {
        GameGenerator gameGenerator = new GameGeneratorImpl();
        return new MazeImpl(gameGenerator.generate(level), this::loseLife, this::wonMaze);
    }

    /**
     * Executes the player's command if one has been set.
     */
    private void consumePlayerCommand() {
        if (this.playerCommand != null) {
            playerCommand.execute(this.currentMaze.getPlayer());
            playerCommand = null;
        }
    }
}
