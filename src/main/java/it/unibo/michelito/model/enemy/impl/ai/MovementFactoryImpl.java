package it.unibo.michelito.model.enemy.impl.ai;

import it.unibo.michelito.model.enemy.api.ai.MovementFactory;
import it.unibo.michelito.model.enemy.api.ai.Movement;
import it.unibo.michelito.model.enemy.api.ai.MovementType;
import it.unibo.michelito.util.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link MovementFactory}.
 */
public final class MovementFactoryImpl implements MovementFactory {
    @Override
    public Movement chilling() {
        return  new MovementImpl() {
            @Override
             double velocity() {
                return 0.02;
            }

            @Override
            MovementType movementType() {
                return MovementType.CHILLING;
            }
        };
    }

    @Override
    public Movement sleeping() {
        return new MovementImpl() {
            @Override
            double velocity() {
                return 0;
            }

            @Override
            MovementType movementType() {
                return MovementType.SLEEPING;
            }
        };
    }

    @Override
    public Movement searching() {
        return new MovementImpl() {
            @Override
            double velocity() {
                return 0.05;
            }

            @Override
            MovementType movementType() {
                return MovementType.SEARCHING;
            }
        };
    }
}
