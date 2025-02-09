package it.unibo.michelito.model.flame.api;

import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.util.Position;

public interface FlameFactory {

    Flame createFlame(Position position, BombType bombType);
}
