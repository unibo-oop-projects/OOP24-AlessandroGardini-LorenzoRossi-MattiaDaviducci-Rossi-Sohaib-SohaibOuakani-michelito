package it.unibo.michelito.model.flame.api;

import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.model.modelutil.Updatable;

public interface Flame extends Temporary, Updatable {
    boolean isExtinguished();
}
