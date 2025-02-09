package it.unibo.michelito.model.bomb.impl;

import it.unibo.michelito.model.bomb.api.Bomb;
import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.flame.impl.FlameFactoryImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

public class BombImpl implements Bomb {
    private final Position position;
    private final BombType bombType;
    private boolean exploded;
    private final int fuseTime = 3000;

    public BombImpl(Position position, BombType bombType) {
        this.position = position;
        this.bombType = bombType;
        this.exploded = false;
    }

    public boolean isExploded() {
        return this.exploded;
    }

    public BombType getBombType() {
        return bombType;
    }

    private void explode(Maze maze) {
        this.exploded = true;
        if (isExploded()) {
            maze.removeMazeObject(this);
        }
        generateFlame(maze);
    }

    private void generateFlame(Maze maze) {
        Flame flame = new FlameFactoryImpl().createFlame(this.position, bombType);
        maze.addMazeObject(flame);
    }

    public static boolean isFlamePassThrough(BombType bombType) {
        return switch (bombType) {
            case NUKE, PASS_THROUGH -> true;
            default -> false;
        };
    }

    public static int getFlameRange(BombType bombType) {
        return switch (bombType) {
            case NUKE -> 7;
            case LONG -> 5;
            case PASS_THROUGH -> 3;
            default -> 1;
        };
    }

    @Override
    public void update(long deltaTime, Maze maze) {
        long remainingTime = fuseTime - deltaTime;
        if (!exploded && remainingTime <= 0) {
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
