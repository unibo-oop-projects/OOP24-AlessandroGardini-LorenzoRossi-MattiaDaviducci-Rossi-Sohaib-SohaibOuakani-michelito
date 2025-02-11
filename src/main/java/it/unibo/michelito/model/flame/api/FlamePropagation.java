package it.unibo.michelito.model.flame.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;

import java.util.List;

public interface FlamePropagation {

    List<Flame> propagate(Position origin, int range, boolean passThrough, Maze maze);
}