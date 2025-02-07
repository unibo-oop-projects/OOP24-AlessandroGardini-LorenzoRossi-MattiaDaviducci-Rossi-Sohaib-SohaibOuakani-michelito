package it.unibo.michelito.model.maze.api;

import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;

public record GameObject(ObjectType objectType, Position position) {
}
