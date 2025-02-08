package it.unibo.michelito.model.enemy.impl;

import it.unibo.michelito.model.enemy.api.Enemy;
import it.unibo.michelito.model.enemy.api.ai.MoodAI;
import it.unibo.michelito.model.enemy.impl.ai.MoodAIImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.api.HitBoxFactory;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import it.unibo.michelito.util.hitbox.api.HitBoxFactory;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;
/**
 * Implementation of {@link Enemy}.
 */
public final class EnemyImpl implements Enemy {
    private Position actualposition;
    private MoodAI moodAI;
    private HitBox hitBox;
    private Direction direction;

    /**
     *Constructor for {@link EnemyImpl}.
     * @param position the initial position of enemy.
     */
    public EnemyImpl(final Position position) {
        this.actualposition = position;
        hitBox = updateHitBox(position);
        direction = Direction.NONE;
    }

    @Override
    public void update(final long deltaTime, final Maze maze) {
        if (moodAI == null) {
            moodAI = new MoodAIImpl(maze);
        }

        direction = moodAI.getDirection();
        verifyHitPlayer(maze);
        this.move(maze, deltaTime);
        moodAI.update(deltaTime);
    }

    @Override
    public Position position() {
        return actualposition;
    }

    @Override
    public HitBox getHitBox() {
        return hitBox;
    }

    @Override
    public ObjectType getType() {
        return ObjectType.ENEMY;
    }

    private HitBox updateHitBox(final Position position) {
        HitBoxFactory hitboxfactory = new HitBoxFactoryImpl();
        return hitboxfactory.entityeHitBox(position);
    }

    private void move(final Maze maze, final long time) {
        BigDecimal xmove = BigDecimal.valueOf(time).multiply(BigDecimal.valueOf(this.direction.toPosition().x()));
        BigDecimal ymove = BigDecimal.valueOf(time).multiply(BigDecimal.valueOf(this.direction.toPosition().y()));

        BigDecimal xvalue = BigDecimal.valueOf(this.position().x()).add(xmove);
        BigDecimal yvalue = BigDecimal.valueOf(this.position().y()).add(ymove);

        Position newposition = new Position(xvalue.doubleValue(), yvalue.doubleValue());

        HitBox newhitbox = updateHitBox(newposition);
        if (!findCollision(maze, newhitbox)) {
            this.actualposition = newposition;
            this.hitBox = newhitbox;
            verifyHitPlayer(maze);
        }
    }

    private boolean findCollision(final Maze maze, final HitBox calshitbox) {
        return maze.getWalls().stream()
                .anyMatch(w -> calshitbox.collision(w.getHitBox()))
                ||
                maze.getBoxes().stream()
                        .anyMatch(b -> calshitbox.collision(b.getHitBox()));
    }

    private void verifyHitPlayer(final Maze maze) {
        if (this.hitBox.collision(maze.getPlayer().getHitBox())) {
            maze.killMichelito();
        }
    }
}
