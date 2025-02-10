package it.unibo.michelito.controller.objectsadapter.impl;

import it.unibo.michelito.controller.objectsadapter.api.ObjectsAdapter;

/**
 * Factory class for creating instances of {@link ObjectsAdapter}.
 */
public class ObjectsAdapterFactory {
    /**
     * Private constructor preventing instantiation.
     */
    private ObjectsAdapterFactory() {}

    /**
     * Creates and returns an instance of {@link ObjectsAdapter}.
     * The implementation choice may vary.
     *
     * @return a new {@link ObjectsAdapter} instance.
     */
    public static ObjectsAdapter createObjectsAdapter() {
        return new ObjectsAdapterWithCache();
    }
}
