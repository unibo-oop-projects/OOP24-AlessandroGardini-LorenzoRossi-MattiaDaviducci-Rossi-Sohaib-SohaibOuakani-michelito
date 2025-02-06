package it.unibo.michelito.model.door.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.Type;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

public class DoorImpl implements Door {
    private boolean open = false;
    final private Position position;

    public DoorImpl(Position position) {
        this.position = position;
    }

    @Override
    public void update(long currentTime, Maze maze) {
        //if (maze.getEnemy().isEmpty) { this.open = true };
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
    public Type getType() {
        return Type.DOOR;
    }

    @Override
    public boolean isOpen() {
        return this.open;
    }
}
