package it.unibo.michelito.model.bomb.impl;

import it.unibo.michelito.model.bomb.api.Bomb;
import it.unibo.michelito.model.bomb.api.BombFactory;
import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.util.Position;

public class BombFactoryImpl implements BombFactory {

    @Override
    public Bomb createBomb(final Position position, final BombType bombType) {
        return new BombImpl(position, bombType);
    }
}
