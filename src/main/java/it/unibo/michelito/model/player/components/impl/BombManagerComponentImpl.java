package it.unibo.michelito.model.player.components.impl;

import it.unibo.michelito.model.bomb.api.BombFactory;
import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.bomb.impl.BombFactoryImpl;
import it.unibo.michelito.model.player.components.api.BombManagerComponent;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.util.Position;

public class BombManagerComponentImpl implements BombManagerComponent {
    private int currentBombLimit;
    private boolean place;
    private BombType bombType;

    public BombManagerComponentImpl(final int limit) {
        this.bombType = BombType.STANDARD;
        this.currentBombLimit = limit;
        this.place = false;
    }

    @Override
    public void setBombLimit(final int limit) {
        this.currentBombLimit = limit;
    }

    @Override
    public int getBombLimit() {
        return this.currentBombLimit;
    }

    @Override
    public void place(final Maze maze, final Position position) {
        final BombFactory factory = new BombFactoryImpl();
        final MazeObject bomb = factory.createBomb(position, this.bombType);
        maze.addMazeObject(bomb);
    }

    @Override
    public void notifyToPlace(final boolean place) {
        this.place = place;
    }

    @Override
    public boolean hasToPlace() {
        return this.place;
    }

    @Override
    public void setBombType(final BombType type) {
        this.bombType = type;
    }

    @Override
    public BombType getBombType() {
        return this.bombType;
    }
}
