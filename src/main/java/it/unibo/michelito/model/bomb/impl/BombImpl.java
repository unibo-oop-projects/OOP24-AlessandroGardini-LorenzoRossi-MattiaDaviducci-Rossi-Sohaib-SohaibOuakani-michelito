package it.unibo.michelito.model.bomb.impl;

import it.unibo.michelito.model.bomb.api.Bomb;
import it.unibo.michelito.model.bomb.api.BombStaticUtil;
import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.flame.impl.FlameFactoryImpl;
import it.unibo.michelito.model.flame.api.FlamePropagation;
import it.unibo.michelito.model.flame.impl.FlamePropagationImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;

import java.util.List;

public class BombImpl implements Bomb {
    private final Position position;
    private final BombType bombType;
    long fuseTime = 3000;

    public BombImpl(final Position position, final BombType bombType) {
        this.position = position;
        this.bombType = bombType;
    }

    @Override
    public void update(final long deltaTime, final Maze maze) {
        this.fuseTime -= deltaTime;
        if (this.fuseTime <= 0) {
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

    @Override
    public BombType getBombType() {
        return bombType;
    }

    private void explode(final Maze maze) {
        maze.removeMazeObject(this);
        generateFlame(maze);
    }

    private void generateFlame(final Maze maze) {
        FlamePropagation flamePropagation = new FlamePropagationImpl(new FlameFactoryImpl());
        List<Flame> flames = flamePropagation.propagate(
                this.position,
                BombStaticUtil.getFlameRange(bombType),
                BombStaticUtil.isFlamePassThrough(bombType),
                maze
        );
        flames.forEach(maze::addMazeObject);
    }
}
