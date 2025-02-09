package it.unibo.michelito.model.player.components.impl;

import it.unibo.michelito.model.blanckspace.api.BlankSpace;
import it.unibo.michelito.model.player.components.api.HitBoxComponent;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBoxFactory;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;

import java.util.Optional;

public class HitBoxComponentImpl implements HitBoxComponent {
    private HitBox hitBox;

    public HitBoxComponentImpl(final Position position) {
        final HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        this.hitBox = hitBoxFactory.entityeHitBox(position);
    }

    @Override
    public HitBox getHitBox() {
        return this.hitBox;
    }

    @Override
    public void update(final Position position) {
        final HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        this.hitBox = hitBoxFactory.entityeHitBox(position);
    }

    @Override
    public boolean checkCollisionWallsBoxes(final Maze maze) {
        final boolean collisionWalls = maze.getWalls().stream()
                .anyMatch(w -> this.hitBox.collision(w.getHitBox()));
        final boolean collisionBox = maze.getBoxes().stream()
                .anyMatch(b -> this.hitBox.collision(b.getHitBox()));
        return collisionWalls || collisionBox;
    }

    @Override
    public Optional<PowerUp> checkCollisionPowerUp(final Maze maze) {
        return maze.getPowerUp().stream()
                .filter(obj -> obj.getType().equals(ObjectType.POWERUP))
                .filter(p -> this.getHitBox().collision(p.getHitBox()))
                .findAny();
    }

    @Override
    public Optional<BlankSpace> closestBlankSpace(final Maze maze) {
        return maze.getBlankSpaces().stream()
                .filter(b -> b.getHitBox().collision(this.hitBox))
                .filter(collidingBlanks -> collidingBlanks.getHitBox().inner(this.hitBox.getCenter()))
                .findAny();
    }
}
