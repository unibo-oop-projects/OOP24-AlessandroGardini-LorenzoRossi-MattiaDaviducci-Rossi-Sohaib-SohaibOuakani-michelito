package it.unibo.michelito.model.bomb.api;

import it.unibo.michelito.util.Position;

public interface BombFactory {

    Bomb createBomb(int range, Position position);
}
