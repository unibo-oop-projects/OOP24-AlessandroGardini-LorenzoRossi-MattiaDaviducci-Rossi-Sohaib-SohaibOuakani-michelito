package it.unibo.michelito.controller.gamecontroller.directionbuilder.impl;

import it.unibo.michelito.controller.gamecontroller.directionbuilder.api.MoveCommandBuilder;
import it.unibo.michelito.controller.playercommand.impl.MoveCommand;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;

import java.util.Arrays;

public class DirectionBuilderImpl implements MoveCommandBuilder {
    private double x = 0;
    private double y = 0;
    @Override
    public MoveCommandBuilder addDirection(Direction direction) {
        if (direction.isDiagonal()) {
            throw new IllegalArgumentException("Direction cannot be diagonal");
        } else {
            this.x = this.x + direction.toPosition().x();
            this.y = this.y + direction.toPosition().y();
            if (this.x > 1 || this.y > 1 || this.x < -1 || this.y < -1) {
                throw new IllegalArgumentException("Cannot give the same direction more than once");
            }
            return this;
        }
    }

    @Override
    public MoveCommand build() {
        double magnitude = Math.sqrt(x * x + y * y);
        if(magnitude == 0) {
            return new MoveCommand(Direction.NONE);
        }
        if (Math.abs(x) == 1 && Math.abs(y) == 1) {
            x = x * Math.sqrt(Math.abs(0.5));
            y = y * Math.sqrt(Math.abs(0.5));
        }
        Position position = new Position(this.x, this.y);
        var direction = Arrays.stream(Direction.values())
                .filter(p -> p.toPosition().equals(position))
                .findAny();
        if (direction.isPresent()) {
            return new MoveCommand(direction.get());
        } else {
            throw new IllegalArgumentException("Direction does not exist");
        }
    }
}
