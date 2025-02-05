package it.unibo.michelito.model.player.impl;

import java.util.Optional;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.api.PlayerCommand;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.api.HitBoxFactory;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

/**
 * implementation of Player.
 */
public class PlayerImpl implements Player {
    private static final int STANDARD_BOMB_LIMIT = 1;
    private static final double STANDARD_SPEED = 1;
    private static final double STANDARD_SPEED_UPGRADE = 0.1;
    private static final int STANDARD_BOMB_LIMIT_UPGRADE = 1;
    private HitBox hitbox;
    private Direction direction;
    private boolean place;
    private Position currentPosition;
    private long lastUpdate;
    private int currentBombLimit = STANDARD_BOMB_LIMIT;
    private double currentSpeed = STANDARD_SPEED;

    /**
     * Constructor for PlayerImpl.
     * @param position the spawning position of the Player.
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
    public final void update(final long time, final Maze maze) {
        if (!this.direction.equals(Direction.NONE)) {
            this.move(time, maze);
            this.setDirection(Direction.NONE);
            this.updateHitbox();
        }
        if (this.place) {
            if (this.allowedToPlaceBomb(maze)) {
                this.placeBomb(maze);
            }
            this.place = false;
        }
        this.lastUpdate = time;
    }

    private boolean allowedToPlaceBomb(final Maze maze) {
        //prendi le bombe e conta se sono meno di quelle max piazzabili.
        return false;
    }

    private void move(final long time, Maze maze) {
        final Position oldPosition = this.position();
        final Position movement = new Position(this.direction.toPosition().x() * STANDARD_SPEED * time, this.direction.toPosition().y() * STANDARD_SPEED * time);
        this.setPosition(new Position(this.position().x() + movement.x(), this.position().y() + movement.y()));
        this.updateHitbox();

        if (maze.getWalls().stream()
                .anyMatch(w -> this.getHitBox().collision(w.getHitBox())) ||
            maze.getBoxes().stream()
                .anyMatch(b -> this.getHitBox().collision(b.getHitBox()))
        ) {
            this.setPosition(oldPosition);
            this.updateHitbox();
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
    public final void increaseBombLimit() {
        this.currentBombLimit = this.currentBombLimit + STANDARD_BOMB_LIMIT_UPGRADE;
    }

    @Override
    public final void increaseSpeed() {
        this.currentSpeed = this.currentSpeed + STANDARD_SPEED_UPGRADE;
    }

    private void setPosition(final Position newPosition) {
        this.currentPosition = newPosition;
    }

    private void placeBomb(final Maze maze) {
        //maze.addMazeObject();
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
