package it.unibo.michelito.controller.objectadapter.api;

import it.unibo.michelito.model.modelutil.MazeObject;

import java.util.Set;

public interface ObjectAdapter {
    Set<MazeObject> requestMazeObjects(final int level);
}
