package it.unibo.michelito.model.player.impl;

import java.util.Optional;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.api.PlayerCommand;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.HitBox;

public class PlayerImpl implements Player {
    private static final int STANDARD_BOMB_LIMIT = 1;
    private static final long STANDARD_SPEED = 1;
    private static final long STANDARD_SPEED_UPGRADE = (long) 0.1;
    private static final int STANDARD_BOMB_LIMIT_UPGRADE = 1;
    private Optional<PlayerCommand> command;
    private Position currentPosition;
    private long lastUpdate;
    private int currentBombLimit = STANDARD_BOMB_LIMIT;
    private long currentSpeed = STANDARD_SPEED;

    public PlayerImpl(final Position position) {
        this.currentPosition = position;
        this.emptyCommand();
    }

    private void emptyCommand() {
        this.command = Optional.empty();
    }

    @Override
    public void setCommand(final PlayerCommand command) {
        this.command = Optional.of(command);
    }

    @Override
    public void update(final long time, final Maze maze) {
        if (this.command.isPresent()) {
            this.command.get().execute(this);
            this.emptyCommand();
        }
        this.lastUpdate = time;
    }

    @Override
    public HitBox getHitBox() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Position getPosition() {
        return new Position(this.currentPosition.x(), this.currentPosition.y());
    }

    @Override
    public Type getType() {
        return Type.PLAYER;
    }

    @Override
    public long getLastUpdateTime() {
        return this.lastUpdate;
    }

    @Override
    public void increaseBombLimit() {
        this.currentBombLimit = this.currentBombLimit + STANDARD_BOMB_LIMIT_UPGRADE;
    }

    @Override
    public void increaseSpeed() {
        this.currentSpeed = this.currentSpeed + STANDARD_SPEED_UPGRADE;
    }

    @Override
    public void setPosition(final Position newPosition) {
        this.currentPosition = newPosition;
    }

    @Override
    public void placeBomb(Maze maze) {
        
    }
}
