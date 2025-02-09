package it.unibo.michelito.model.bomb.impl;

import it.unibo.michelito.model.bomb.api.Bomb;
import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;

public class BombImpl implements Bomb {
    private final Position position;
    private final BombType bombType;
    private boolean exploded;
    private long timerStart;

    public BombImpl(Position position, BombType bombType) {
        this.position = position;
        this.bombType = bombType;
        this.exploded = false;
        startTimer();
    }

    private void startTimer() {
        this.timerStart = System.currentTimeMillis();
    }

    public boolean isExploded() {
        return this.exploded;
    }

    private void explode(Maze maze) {
        this.exploded = true;
        if (isExploded()) {
            maze.removeMazeObject(this);
        }
    }

    private boolean isPassThrough() {
        return switch (bombType) {
            case NUKE -> true;
            case PASS_THROUGH -> true;
            default -> false;
        };
    }

    private int getRange() {
        return switch (bombType) {
            case NUKE -> 7;
            case LONG -> 5;
            case PASS_THROUGH -> 3;
            default -> 1;
        };
    }

    @Override
    public void update(long deltaTime, Maze maze) {
        if (!exploded && deltaTime - timerStart >= 3000) {
            explode(maze);
        }
    }

    @Override
    public Position position() {
        return this.position;
    }

    @Override
    public HitBox getHitBox() {
        return new HitBoxFactoryImpl().squareHitBox(this.position);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.BOMB;
    }
}
