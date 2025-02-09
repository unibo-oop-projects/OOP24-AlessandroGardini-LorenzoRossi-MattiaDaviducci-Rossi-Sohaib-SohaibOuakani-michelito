package it.unibo.michelito.model.flame.impl;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum FlamePropagation {
    UP(Direction.UP),
    DOWN(Direction.DOWN),
    LEFT(Direction.LEFT),
    RIGHT(Direction.RIGHT);

    private final Direction direction;

    FlamePropagation(Direction direction) {
        this.direction = direction;
    }

    public List<HitBox> updateHitBox(Position origin, int range, boolean passThrough, Maze maze) {
        List<HitBox> hitBoxes = new ArrayList<>();
        HitBoxFactoryImpl hitBoxFactory = new HitBoxFactoryImpl();
        Position delta = direction.toPosition();
        for (int i = 1; i <= range; i++) {
            Position newPos = new Position(origin.x() + i * delta.x(), origin.y() + i * delta.y());
            if (isWall(maze, newPos)) {
                break;
            }
            if (isBox(maze, newPos)) {
                removeBox(maze, newPos);
                if (!passThrough) {
                    break;
                }
            }
            hitBoxes.add(hitBoxFactory.squareHitBox(newPos));
        }
        return hitBoxes;
    }

    public static List<HitBox> getFlameHitBoxes(Position origin, int range, boolean passThrough, Maze maze) {
        List<HitBox> allHitBoxes = new ArrayList<>();
        HitBoxFactoryImpl hitBoxFactory = new HitBoxFactoryImpl();
        allHitBoxes.add(hitBoxFactory.squareHitBox(origin));
        Arrays.stream(FlamePropagation.values())
                .map(fp -> fp.updateHitBox(origin, range, passThrough, maze))
                .forEach(allHitBoxes::addAll);
        return allHitBoxes;
    }

    private boolean isWall(Maze maze, Position pos) {
        return maze.getWalls().stream().anyMatch(wall -> wall.position().equals(pos));
    }

    private boolean isBox(Maze maze, Position pos) {
        return maze.getBoxes().stream().anyMatch(box -> box.position().equals(pos));
    }

    private void removeBox(Maze maze, Position pos) {
        maze.getBoxes().stream()
                .filter(box -> box.position().equals(pos))
                .findFirst()
                .ifPresent(maze::removeMazeObject);
    }

}