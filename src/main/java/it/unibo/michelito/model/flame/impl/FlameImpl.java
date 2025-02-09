package it.unibo.michelito.model.flame.impl;

import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.bomb.impl.BombImpl;
import it.unibo.michelito.model.enemy.api.Enemy;
import it.unibo.michelito.model.flame.api.Flame;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

import java.util.ArrayList;
import java.util.List;

public class FlameImpl implements Flame {
    private final Position position;
    private final BombType bombType;
    private long timeLeft;
    private final int range;
    private final boolean passThrough;
    private boolean extinguished;
    private boolean alreadyCheckedCollisions = false;

    public FlameImpl(Position position, BombType bombType) {
        this.position = position;
        this.bombType = bombType;
        this.timeLeft = 1000;
        this.extinguished = false;
        this.range = getRange();
        this.passThrough = isPassThrough(bombType);
    }

    public boolean isExtinguished() {
        return extinguished;
    }

    private void extinguish(Maze maze) {
        extinguished = true;
        if (isExtinguished()) {
            maze.removeMazeObject(this);
        }
    }

    private boolean isPassThrough(BombType bombType) {
        return BombImpl.isFlamePassThrough(bombType);
    }

    private int getRange() {
        return BombImpl.getFlameRange(bombType);
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
    public void update(long deltaTime, Maze maze) {
        timeLeft -= deltaTime;
        if (!extinguished && timeLeft <= 0) {
            extinguish(maze);
        }
        if (!alreadyCheckedCollisions) {
            alreadyCheckedCollisions = true;

            List<HitBox> flameHitBoxes = updateHitBox(maze);

            checkAndKillMichelito(maze, flameHitBoxes);
            checkAndKillEnemies(maze, flameHitBoxes);
        }
    }

    private void checkAndKillMichelito(Maze maze, List<HitBox> flameHitBoxes) {
        Player player = maze.getPlayer();
        Position playerPos = player.position();

        if (flameHitBoxes.stream().anyMatch(hitBox -> hitBox.getCenter().equals(playerPos))) {
            maze.killMichelito();
        }
    }

    private void checkAndKillEnemies(Maze maze, List<HitBox> flameHitBoxes) {
        List<Enemy> enemiesToRemove = new ArrayList<>();

        for (Enemy enemy : maze.getEnemies()) {
            Position enemyPos = enemy.position();
            if (flameHitBoxes.stream().anyMatch(hitBox -> hitBox.getCenter().equals(enemyPos))) {
                enemiesToRemove.add(enemy);
            }
        }

        enemiesToRemove.forEach(maze::removeMazeObject);
    }

    public List<HitBox> updateHitBox(Maze maze) {
        return FlamePropagation.getFlameHitBoxes(this.position, range, passThrough, maze);
    }
}
