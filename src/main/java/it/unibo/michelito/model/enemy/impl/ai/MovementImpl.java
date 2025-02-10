package it.unibo.michelito.model.enemy.impl.ai;

import it.unibo.michelito.model.enemy.api.ai.Movement;
import it.unibo.michelito.model.enemy.api.ai.MovementType;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBoxFactory;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Implementation of {@link Movement}.
 */
public abstract class MovementImpl implements Movement {
    private Position position;
    private Direction direction = Direction.NONE;
    private HitBox hitBox;
    private Set<Direction> directions = Set.of(Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT);

    @Override
    public void setPosition(final Position position){
        this.position = position;
        updateHitBox(position);
    }

    @Override
    public void move(final Maze maze, final long time) {
        if(direction == Direction.NONE || shift(maze,time,this.direction).equals(this.position) ) {
            Random r = new Random();
            List<Direction> possibleway = new ArrayList<>();
            possibleway.add(Direction.NONE);
            possibleway.addAll(directions.stream().filter(x -> !shift(maze,time,x).equals(this.position)).toList());
            this.direction = possibleway.get(r.nextInt(possibleway.size()));
        }

        this.position = shift(maze, time, this.direction);
        this.hitBox = updateHitBox(position);
    }

    @Override
    public MovementType getMovementType() {
        return movementType();
    }

    @Override
    public Position getPosition() {
        return this.position;
    }

    private boolean findCollision(final Maze maze, final HitBox calshitbox) {
        return maze.getWalls().stream()
                .anyMatch(w -> calshitbox.collision(w.getHitBox()))
                ||
                maze.getBoxes().stream()
                        .anyMatch(b -> calshitbox.collision(b.getHitBox()));
    }

    private HitBox updateHitBox(final Position position) {
        final HitBoxFactory hitboxfactory = new HitBoxFactoryImpl();
        return hitboxfactory.entityeHitBox(position);
    }

    private Position shift(final Maze maze, final long time, final Direction direction) {
        final BigDecimal xMove = BigDecimal.valueOf(time).multiply(BigDecimal.valueOf(direction.toPosition().x() * velocity()));
        final BigDecimal yMove = BigDecimal.valueOf(time).multiply(BigDecimal.valueOf(direction.toPosition().y() * velocity()));

        final BigDecimal xValue = BigDecimal.valueOf(this.position.x()).add(xMove);
        final BigDecimal yValue = BigDecimal.valueOf(this.position.y()).add(yMove);

        final Position newposition = new Position(xValue.doubleValue(), yValue.doubleValue());

        final HitBox newhitbox = updateHitBox(newposition);
        if (!findCollision(maze, newhitbox)) {
            return newposition;
        }
        return position;
    }


    abstract double velocity();

    abstract MovementType movementType();
}
