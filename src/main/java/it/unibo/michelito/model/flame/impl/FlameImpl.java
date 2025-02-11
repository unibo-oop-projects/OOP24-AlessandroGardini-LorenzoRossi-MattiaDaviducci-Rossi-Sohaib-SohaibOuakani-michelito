package it.unibo.michelito.model.flame.impl;

import it.unibo.michelito.model.enemy.api.Enemy;
import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;

import java.util.HashSet;
import java.util.Set;

public class FlameImpl implements Flame {
    private final Position position;
    private long timeLeft;

    public FlameImpl(final Position position) {
        this.position = position;
        this.timeLeft = 1000;
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
        computeTimeToExtinguish(maze, deltaTime);
        checkAndKillMichelito(maze, getHitBox());
        checkAndKillEnemies(maze, getHitBox());
    }

    private void computeTimeToExtinguish(final Maze maze, final long deltaTime) {
        this.timeLeft -= deltaTime;
        if (this.timeLeft <= 0) {
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
        final Set<Enemy> enemiesToRemove = new HashSet<>();
        for (final Enemy enemy : maze.getEnemies()) {
            if (flameHitBox.collision(enemy.getHitBox())) {
                enemiesToRemove.add(enemy);
            }
        }
        enemiesToRemove.forEach(maze::removeMazeObject);
    }
}
