package it.unibo.michelito.controller.objectsadapter.api;

import it.unibo.michelito.model.modelutil.MazeObject;

import java.util.Set;

public interface ObjectsAdapter {
    Set<MazeObject> requestMazeObjects(final int level);
}
