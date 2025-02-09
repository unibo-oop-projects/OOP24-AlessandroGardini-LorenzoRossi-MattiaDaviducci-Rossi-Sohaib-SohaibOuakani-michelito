package it.unibo.michelito.model.flame.impl;

import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.flame.api.FlameFactory;
import it.unibo.michelito.util.Position;

public class FlameFactoryImpl implements FlameFactory {

    @Override
    public Flame createFlame(Position position, BombType bombType) {
        return new FlameImpl(position, bombType);
    }
}
