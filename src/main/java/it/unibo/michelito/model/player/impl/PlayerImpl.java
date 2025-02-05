package it.unibo.michelito.model.player.impl;

import java.util.Optional;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.api.PlayerCommand;
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
    private Optional<PlayerCommand> command;
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
        this.emptyCommand();
        this.updateHitbox();
    }

    private void updateHitbox() {
        final HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        this.hitbox = hitBoxFactory.entityeHitBox(this.getPosition());
    }

    private void emptyCommand() {
        this.command = Optional.empty();
    }

    @Override
    public final void setCommand(final PlayerCommand command) {
        this.command = Optional.of(command);
    }

    @Override
    public Optional<PlayerCommand> getCommand() {
        return  this.command;
    }

    @Override
    public final void update(final long time, final Maze maze) {
        if (this.command.isPresent()) {
            this.command.get().execute(this);
            this.emptyCommand();
        }
        this.updateHitbox();
        this.lastUpdate = time;
    }

    @Override
    public final HitBox getHitBox() {
        return this.hitbox;
    }

    @Override
    public final Position getPosition() {
        return new Position(this.currentPosition.x(), this.currentPosition.y());
    }

    @Override
    public final Type getType() {
        return Type.PLAYER;
    }

    @Override
    public final long getLastUpdateTime() {
        return this.lastUpdate;
    }

    @Override
    public final void increaseBombLimit() {
        this.currentBombLimit = this.currentBombLimit + STANDARD_BOMB_LIMIT_UPGRADE;
    }

    @Override
    public final void increaseSpeed() {
        this.currentSpeed = this.currentSpeed + STANDARD_SPEED_UPGRADE;
    }

    @Override
    public double getSpeed() {
        return  this.currentSpeed;
    }

    @Override
    public int getBombLimit() {
        return this.currentBombLimit;
    }

    @Override
    public final void setPosition(final Position newPosition) {
        this.currentPosition = newPosition;
    }

    @Override
    public final void placeBomb(final Maze maze) {
        //maze.addMazeObject();
    }
}
