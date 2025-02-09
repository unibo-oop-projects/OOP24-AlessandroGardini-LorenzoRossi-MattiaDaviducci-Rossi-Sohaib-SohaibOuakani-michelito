package it.unibo.michelito.model.player.impl;

import java.math.BigDecimal;
import java.util.Optional;

import it.unibo.michelito.model.blanckspace.api.BlankSpace;
import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.bomb.impl.BombFactoryImpl;
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
    private Direction direction;
    private boolean place;
    private Position currentPosition;
    private int currentBombLimit = STANDARD_BOMB_LIMIT;
    private double currentSpeed = STANDARD_SPEED;
    private BombType bombType = BombType.STANDARD;

    /**
     * Constructor for {@link PlayerImpl}.
     * @param position the spawning {@link Position} of the {@link Player}.
     */
    public PlayerImpl(final Position position) {
        this.currentPosition = position;
        this.setDirection(Direction.NONE);
        this.updateHitbox();
    }

    private void updateHitbox() {
        final HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        this.hitbox = hitBoxFactory.entityeHitBox(this.position());
    }

    @Override
    public final void update(final long deltaTime, final Maze maze) {
        if (!this.direction.equals(Direction.NONE)) {
            this.move(deltaTime, maze);
            this.setDirection(Direction.NONE);
            this.updateHitbox();
            this.checkPowerUp(maze);
        }
        if (this.place) {
            if (this.allowedToPlaceBomb(maze)) {
                this.placeBomb(maze);
            }
            this.place = false;
        }
    }

    private boolean allowedToPlaceBomb(final Maze maze) {
        return maze.getBombs().size() < this.currentBombLimit;
    }

    private void move(final long time, final Maze maze) {
        final Position oldPosition = this.position();
        final Position newPosition = this.calculateNextPosition(time);
        this.setPosition(newPosition);
        this.updateHitbox();

        if (this.checkCollision(maze)) {
            this.setPosition(oldPosition);
            this.updateHitbox();
        }
    }

    private Position calculateNextPosition(final long time) {
        final BigDecimal move = BigDecimal.valueOf(this.currentSpeed).multiply(BigDecimal.valueOf(time));
        final BigDecimal xDisplacement = move.multiply(BigDecimal.valueOf(this.direction.toPosition().x()));
        final BigDecimal yDisplacement = move.multiply(BigDecimal.valueOf(this.direction.toPosition().y()));

        final BigDecimal newX = BigDecimal.valueOf(this.position().x()).add(xDisplacement);
        final BigDecimal newY = BigDecimal.valueOf(this.position().y()).add(yDisplacement);

        return new Position(newX.doubleValue(), newY.doubleValue());
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
        return this.currentPosition;
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
        this.currentBombLimit = newLimit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSpeed(final double newSpeed) {
        this.currentSpeed = newSpeed;
    }

    @Override
    public double getSpeed() {
        return  this.currentSpeed;
    }

    @Override
    public int getBombLimit() {
        return this.currentBombLimit;
    }


    private void setPosition(final Position newPosition) {
        this.currentPosition = newPosition;
    }

    private void placeBomb(final Maze maze) {
        final Optional<BlankSpace> blankSpace = maze.getBlankSpaces().stream()
                .filter(b -> b.getHitBox().collision(this.hitbox))
                .filter(innerblank -> innerblank.getHitBox().inner(this.position()))
                .findAny();

        if (blankSpace.isPresent()) {
            maze.addMazeObject(new BombFactoryImpl().createBomb(blankSpace.get().position(), this.bombType));
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public final void setDirection(final Direction direction) {
        this.direction = direction;
    }

    @Override
    public final void notifyToPlace() {
        this.place = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setBombType(final BombType type) {
        this.bombType = type;
    }

    @Override
    public BombType getBombType() {
        return this.bombType;
    }
}
