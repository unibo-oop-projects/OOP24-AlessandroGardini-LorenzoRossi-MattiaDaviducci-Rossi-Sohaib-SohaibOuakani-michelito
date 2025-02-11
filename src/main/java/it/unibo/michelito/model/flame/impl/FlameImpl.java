package it.unibo.michelito.model.flame.impl;

import it.unibo.michelito.model.enemy.api.Enemy;
import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class FlameImpl implements Flame {
    private final Position position;
    private long timeLeft;
    private boolean extinguished;

    public FlameImpl(final Position position, final Maze maze) {
        this.position = position;
        this.timeLeft = 1000;
        this.extinguished = false;
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public HitBox getHitBox() {
        return new HitBoxFactoryImpl().squareHitBox(this.position);
    }

    @Override
    public ObjectType getType() {
        return ObjectType.FLAME;
    }

    @Override
    public void update(final long deltaTime, final Maze maze) {
        timeLeft -= deltaTime;
        if (!extinguished && timeLeft <= 0) {
            extinguish(maze);
        }
        checkAndKillMichelito(maze, getHitBox());
        checkAndKillEnemies(maze, getHitBox());
    }

    @Override
    public boolean isExtinguished() {
        return extinguished;
    }

    private void extinguish(final Maze maze) {
        extinguished = true;
        if (isExtinguished()) {
            maze.removeMazeObject(this);
        }
    }

    private void checkAndKillMichelito(final Maze maze, final HitBox flameHitBox) {
        final Player player = maze.getPlayer();
        if (flameHitBox.collision(player.getHitBox())) {
            maze.killMichelito();
        }
    }

    private void checkAndKillEnemies(final Maze maze, final HitBox flameHitBox) {
        final List<Enemy> enemiesToRemove = new ArrayList<>();
        for (final Enemy enemy : maze.getEnemies()) {
            if (flameHitBox.collision(enemy.getHitBox())) {
                enemiesToRemove.add(enemy);
            }
        }
        enemiesToRemove.forEach(maze::removeMazeObject);
    }
}
