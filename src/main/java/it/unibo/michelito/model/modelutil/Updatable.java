package it.unibo.michelito.model.modelutil;

import it.unibo.michelito.model.maze.api.Maze;

public interface Updatable extends MazeObject {
    /**
     * This method tells the {@link MazeObject} to update itself.
     * @param currentTime is the current time in millisecond.
     * @param maze is the current {@link Maze}.
     */
    void update(long currentTime, Maze maze);
}
