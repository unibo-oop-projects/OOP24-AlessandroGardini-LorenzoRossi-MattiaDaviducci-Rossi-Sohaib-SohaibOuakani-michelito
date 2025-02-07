package it.unibo.michelito.model.enemy.impl;

import it.unibo.michelito.model.enemy.api.Enemy;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;

public class EnemyImpl implements Enemy {
    private Position actualposition;


    EnemyImpl(Position position) {

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
