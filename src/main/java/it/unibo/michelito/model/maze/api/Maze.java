package it.unibo.michelito.model.maze.api;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.model.door.api.Door;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.powerups.api.Powerup;
import it.unibo.michelito.model.wall.api.Wall;

import java.util.Set;

/**
 * Interface that represent a maze and where all {@link MazeObject} are contained.
 * Provides a method for each interface that extend {@link MazeObject}.
 * Provides methods to be called once the level is won and for the eventual death of michelito.
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
     * Remove a {@link Temporary} from the maze.
     *
     * @param temporaryObject to be removed.
     * @return whether the operation as been successful.
     */
    boolean removeMazeObject(Temporary temporaryObject);

    /**
     * Method to be called to kill michelito.
     */
    void killMichelito();

    /**
     * Method to be called one michelito enters the door.
     */
    void enterTheDoor();

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

    /**
     * Getter the {@link Door} within this maze.
     *
     * @return the {@link Door}.
     */
    Door getDoor();
}
