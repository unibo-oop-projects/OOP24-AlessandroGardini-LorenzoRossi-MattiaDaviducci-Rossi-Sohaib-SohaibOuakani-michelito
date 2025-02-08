package it.unibo.michelito.controller.objectsadapter.impl;

import it.unibo.michelito.controller.objectsadapter.api.ObjectsAdapter;
import it.unibo.michelito.model.modelutil.MazeObject;

import java.util.Set;

public class ObjectAdapterImpl implements ObjectsAdapter {
    @Override
    public Set<MazeObject> requestMazeObjects(int level) {
        return Set.of();
    }
}
