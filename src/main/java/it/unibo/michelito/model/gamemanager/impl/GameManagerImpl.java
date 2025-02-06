package it.unibo.michelito.model.gamemanager.impl;

import it.unibo.michelito.model.gamegenerator.api.GameGenerator;
import it.unibo.michelito.model.gamemanager.api.GameManager;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.PlayerCommand;

public class GameManagerImpl implements GameManager {
    private final static int MAX_MAZE_INDEX = 1;
    private final static int STARTING_LIFE_COUNT = 5;

    private int currentLevel;
    private int currentLifeCount;
    private Maze currentMaze;

    private PlayerCommand playerCommand;

    private final Runnable onGameWin;
    private final Runnable onGameOver;

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
    
    private void startGame() {
        this.currentLevel = 0;
        this.currentMaze = this.generateMaze(this.currentLevel);
    }
    
    private void loseLife(){
        if (this.currentLifeCount == 0) {
            this.onGameOver.run();
        } else {
            this.currentLifeCount--;
            this.currentMaze = this.generateMaze(this.currentLevel);
        }
    }

    private void WonMaze() {
        if (this.currentLevel + 1 > MAX_MAZE_INDEX) {
            this.onGameWin.run();
        } else {
            this.currentLevel++;
            this.currentMaze = this.generateMaze(this.currentLevel);
        }
    }
    
    private Maze generateMaze(final int level) { //TODO: gamegenerate impl
        GameGenerator gameGenerator; // new gameGeneratorImpl();
        return null; //new MazeImpl(gameGenerator.generate(level), this::loseLife, this::WonMaze);
    }
    
    private void consumePlayerCommand() {
        if (this.playerCommand != null) {
            playerCommand.execute(this.currentMaze.getPlayer());
            playerCommand = null;
        }
    }
}
