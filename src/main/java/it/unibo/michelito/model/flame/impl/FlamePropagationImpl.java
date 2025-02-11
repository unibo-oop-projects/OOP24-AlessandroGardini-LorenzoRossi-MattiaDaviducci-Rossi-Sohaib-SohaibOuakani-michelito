package it.unibo.michelito.model.flame.impl;

import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.flame.api.FlameFactory;
import it.unibo.michelito.model.flame.api.FlamePropagation;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class FlamePropagationImpl implements FlamePropagation {
    private static final long BLOCK_SIZE = 6;
    private final FlameFactory flameFactory;

    public FlamePropagationImpl(FlameFactory flameFactory) {
        this.flameFactory = flameFactory;
    }

    @Override
    public List<Flame> propagate(Position origin, int range, boolean passThrough, Maze maze) {
        List<Flame> allFlames = new ArrayList<>();
        allFlames.add(flameFactory.createFlame(origin, maze));
        maze.addMazeObject(flameFactory.createFlame(origin, maze));
        for (Direction direction : Direction.values()) {
            Position delta = direction.toPosition();
            if (!(delta.x() != 0 && delta.y() != 0)) {
                allFlames.addAll(createFlames(origin, direction, range, passThrough, maze));
            }
        }
        return allFlames;
    }

    private List<Flame> createFlames(Position origin, Direction direction, int range, boolean passThrough, Maze maze) {
        List<Flame> flames = new ArrayList<>();
        Position delta = direction.toPosition();
        for (int i = 1; i <= range; i++) {
            Position newPos = new Position(
                    BigDecimal.valueOf(origin.x())
                            .add(BigDecimal.valueOf(i).
                                    multiply(BigDecimal.valueOf(delta.x())
                                            .multiply(BigDecimal.valueOf(BLOCK_SIZE))))
                            .doubleValue(),
                    BigDecimal.valueOf(origin.y())
                            .add(BigDecimal.valueOf(i)
                                    .multiply(BigDecimal.valueOf(delta.y())
                                            .multiply(BigDecimal.valueOf(BLOCK_SIZE))))
                            .doubleValue()
            );
            if (isWall(maze, newPos)) {
                break;
            }
            if (isBox(maze, newPos)) {
                maze.removeMazeObject(maze.getBoxes().stream().filter(box -> box.position().equals(newPos)).findFirst().get());
                if (!passThrough) {
                    break;
                }
            }
            Flame flame = flameFactory.createFlame(newPos, maze);
            flames.add(flame);
            maze.addMazeObject(flame);
        }
        return flames;
    }

    private boolean isWall(Maze maze, Position pos) {
        return maze.getWalls().stream().anyMatch(wall -> wall.position().equals(pos));
    }

    private boolean isBox(Maze maze, Position pos) {
        return maze.getBoxes().stream().anyMatch(box -> box.position().equals(pos));
    }

    /*private void removeBox(Maze maze, Position pos) {
        maze.getBoxes().stream()
                .filter(box -> box.position().equals(pos))
                .findFirst()
                .ifPresent(box -> {
                    maze.removeMazeObject(box);
                    if (maze.getDoor().position().equals(pos)) {
                        final PowerUpFactory factory = new PowerUpFactoryImpl();
                        Optional<PowerUp> powerUp = factory.generateRandomPowerUp(pos);
                        powerUp.ifPresent(maze::addMazeObject);
                    }
                });
    }*/
}