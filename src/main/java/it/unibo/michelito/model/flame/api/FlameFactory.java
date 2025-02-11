package it.unibo.michelito.model.flame.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;

public interface FlameFactory {

    Flame createFlame(Position position, Maze maze);
}
