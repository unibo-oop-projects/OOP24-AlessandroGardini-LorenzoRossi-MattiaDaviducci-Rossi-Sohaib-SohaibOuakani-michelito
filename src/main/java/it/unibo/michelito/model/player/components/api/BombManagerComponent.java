package it.unibo.michelito.model.player.components.api;

import it.unibo.michelito.model.bomb.api.BombType;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;

public interface BombManagerComponent {
    void setBombLimit(int limit);

    int getBombLimit();

    void place(Maze maze, Position position);

    void notifyToPlace(boolean place);

    boolean hasToPlace();

    void setBombType(BombType type);

    BombType getBombType();
}
