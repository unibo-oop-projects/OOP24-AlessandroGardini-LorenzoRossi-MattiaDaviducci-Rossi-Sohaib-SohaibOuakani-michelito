package it.unibo.michelito.model.bomb.api;

import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.model.modelutil.Updatable;

public interface Bomb extends Temporary, Updatable {
    boolean isExploded();
    BombType getBombType();
}
