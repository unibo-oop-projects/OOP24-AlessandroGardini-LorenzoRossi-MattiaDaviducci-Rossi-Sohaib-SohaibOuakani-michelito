package it.unibo.michelito.util;

import it.unibo.michelito.model.maze.api.Maze;

/**
 * Generic Object of the game, used outside the {@link Maze}.
 *
 * @param objectType the {@link ObjectType}.
 * @param position the {@link Position}.
 */
public record GameObject(ObjectType objectType, Position position) {
}
