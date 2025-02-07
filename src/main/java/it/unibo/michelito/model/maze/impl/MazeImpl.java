package it.unibo.michelito.model.maze.impl;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.model.door.api.Door;
import it.unibo.michelito.model.enemy.api.Enemy;
import it.unibo.michelito.model.gamegenerator.api.GameGenerator;
import it.unibo.michelito.model.gamegenerator.impl.GameGeneratorImpl;
import it.unibo.michelito.model.maze.api.Level;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.model.wall.api.Wall;
import it.unibo.michelito.model.maze.api.GameObject;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * An implementation of the {@link Maze} interface representing a maze in the game.
 * <p>
 * This class manages the maze objects, including walls, boxes, enemies, power-ups, and more.
 * It provides methods to interact with and manipulate these objects within the maze.
 * </p>
 */
public final class MazeImpl implements Maze, Level {
    private final Set<MazeObject> mazeObjectsSet;
    private boolean won;
    private boolean lost;

    public MazeImpl(final int levelNumber) {
        GameGenerator gameGenerator = new GameGeneratorImpl();
        mazeObjectsSet = new HashSet<>(gameGenerator.generate(levelNumber));
    }

    @Override
    public void update(long currentTime) {
        this.getUpdatable().forEach(u -> u.update(currentTime, this));
    }

    @Override
    public Set<GameObject> getObjects() {
        return this.getAllObjects().stream()
                .map(m -> new GameObject(m.getType(), m.position()))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isWon() {
        return this.won;
    }

    @Override
    public boolean isLost() {
        return this.lost;
    }

    @Override
    public boolean addMazeObject(final MazeObject mazeObject) {
        Objects.requireNonNull(mazeObject);
        return this.mazeObjectsSet.add(mazeObject);
    }

    @Override
    public boolean removeMazeObject(final Temporary temporaryObject) {
        Objects.requireNonNull(temporaryObject);
        return this.mazeObjectsSet.remove(temporaryObject);
    }

    @Override
    public void killMichelito() {
        this.lost = true;
    }

    @Override
    public void enterTheDoor() {
        this.won = true;
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
    public Set<PowerUp> getPowerUp() {
        return this.getObjectsOfType(PowerUp.class);
    }

    @Override
    public Door getDoor() {
        return this.getObjectsOfType(Door.class)
                .stream()
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    @Override
    public Set<Enemy> getEnemies() {
        return this.getObjectsOfType(Enemy.class);
    }

    /**
     * A utility method to filter and retrieve all objects of a specific type from the maze.
     *
     * @param type the class type to filter the objects by.
     * @param <T>  the type of element that will be contained in the output set.
     * @return a {@link Set} of objects of the specified type.
     */
    private <T> Set<T> getObjectsOfType(final Class<T> type) {
        return this.mazeObjectsSet.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toSet());
    }

    @Override
    public int hashCode() {
        return Objects.hash(mazeObjectsSet);
    }

    @Override
    public String toString() {
        return "MazeImpl{" +
                "mazeObjectsSet=" + mazeObjectsSet +
                ", won=" + won +
                ", lost=" + lost +
                '}';
    }
}
