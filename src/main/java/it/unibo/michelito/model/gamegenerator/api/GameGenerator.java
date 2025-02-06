package it.unibo.michelito.model.gamegenerator.api;

import it.unibo.michelito.model.modelutil.MazeObject;

import java.util.Set;

/**
 *
 */
public interface GameGenerator {
    /**
     * @param levelNumber
     * @return
     */
    Set<MazeObject> generate(Integer levelNumber);

    /**
     *
     * @return
     */
    Integer getLevelNumber();
}
