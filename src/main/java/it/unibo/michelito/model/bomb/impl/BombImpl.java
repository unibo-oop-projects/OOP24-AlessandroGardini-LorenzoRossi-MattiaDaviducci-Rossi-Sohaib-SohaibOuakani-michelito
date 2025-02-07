package it.unibo.michelito.model.bomb.impl;

import it.unibo.michelito.model.bomb.api.Bomb;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;

public class BombImpl implements Bomb {
    private final Position position;
    private final int range;
    private boolean exploded;
    private long timerStart;

    public BombImpl(int range, Position position) {
        this.range = range;
        this.position = position;
        this.exploded = false;
        startTimer();
    }

    @Override
    public void startTimer() {
        this.timerStart = System.currentTimeMillis();
    }

    @Override
    public boolean isExploded() {
        return false;
    }

    @Override
    public void explode() {

    }

    @Override
    public boolean isPassThrough() {
        return false;
    }

    @Override
    public int getRange() {
        return 1;
    }

    @Override
    public void update(long currentTime, Maze maze) {

    }

    @Override
    public Position position() {
        return null;
    }

    @Override
    public HitBox getHitBox() {
        return null;
    }

    @Override
    public Type getType() {
        return null;
    }
}
