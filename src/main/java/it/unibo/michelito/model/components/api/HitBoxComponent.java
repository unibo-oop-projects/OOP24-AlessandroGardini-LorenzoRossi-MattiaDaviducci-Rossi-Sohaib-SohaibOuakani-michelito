package it.unibo.michelito.model.components.api;

import it.unibo.michelito.model.blanckspace.api.BlankSpace;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.modelutil.hitbox.api.HitBox;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.util.Position;

import java.util.Optional;

public interface HitBoxComponent {
    HitBox getHitBox();

    void update(Position position);

    boolean checkCollisionWallsBoxes(Maze maze);

    Optional<PowerUp> checkCollisionPowerUp(Maze maze);

    Optional<BlankSpace> closestBlankSpace(Maze maze);
}
