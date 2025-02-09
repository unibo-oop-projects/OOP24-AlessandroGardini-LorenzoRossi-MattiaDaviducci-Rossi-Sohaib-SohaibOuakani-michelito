package it.unibo.michelito.model.player.impl;

import java.util.Objects;
import java.util.Optional;

import it.unibo.michelito.model.blanckspace.api.BlankSpace;
import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.components.api.BombManagerComponent;
import it.unibo.michelito.model.components.api.HitBoxComponent;
import it.unibo.michelito.model.components.api.MovementComponent;
import it.unibo.michelito.model.components.impl.BombManagerComponentImpl;
import it.unibo.michelito.model.components.impl.HitBoxComponentImpl;
import it.unibo.michelito.model.components.impl.MovementComponentImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.ModifiablePlayer;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.powerups.api.PowerUp;

/**
 * Implementation of {@link Player}.
 */
public class PlayerImpl implements Player, ModifiablePlayer {
    private static final int STANDARD_BOMB_LIMIT = 1;
    private static final double STANDARD_SPEED = 1;
    private final HitBoxComponent hitBoxComponent;
    private final MovementComponent movementComponent;
    private final BombManagerComponent bombManagerComponent;

    /**
     * Constructor for {@link PlayerImpl}.
     * @param position the spawning {@link Position} of the {@link Player}.
     */
    public PlayerImpl(final Position position) {
        this.movementComponent = new MovementComponentImpl(position, STANDARD_SPEED);
        this.bombManagerComponent = new BombManagerComponentImpl(STANDARD_BOMB_LIMIT);
        this.hitBoxComponent = new HitBoxComponentImpl(position);
    }

    @Override
    public final void update(final long deltaTime, final Maze maze) {
            this.move(deltaTime, maze);
            this.hitBoxComponent.update(this.position());
            this.checkPowerUp(maze);
        if (this.bombManagerComponent.hasToPlace()) {
            if (this.allowedToPlaceBomb(maze)) {
                this.placeBomb(maze);
            }
            this.bombManagerComponent.notifyToPlace(false);
        }
    }

    private boolean allowedToPlaceBomb(final Maze maze) {
        return maze.getBombs().size() < this.bombManagerComponent.getBombLimit();
    }

    private void move(final long time, final Maze maze) {
        final Position oldPosition = this.position();
        this.movementComponent.move(time);
        this.hitBoxComponent.update(this.position());

        if (this.isCollidingWithWallsOrBoxes(maze)) {
            this.movementComponent.setPosition(oldPosition);
            this.hitBoxComponent.update(this.position());
        }
    }

    private boolean isCollidingWithWallsOrBoxes(final Maze maze) {
        return this.hitBoxComponent.checkCollisionWallsBoxes(maze);
    }

    private void checkPowerUp(final Maze maze) {
        final Optional<PowerUp> powerUp = this.hitBoxComponent.checkCollisionPowerUp(maze);

        if (powerUp.isPresent()) {
            powerUp.get().applyEffect(this);
            maze.removeMazeObject(powerUp.get());
        }
    }

    @Override
    public final Position position() {
        return this.movementComponent.getPosition();
    }

    @Override
    public final HitBox getHitBox() {
        return this.hitBoxComponent.getHitBox();
    }

    @Override
    public final ObjectType getType() {
        return ObjectType.PLAYER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBombLimit(final int newLimit) {
        this.bombManagerComponent.setBombLimit(newLimit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final double newSpeed) {
        this.movementComponent.setSpeed(newSpeed);
    }

    @Override
    public double getSpeed() {
        return this.movementComponent.getSpeed();
    }

    @Override
    public int getBombLimit() {
        return this.bombManagerComponent.getBombLimit();
    }

    private void placeBomb(final Maze maze) {
        final Optional<BlankSpace> blankSpace = this.hitBoxComponent.closestBlankSpace(maze);

        if (blankSpace.isPresent()) {
            this.bombManagerComponent.place(maze, blankSpace.get().position());
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public final void setDirection(final Direction direction) {
        if (Objects.isNull(direction)) {
            throw new IllegalArgumentException();
        } else {
            this.movementComponent.setDirection(direction);
        }
    }

    @Override
    public final void notifyToPlace() {
        this.bombManagerComponent.notifyToPlace(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBombType(final BombType type) {
        this.bombManagerComponent.setBombType(type);
    }

    @Override
    public BombType getBombType() {
        return this.bombManagerComponent.getBombType();
    }
}
