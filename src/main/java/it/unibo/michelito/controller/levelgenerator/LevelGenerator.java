package it.unibo.michelito.controller.levelgenerator;

import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *A class that create the maze reading form file.
 */
public class LevelGenerator {
    private static final int MAZE_BLOCK_WIDTH = 20;
    private static final int MAZE_BLOCK_HEIGHT = 15;
    private static final int BLOCkEDGE = 6;
    private static final int TEST_MAZE_CODE = -1;

    /**
     * @param levelNumber the number of level.
     * @return a set of {@link GameObject} that represent every object in the current level maze.
     */
     public static Set<GameObject> generate(int levelNumber){
         Set<GameObject> maze = new HashSet<>(baseMaze());
         String file = "src/main/resources/level/level"+levelNumber+".txt";
         maze.addAll(baseMaze());
         if(levelNumber != TEST_MAZE_CODE){
             maze.addAll(mazeFromFile(file));
         }
         else {
             maze.add(new GameObject(ObjectType.PLAYER, new Position(BLOCkEDGE, BLOCkEDGE)));
         }
         return maze;
    }

    private static Set<GameObject> baseMaze(){
        Set<GameObject> maze = new HashSet<>();
        cellPositions().forEach(x -> maze.add(new GameObject(ObjectType.BLANK_SPACE, x)));
        for(int x = 0; x < MAZE_BLOCK_WIDTH; x++){
            maze.add(new GameObject(ObjectType.WALL, new Position(x*BLOCkEDGE, 0)));
            maze.add(new GameObject(ObjectType.WALL, new Position(x*BLOCkEDGE, MAZE_BLOCK_HEIGHT *BLOCkEDGE)));
        }
        for(int x = 0; x < MAZE_BLOCK_HEIGHT; x++){
            maze.add(new GameObject(ObjectType.WALL, new Position(0, x*BLOCkEDGE)));
            maze.add(new GameObject(ObjectType.WALL, new Position(MAZE_BLOCK_WIDTH *BLOCkEDGE, x*BLOCkEDGE)));
        }
        for(int x = 0; x < MAZE_BLOCK_WIDTH; x = x+2){
            for(int y = 0; y < MAZE_BLOCK_HEIGHT; y = y+2){
                maze.add(new GameObject(ObjectType.WALL, new Position(x*BLOCkEDGE, y*BLOCkEDGE)));
            }
        }
        return maze;
    }

    private static Set<GameObject> mazeFromFile(String file){
        Set<GameObject> maze = new HashSet<>();
        String objectType;
        double xValue;
        double yValue;
        GameObject readObject;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String riga;
            while ((riga = br.readLine()) != null) {
                String[] read = riga.split(" ");
                objectType = read[0];
                xValue = Double.parseDouble(read[1]);
                yValue = Double.parseDouble(read[2]);
                readObject = switch (objectType) {
                    case "wall" -> new GameObject(ObjectType.WALL, new Position(xValue, yValue));
                    case "box" -> new GameObject(ObjectType.BOX, new Position(xValue, yValue));
                    case "enemy" -> new GameObject(ObjectType.ENEMY, new Position(xValue, yValue));
                    case "door" -> new GameObject(ObjectType.DOOR, new Position(xValue, yValue));
                    case "player" -> new GameObject(ObjectType.PLAYER, new Position(xValue, yValue));
                    default -> throw new IllegalArgumentException("Invalid object type: " + objectType);
                };
                if(cellPositions().contains(readObject.position())){
                    maze.add(readObject);
                }
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura del file: " + e.getMessage());
        }
        return maze;
    }

    private static Set<Position> cellPositions() {
        Set<Position> positions = new HashSet<>();
        for (int x = 0; x < MAZE_BLOCK_WIDTH; x++ ) {
            for (int y = 0; y < MAZE_BLOCK_HEIGHT; y++ ) {
                positions.add(new Position(x*BLOCkEDGE, y*BLOCkEDGE));
            }
        }
        return positions;
    }
}
