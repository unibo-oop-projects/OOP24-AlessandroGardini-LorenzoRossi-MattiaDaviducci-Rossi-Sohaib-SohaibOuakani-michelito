package it.unibo.michelito.model.components.api;

import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;

public interface MovementComponent {
    void move(long time);

    void setDirection(Direction direction);

    void setSpeed(double speed);

    void setPosition(Position position);

    Position getPosition();
}
