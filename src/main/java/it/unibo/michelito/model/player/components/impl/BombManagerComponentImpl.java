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

    /**
     *{@inheritDoc}
     */
    @Override
    public void setBombLimit(final int limit) {
        this.currentBombLimit = limit;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public int getBombLimit() {
        return this.currentBombLimit;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void place(final Maze maze, final Position position) {
        final BombFactory factory = new BombFactoryImpl();
        final MazeObject bomb = factory.createBomb(position, this.bombType);
        if(this.place) {
            maze.addMazeObject(bomb);
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void notifyToPlace(final boolean place) {
        this.place = place;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public boolean hasToPlace() {
        return this.place;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void setBombType(final BombType type) {
        this.bombType = type;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public BombType getBombType() {
        return this.bombType;
    }
}
