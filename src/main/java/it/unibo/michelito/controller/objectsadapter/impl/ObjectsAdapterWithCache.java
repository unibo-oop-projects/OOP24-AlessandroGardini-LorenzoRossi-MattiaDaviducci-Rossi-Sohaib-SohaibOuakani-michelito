package it.unibo.michelito.controller.objectsadapter.impl;

import it.unibo.michelito.controller.objectsadapter.api.ObjectsAdapter;
import it.unibo.michelito.model.modelutil.MazeObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * A proxy implementation of {@link ObjectsAdapter} that caches the results of maze object requests.
 * This class enhances performance by storing previously retrieved sets of {@link MazeObject},
 * avoiding redundant calls to the underlying {@link ObjectsAdapterImpl}.
 * While this class is public, it is recommended to use the {@link ObjectsAdapterFactory} for creating instances.
 */
public class ObjectsAdapterWithCache implements ObjectsAdapter {
    final private ObjectsAdapter base = new ObjectsAdapterImpl();
    final private Map<Integer, Set<MazeObject>> cache = new HashMap<>();

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<MazeObject> requestMazeObjects(final int level) {
        return this.cache.computeIfAbsent(level, base::requestMazeObjects);
    }
}
