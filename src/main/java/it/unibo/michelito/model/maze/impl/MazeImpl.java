package it.unibo.michelito.model.maze.impl;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.model.wall.api.Wall;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public record MazeImpl(Set<MazeObject> mazeObjectsSet) implements Maze {
    @Override
    public boolean addMazeObject(MazeObject mazeObject) {
        Objects.requireNonNull(mazeObject);
        return this.mazeObjectsSet.add(mazeObject);
    }

    @Override
    public boolean removeMazeObject(MazeObject mazeObject) {
        Objects.requireNonNull(mazeObject);
        //TODO: player control
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

    private <T> Set<T> getObjectsOfType(Class<T> type) {
        return this.mazeObjectsSet.stream()
                .filter(type::isInstance)
                .map(type::cast)
                .collect(Collectors.toSet());
    }

}
