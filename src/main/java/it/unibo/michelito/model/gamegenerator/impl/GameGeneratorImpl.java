package it.unibo.michelito.model.gamegenerator.impl;

import it.unibo.michelito.model.gamegenerator.api.GameGenerator;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.util.GameObject;

import java.util.HashSet;
import java.util.Set;

public class GameGeneratorImpl {
    private int currentlevel;


    public Set<GameObject> generate(Integer levelNumber) {
        this.currentlevel = levelNumber;
        Set<GameObject> mazeObjects = new HashSet<>();
        return mazeObjects;

    }


    public Integer getLevelNumber() {
        return currentlevel;
    }
}
