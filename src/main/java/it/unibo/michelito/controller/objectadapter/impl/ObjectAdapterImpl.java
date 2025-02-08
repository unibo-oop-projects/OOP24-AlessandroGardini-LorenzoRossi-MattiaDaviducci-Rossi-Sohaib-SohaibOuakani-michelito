package it.unibo.michelito.controller.objectadapter.impl;

import it.unibo.michelito.controller.objectadapter.api.ObjectAdapter;
import it.unibo.michelito.model.modelutil.MazeObject;

import java.util.Set;

public class ObjectAdapterImpl implements ObjectAdapter {
    @Override
    public Set<MazeObject> requestMazeObjects(int level) {
        return Set.of();
    }
}
