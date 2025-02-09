package it.unibo.michelito.controller.objectsadapter.api;

import it.unibo.michelito.model.modelutil.MazeObject;

import java.util.Set;

/**
 * Interface for an ObjectsAdapter.
 * Adapter for the LevelGenerator.
 */
public interface ObjectsAdapter {
    /**
     * This method retrieves a set of {@link MazeObject} from the LevelGenerator.
     *
     * @param level the level to generate the set of {@link MazeObject}s.
     * @return a set of {@link MazeObject} representing the level.
     */
    Set<MazeObject> requestMazeObjects(final int level);
}
