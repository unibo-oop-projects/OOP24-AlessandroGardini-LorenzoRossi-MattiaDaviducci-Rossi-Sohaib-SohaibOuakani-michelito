package it.unibo.michelito.model.maze.api;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.powerups.Powerup;
import it.unibo.michelito.model.wall.api.Wall;

import java.util.Set;

/**
 * Interface that represent a maze and where all {@link MazeObject} are contained.
 * It provides a method for each interface that extend {@link MazeObject}.
 */
public interface Maze {
    /**
     * Adds a {@link MazeObject} in the maze.
     *
     * @param mazeObject to be added.
     * @return whether the operation as been successful.
     */
    boolean addMazeObject(MazeObject mazeObject);

    /**
     * Remove a {@link MazeObject} from the maze.
     *
     * @param mazeObject to be removed.
     * @return whether the operation as been successful.
     */
    boolean removeMazeObject(MazeObject mazeObject);

    /**
     * Getter for all the {@link MazeObject} in the maze.
     *
     * @return all {@link MazeObject}.
     */
    Set<MazeObject> getAllObjects();

    /**
     * Getter for all the {@link Wall} in the maze.
     *
     * @return all {@link Wall}.
     */
    Set<Wall> getWalls();

    /**
     * Getter for all the {@link Box} in the maze.
     *
     * @return all {@link Box}.
     */
    Set<Box> getBoxes();

    /**
     * Getter for all the {@link Updatable} Objects in the maze.
     *
     * @return all {@link Updatable} Objects.
     */
    Set<Updatable> getUpdatable();

    /**
     * Getter the {@link Player} within this maze.
     *
     * @return the {@link Player}.
     */
    Player getPlayer();

    /**
     * Getter for all the {@link Powerup} Objects in the maze.
     *
     * @return all {@link Powerup} Objects.
     */
    Set<Powerup> getPowerup();
}
