package it.unibo.michelito.model.bomb.impl;

import it.unibo.michelito.model.bomb.api.Bomb;
import it.unibo.michelito.model.bomb.api.BombFactory;
import it.unibo.michelito.util.Position;

/**
 * Implementation of a {@link BombFactory}.
 */
public class BombFactoryImpl implements BombFactory {
    private static final int STANDARD_BOMB_RANGE = 1;
    private static final int NUKE_BOMB_RANGE = 7;
    private static final int PASS_THROUGH_BOMB_RANGE = 3;
    private static final int LONG_BOMB_RANGE = 5;

    /**
     * {@inheritDoc}
     */
    @Override
    public Bomb createStandardBomb(Position position) {
        return new AbstractBomb(position) {
            @Override
            public int getRange() {
                return STANDARD_BOMB_RANGE;
            }

            @Override
            public boolean isPassThrough() {
                return false;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bomb createNukeBomb(Position position) {
        return new AbstractBomb(position) {
            @Override
            public int getRange() {
                return NUKE_BOMB_RANGE;
            }

            @Override
            public boolean isPassThrough() {
                return true;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bomb createPassThroughBomb(Position position) {
        return new AbstractBomb(position) {
            @Override
            public int getRange() {
                return PASS_THROUGH_BOMB_RANGE;
            }

            @Override
            public boolean isPassThrough() {
                return true;
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Bomb createLongBomb(Position position) {
        return new AbstractBomb(position) {
            @Override
            public int getRange() {
                return LONG_BOMB_RANGE;
            }

            @Override
            public boolean isPassThrough() {
                return false;
            }
        };
    }
}
