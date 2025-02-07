package it.unibo.michelito.model.player.impl;

import java.math.BigDecimal;
import java.util.Optional;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.api.HitBoxFactory;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;
import it.unibo.michelito.model.powerups.api.PowerUp;

/**
 * Implementation of {@link Player}.
 */
public class PlayerImpl implements Player {
    private static final int STANDARD_BOMB_LIMIT = 1;
    private static final double STANDARD_SPEED = 1;
    private HitBox hitbox;
    private Direction direction;
    private boolean place;
    private Position currentPosition;
    private long lastUpdate;
    private int currentBombLimit = STANDARD_BOMB_LIMIT;
    private double currentSpeed = STANDARD_SPEED;

    /**
     * Constructor for {@link PlayerImpl}.
     * @param position the spawning {@link Position} of the {@link Player}.
     */
    public PlayerImpl(final Position position) {
        this.currentPosition = position;
        this.lastUpdate = 0;
        this.setDirection(Direction.NONE);
        this.updateHitbox();
    }

    private void updateHitbox() {
        final HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        this.hitbox = hitBoxFactory.entityeHitBox(this.position());
    }

    @Override
    public final void update(final long currentTime, final Maze maze) {
        final long deltaTime = currentTime - this.lastUpdate;
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
        this.lastUpdate = currentTime;
    }

    private boolean allowedToPlaceBomb(final Maze maze) {
        //TODO: when maze.getBombs() is added
        return false;
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
        boolean collisionWalls = maze.getWalls().stream()
                .anyMatch(w -> this.getHitBox().collision(w.getHitBox()));
        boolean collisionBox = maze.getBoxes().stream()
                .anyMatch(b -> this.getHitBox().collision(b.getHitBox()));
        return collisionWalls || collisionBox;
    }

    private void checkPowerUp(final Maze maze) {
        final Optional<PowerUp> powerUp = maze.getPowerUp().stream()
                .filter(obj -> obj.getType().equals(Type.POWERUP))
                .filter(p -> this.getHitBox().collision(p.getHitBox())/*p.getHitBox().collision(this.hitbox)*/)
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
    public final Type getType() {
        return Type.PLAYER;
    }

    @Override
    public void increaseBombLimit(int amount) {
        this.currentBombLimit = this.currentBombLimit + amount;
    }

    @Override
    public void increaseSpeed(double speedIncrease) {
        this.currentSpeed = BigDecimal.valueOf(this.currentSpeed).add(BigDecimal.valueOf(speedIncrease)).doubleValue();
    }


    private void setPosition(final Position newPosition) {
        this.currentPosition = newPosition;
    }

    private void placeBomb(final Maze maze) {
        //maze.addMazeObject();
        //TODO: when the Bomb is added
    }

    @Override
    public final void setDirection(final Direction direction) {
        this.direction = direction;
    }

    @Override
    public final void notifyToPlace() {
        this.place = true;
    }
}
