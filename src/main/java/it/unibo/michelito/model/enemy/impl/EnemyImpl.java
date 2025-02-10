package it.unibo.michelito.model.enemy.impl;

import it.unibo.michelito.model.enemy.api.Enemy;
import it.unibo.michelito.model.enemy.api.ai.MoodAI;
import it.unibo.michelito.model.enemy.impl.ai.MoodAIImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBoxFactory;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;

import java.math.BigDecimal;
/**
 * Implementation of {@link Enemy}.
 */
public final class EnemyImpl implements Enemy {
    private final int TRY_NEW_DIRECTION_TIME = 300;
    private int countUpdate = 0;
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
        if(countUpdate < TRY_NEW_DIRECTION_TIME) {
            countUpdate++;
        }
        else {
            direction = moodAI.getDirection();
            countUpdate = 0;
        }
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
        final HitBoxFactory hitboxfactory = new HitBoxFactoryImpl();
        return hitboxfactory.entityeHitBox(position);
    }

    private void move(final Maze maze, final long time) {
        final BigDecimal xMove = BigDecimal.valueOf(time).multiply(BigDecimal.valueOf(this.direction.toPosition().x() * 0.1));
        final BigDecimal yMove = BigDecimal.valueOf(time).multiply(BigDecimal.valueOf(this.direction.toPosition().y() * 0.1));

        final BigDecimal xValue = BigDecimal.valueOf(this.position().x()).add(xMove);
        final BigDecimal yValue = BigDecimal.valueOf(this.position().y()).add(yMove);

        final Position newposition = new Position(xValue.doubleValue(), yValue.doubleValue());

        final HitBox newhitbox = updateHitBox(newposition);
        if (!findCollision(maze, newhitbox)) {
            this.actualposition = newposition;
            this.hitBox = newhitbox;
            verifyHitPlayer(maze);
        }
        else{
            searchForNewDirection();
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

    private void searchForNewDirection() {
        Direction newDirection;
        do{
            newDirection = moodAI.getDirection();
        }while (newDirection == direction);
        direction = newDirection;
    }
}
