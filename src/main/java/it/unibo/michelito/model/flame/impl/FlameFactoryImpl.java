package it.unibo.michelito.model.flame.impl;

import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.flame.api.FlameFactory;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;

public class FlameFactoryImpl implements FlameFactory {

    @Override
    public Flame createFlame(final Position position, final Maze maze) {
        return new FlameImpl(position, maze);
    }
}
