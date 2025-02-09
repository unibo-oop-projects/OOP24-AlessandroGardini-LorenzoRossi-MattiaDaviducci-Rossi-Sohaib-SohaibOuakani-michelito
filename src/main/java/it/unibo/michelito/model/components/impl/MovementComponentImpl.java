package it.unibo.michelito.model.components.impl;

import it.unibo.michelito.model.components.api.MovementComponent;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;

import java.math.BigDecimal;

public class MovementComponentImpl implements MovementComponent {
    private static final double STANDARD_SPEED = 1;
    private Direction direction;
    private Position position;
    private double speed;

    public MovementComponentImpl(final Position position) {
        this.position = position;
        this.speed = STANDARD_SPEED;
        this.direction = Direction.NONE;
    }

    @Override
    public void move(final long time) {
        this.setPosition(calculateNextPosition(time));
        this.setDirection(Direction.NONE);
    }

    private Position calculateNextPosition(final long time) {
        final BigDecimal move = BigDecimal.valueOf(this.speed).multiply(BigDecimal.valueOf(time));
        final BigDecimal xDisplacement = move.multiply(BigDecimal.valueOf(this.direction.toPosition().x()));
        final BigDecimal yDisplacement = move.multiply(BigDecimal.valueOf(this.direction.toPosition().y()));

        final BigDecimal newX = BigDecimal.valueOf(this.position.x()).add(xDisplacement);
        final BigDecimal newY = BigDecimal.valueOf(this.position.y()).add(yDisplacement);

        return new Position(newX.doubleValue(), newY.doubleValue());
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    @Override
    public double getSpeed() {
        return this.speed;
    }
}
