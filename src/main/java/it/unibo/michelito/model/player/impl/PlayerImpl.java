package it.unibo.michelito.model.player.impl;

import java.util.Objects;
import java.util.Optional;

import it.unibo.michelito.model.blanckspace.api.BlankSpace;
import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.bomb.impl.BombFactoryImpl;
import it.unibo.michelito.model.components.api.BombManagerComponent;
import it.unibo.michelito.model.components.api.MovementComponent;
import it.unibo.michelito.model.components.impl.BombManagerComponentImpl;
import it.unibo.michelito.model.components.impl.MovementComponentImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.ModifiablePlayer;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBoxFactory;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;
import it.unibo.michelito.model.powerups.api.PowerUp;

/**
 * Implementation of {@link Player}.
 */
public class PlayerImpl implements Player, ModifiablePlayer {
    private static final int STANDARD_BOMB_LIMIT = 1;
    private static final double STANDARD_SPEED = 1;
    private HitBox hitbox;
    private final MovementComponent movementComponent;
    private final BombManagerComponent bombManagerComponent;

    /**
     * Constructor for {@link PlayerImpl}.
     * @param position the spawning {@link Position} of the {@link Player}.
     */
    public PlayerImpl(final Position position) {
        this.movementComponent = new MovementComponentImpl(position, STANDARD_SPEED);
        this.bombManagerComponent = new BombManagerComponentImpl(STANDARD_BOMB_LIMIT);
        this.updateHitbox();
    }

    private void updateHitbox() {
        final HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        this.hitbox = hitBoxFactory.entityeHitBox(this.position());
    }

    @Override
    public final void update(final long deltaTime, final Maze maze) {
            this.move(deltaTime, maze);
            this.updateHitbox();
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
        this.updateHitbox();

        if (this.checkCollision(maze)) {
            this.setPosition(oldPosition);
            this.updateHitbox();
        }
    }

    private boolean checkCollision(final Maze maze) {
        final boolean collisionWalls = maze.getWalls().stream()
                .anyMatch(w -> this.getHitBox().collision(w.getHitBox()));
        final boolean collisionBox = maze.getBoxes().stream()
                .anyMatch(b -> this.getHitBox().collision(b.getHitBox()));
        return collisionWalls || collisionBox;
    }

    private void checkPowerUp(final Maze maze) {
        final Optional<PowerUp> powerUp = maze.getPowerUp().stream()
                .filter(obj -> obj.getType().equals(ObjectType.POWERUP))
                .filter(p -> this.getHitBox().collision(p.getHitBox()))
                .findAny();

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
        return this.hitbox;
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


    private void setPosition(final Position newPosition) {
        this.movementComponent.setPosition(newPosition);
    }

    private void placeBomb(final Maze maze) {
        final Optional<BlankSpace> blankSpace = findBlankToPlaceBomb(maze);

        if (blankSpace.isPresent()) {
            //maze.addMazeObject(new BombFactoryImpl().createBomb(blankSpace.get().position(), this.bombType));
            this.bombManagerComponent.place(maze, blankSpace.get().position());
        } else {
            throw new IllegalStateException();
        }
    }

    private Optional<BlankSpace> findBlankToPlaceBomb(Maze maze) {
        return maze.getBlankSpaces().stream()
                .filter(b -> b.getHitBox().collision(this.hitbox))
                .filter(innerblank -> innerblank.getHitBox().inner(this.position()))
                .findAny();
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
