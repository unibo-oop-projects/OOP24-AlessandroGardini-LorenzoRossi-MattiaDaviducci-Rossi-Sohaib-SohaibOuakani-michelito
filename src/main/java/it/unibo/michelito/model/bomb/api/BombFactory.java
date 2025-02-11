package it.unibo.michelito.model.bomb.api;

import it.unibo.michelito.util.Position;

public interface BombFactory {

    Bomb createStandardBomb(Position position);

    Bomb createNukeBomb(Position position);

    Bomb createPassThroughBomb(Position position);

    Bomb createLongBomb(Position position);
}
