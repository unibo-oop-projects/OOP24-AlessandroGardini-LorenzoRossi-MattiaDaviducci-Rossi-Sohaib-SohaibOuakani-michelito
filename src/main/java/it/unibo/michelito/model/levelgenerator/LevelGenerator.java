package it.unibo.michelito.model.levelgenerator;

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
    private static final Integer MAZEBLOCKWHID = 20;
    private static final Integer MAZEBLOCKHIGHT = 15;

    /**
     * @param levelNumber the number of level.
     * @return a set of {@link GameObject} that represent every object in the current level maze.
     */
     public static Set<GameObject> generate(Integer levelNumber){
         Set<GameObject> maze = new HashSet<>(basemaze());
         String file = "src/main/resources/level/level"+levelNumber+".txt";
         maze.addAll(basemaze());
         if(levelNumber != -1){
             maze.addAll(mazeFromFile(file));
         }
         return maze;
    }

    private static Set<GameObject> basemaze(){
        Set<GameObject> maze = new HashSet<>();

        cellPositions().forEach(x -> maze.add(new GameObject(ObjectType.BLANK_SPACE, x)));

        for(int x = 0; x <= MAZEBLOCKWHID; x++){
            maze.add(new GameObject(ObjectType.WALL, new Position(x*6, 0)));
            maze.add(new GameObject(ObjectType.WALL, new Position(x*6, MAZEBLOCKHIGHT*6)));
        }
        for(int x = 0; x <= MAZEBLOCKHIGHT; x++){
            maze.add(new GameObject(ObjectType.WALL, new Position(0, x*6)));
            maze.add(new GameObject(ObjectType.WALL, new Position(MAZEBLOCKWHID*6, x*6)));
        }
        for(int x = 0; x <= MAZEBLOCKWHID; x = x+2){
            for(int y = 0; y <= MAZEBLOCKHIGHT; y = y+2){
                maze.add(new GameObject(ObjectType.WALL, new Position(x*6, y*6)));
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
        for (int x = 0; x <= MAZEBLOCKWHID; x++ ) {
            for (int y = 0; y <= MAZEBLOCKHIGHT; y++ ) {
                positions.add(new Position(x*6, y*6));
            }
        }
        return positions;
    }
}
