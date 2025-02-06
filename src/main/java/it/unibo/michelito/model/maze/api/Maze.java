package it.unibo.michelito.model.maze.api;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.model.wall.api.Wall;

import java.util.Set;

/**
 * Interface that represent a maze and where all MazeObjects are contained.
 * It provides a method for each interface that extend MazeObject.
 */
public interface Maze {
    /**
     * Adds a MazeObject in the maze.
     *
     * @param mazeObject to be added.
     * @return whether the operation as been successful.
     */
    boolean addMazeObject(MazeObject mazeObject);

    /**
     * Remove a MazeObject from the maze.
     *
     * @param mazeObject to be removed.
     * @return whether the operation as been successful.
     */
    boolean removeMazeObject(MazeObject mazeObject);

    /**
     * Getter for all the MazeObjects in the maze.
     *
     * @return all MazeObjects.
     */
    Set<MazeObject> getAllObjects();

    /**
     * Getter for all the Walls in the maze.
     *
     * @return all Walls.
     */
    Set<Wall> getWalls();

    /**
     * Getter for all the Boxes in the maze.
     *
     * @return all Walls.
     */
    Set<Box> getBoxes();

    /**
     * Getter for all the Updatable Objects in the maze.
     *
     * @return all Updatable Objects.
     */
    Set<Updatable> getUpdatable();

    /**
     * Getter the player within this maze.
     *
     * @return the player.
     */
    Player getPlayer();

    /**
     * Getter for all the Updatable Objects in the maze.
     *
     * @return all Updatable Objects.
     */
    Set<PowerUp> getPowerup();
}
