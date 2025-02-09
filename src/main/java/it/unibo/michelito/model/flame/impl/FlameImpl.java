package it.unibo.michelito.model.flame.impl;

import it.unibo.michelito.model.bomb.api.BombStaticUtil;
import it.unibo.michelito.model.bomb.api.BombType;
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
    private final BombType bombType;
    private long timeLeft;
    private final int range;
    private final boolean passThrough;
    private boolean extinguished;
    private boolean alreadyCheckedCollisions;

    public FlameImpl(final Position position, final BombType bombType) {
        this.position = position;
        this.bombType = bombType;
        this.timeLeft = 1000;
        this.extinguished = false;
        this.range = getRange();
        this.passThrough = isPassThrough();
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
        return null;
    }

    @Override
    public void update(final long deltaTime, final Maze maze) {
        timeLeft -= deltaTime;
        if (!extinguished && timeLeft <= 0) {
            extinguish(maze);
        }
        if (!alreadyCheckedCollisions) {
            alreadyCheckedCollisions = true;

            final List<HitBox> flameHitBoxes = updateHitBox(maze);

            checkAndKillMichelito(maze, flameHitBoxes);
            checkAndKillEnemies(maze, flameHitBoxes);
        }
    }

    @Override
    public boolean isExtinguished() {
        return extinguished;
    }

    @Override
    public List<HitBox> updateHitBox(final Maze maze) {
        return FlamePropagation.getFlameHitBoxes(this.position, range, passThrough, maze);
    }

    private void extinguish(final Maze maze) {
        extinguished = true;
        if (isExtinguished()) {
            maze.removeMazeObject(this);
        }
    }

    private boolean isPassThrough() {
        return BombStaticUtil.isFlamePassThrough(bombType);
    }

    private int getRange() {
        return BombStaticUtil.getFlameRange(bombType);
    }

    private void checkAndKillMichelito(final Maze maze, final List<HitBox> flameHitBoxes) {
        final Player player = maze.getPlayer();
        final Position playerPos = player.position();
        if (flameHitBoxes.stream().anyMatch(hitBox -> hitBox.getCenter().equals(playerPos))) {
            maze.killMichelito();
        }
    }

    private void checkAndKillEnemies(final Maze maze, final List<HitBox> flameHitBoxes) {
        final List<Enemy> enemiesToRemove = new ArrayList<>();
        for (final Enemy enemy : maze.getEnemies()) {
            final Position enemyPos = enemy.position();
            if (flameHitBoxes.stream().anyMatch(hitBox -> hitBox.getCenter().equals(enemyPos))) {
                enemiesToRemove.add(enemy);
            }
        }
        enemiesToRemove.forEach(maze::removeMazeObject);
    }
}
