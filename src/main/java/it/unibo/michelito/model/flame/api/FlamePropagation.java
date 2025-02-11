package it.unibo.michelito.model.flame.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;

import java.util.Set;

public interface FlamePropagation {

    Set<Flame> propagate(Position origin, int range, boolean passThrough, Maze maze);
}