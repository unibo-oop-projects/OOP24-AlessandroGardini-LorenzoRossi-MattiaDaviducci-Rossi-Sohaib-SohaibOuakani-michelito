package it.unibo.michelito.model.player.components.impl;

import it.unibo.michelito.model.bomb.api.BombFactory;
import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.bomb.impl.BombFactoryImpl;
import it.unibo.michelito.model.player.components.api.BombManagerComponent;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.model.bomb.api.Bomb;

/**
 * Implementation of the {@link BombManagerComponent} interface.
 * This class manages {@link Bomb} placement and configuration within the game.
 */
public class BombManagerComponentImpl implements BombManagerComponent {
    private static final int STANDARD_BOMB_LIMIT = 1;
    private int currentBombLimit;
    private boolean place;
    private BombType bombType;

    /**
     * Constructs a {@link BombManagerComponentImpl} with default values.
     * Default bomb type is {@code BombType.STANDARD},
     * and the initial {@link Bomb} limit is set to {@value #STANDARD_BOMB_LIMIT}.
     */
    public BombManagerComponentImpl() {
        this.bombType = BombType.STANDARD;
        this.currentBombLimit = STANDARD_BOMB_LIMIT;
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
        if (this.place) {
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
