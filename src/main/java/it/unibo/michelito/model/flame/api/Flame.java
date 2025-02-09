package it.unibo.michelito.model.flame.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.model.modelutil.Updatable;
import it.unibo.michelito.util.hitbox.api.HitBox;

import java.util.List;

public interface Flame extends Temporary, Updatable {
    List<HitBox> updateHitBox(Maze maze);
    boolean isExtinguished();
}
