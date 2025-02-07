package it.unibo.michelito.model.gamegenerator.impl;

import it.unibo.michelito.model.gamegenerator.api.GameGenerator;
import it.unibo.michelito.model.modelutil.MazeObject;

import java.util.HashSet;
import java.util.Set;

public class GameGeneratorImpl implements GameGenerator {
    private int currentlevel;

    @Override
    public Set<MazeObject> generate(Integer levelNumber) {
        this.currentlevel = levelNumber;
        Set<MazeObject> mazeObjects = new HashSet<>();
        return mazeObjects;

    }

    @Override
    public Integer getLevelNumber() {
        return currentlevel;
    }
}
