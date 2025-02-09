package it.unibo.michelito.controller.levelgenerator;

import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestLevelGenerator {

    @Test void testLevelGeneratorTestLevel() {
        testLevelGeneratorBaseLevel(-1);
    }

    @Test void testLevelGeneratorLevel1() {
        Set<GameObject> basemaze = new HashSet<>(testLevelGeneratorBaseLevel(-1));
        Set<GameObject> maze1 = new HashSet<>(testLevelGeneratorBaseLevel(1));
        assertNotEquals(basemaze, maze1);
        assertTrue(maze1.stream().map(GameObject::objectType).anyMatch(x -> x.equals(ObjectType.DOOR)));
    }

    Set<GameObject> testLevelGeneratorBaseLevel(int level) {
        Set<GameObject> maze = LevelGenerator.generate(level);
        assertFalse(maze.isEmpty());
        assertEquals(1, maze.stream().map(GameObject::objectType).filter(x -> x.equals(ObjectType.PLAYER)).count());
        assertTrue(maze.stream().map(GameObject::objectType).anyMatch(x -> x.equals(ObjectType.WALL)));
        assertTrue(maze.stream().map(GameObject::objectType).anyMatch(x -> x.equals(ObjectType.BLANK_SPACE)));
        assertTrue(maze.stream().anyMatch(x -> x.equals(new GameObject(ObjectType.WALL, new Position(0,0)))));
        assertTrue(maze.stream().anyMatch(x -> x.equals(new GameObject(ObjectType.WALL, new Position(19*6,0)))));
        assertTrue(maze.stream().anyMatch(x -> x.equals(new GameObject(ObjectType.WALL, new Position(0,14*6)))));
        assertTrue(maze.stream().anyMatch(x -> x.equals(new GameObject(ObjectType.WALL, new Position(19*6,14*6)))));
        assertTrue(maze.stream().anyMatch(x -> x.equals(new GameObject(ObjectType.BLANK_SPACE, new Position(0,0)))));
        assertTrue(maze.stream().anyMatch(x -> x.equals(new GameObject(ObjectType.BLANK_SPACE, new Position(19*6,0)))));
        assertTrue(maze.stream().anyMatch(x -> x.equals(new GameObject(ObjectType.BLANK_SPACE, new Position(0,14*6)))));
        assertTrue(maze.stream().anyMatch(x -> x.equals(new GameObject(ObjectType.BLANK_SPACE, new Position(19*6,14*6)))));
        assertTrue(maze.stream().anyMatch(x -> x.equals(new GameObject(ObjectType.WALL, new Position(2*6,2*6)))));
        assertTrue(maze.stream().anyMatch(x -> x.equals(new GameObject(ObjectType.WALL, new Position(4*6,6*6)))));
        return maze;
    }

}
