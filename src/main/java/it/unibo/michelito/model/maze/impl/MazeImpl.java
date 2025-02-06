package it.unibo.michelito.model.maze.impl;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.powerups.Powerup;
import it.unibo.michelito.model.wall.api.Wall;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link Maze} interface.
 *
 * @param mazeObjectsSet the initial set of {@link MazeObject} instances.
 * @param michelitoDeathHandler the action to be executed when Michelito dies.
 */
public record MazeImpl(Set<MazeObject> mazeObjectsSet, Runnable michelitoDeathHandler) implements Maze {
    @Override
    public boolean addMazeObject(final MazeObject mazeObject) {
        Objects.requireNonNull(mazeObject);
        return this.mazeObjectsSet.add(mazeObject);
    }

    @Override
    public boolean removeMazeObject(final MazeObject mazeObject) {
        Objects.requireNonNull(mazeObject);
        if (mazeObject.equals(this.getPlayer())) {
            michelitoDeathHandler.run();
            return true;
        }
        return this.mazeObjectsSet.remove(mazeObject);
    }

    @Override
    public Set<MazeObject> getAllObjects() {
        return Set.copyOf(this.mazeObjectsSet);
    }

    @Override
    public Set<Wall> getWalls() {
        return this.getObjectsOfType(Wall.class);
    }

    @Override
    public Set<Box> getBoxes() {
        return this.getObjectsOfType(Box.class);
    }

    @Override
    public Set<Updatable> getUpdatable() {
        return this.getObjectsOfType(Updatable.class);
    }

    @Override
    public Player getPlayer() {
        return this.getObjectsOfType(Player.class)
                .stream()
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public Set<Powerup> getPowerup() {
        return this.getObjectsOfType(Powerup.class);
    }

    /**
     * Utility method to filter mazeObjectsSet to a set of one type.
     *
     * @param type is the class type to filter.
     * @return a set with all the object.
     * @param <T> the type of element that will be contained in the output.
     */
    private <T> Set<T> getObjectsOfType(final Class<T> type) {
        return this.mazeObjectsSet.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toSet());
    }

}
