package it.unibo.michelito.model.gamemanager.impl;

import it.unibo.michelito.model.gamegenerator.api.GameGenerator;
import it.unibo.michelito.model.gamemanager.api.GameManager;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.player.api.PlayerCommand;

public class GameManagerImpl implements GameManager {
    private int currentLevel;
    private int life;
    private Maze currentMaze;
    private GameGenerator gameGenerator; //TODO: insert constructor when provided -> can be final !!

    @Override
    public void setCommand(PlayerCommand playerCommand) {

    }

    @Override
    public void update(long currentTime) {
        currentMaze.getUpdatable().forEach(e -> e.update(currentTime, currentMaze));

    }

    @Override
    public void init() {
        this.currentLevel = 0;
        currentMaze = new MazeImpl(gameGenerator.generate(this.currentLevel), this::loseLife);
    }

    private void loseLife(){

    }
}
