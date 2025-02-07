package it.unibo.michelito.model.gamegenerator.api;

import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class GameGenerator {
    private static final Integer MAZEBLOCKWHID = 20;
    private static final Integer MAZEBLOCKHIGHT = 15;
    private Integer levelNumber;

    /**
     * @return
     */
     public GameGenerator() {
         this.levelNumber = -1;
     }

    /**
     * @param levelNumber
     * @return
     */
     public static Set<GameObject> generate(Integer levelNumber){
         Set<GameObject> maze = new HashSet<>(basemaze());

         String file = "level"+levelNumber+".txt";
         maze.addAll(mazeFromFile(file));
         return maze;
    }

    /**
     *
     * @return
     */
    Integer getLevelNumber(){
        return this.levelNumber;
    }

    private static Set<GameObject> basemaze(){
        Set<GameObject> maze = new HashSet<>();
        for(int x = 0; x <= MAZEBLOCKWHID; x++){
            for(int y = 0; y <= MAZEBLOCKHIGHT; y++){
                maze.add(new GameObject(ObjectType.BLANK_SPACE, new Position(x*6, y*6)));
            }
        }
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
        return new HashSet<>();
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
                switch (objectType){
                    case "wall":
                        readObject = new GameObject(ObjectType.WALL, new Position(xValue, yValue));
                        break;
                    case "box":
                        readObject = new GameObject(ObjectType.BOX, new Position(xValue, yValue));
                        break;
                    case "enemy":
                        readObject = new GameObject(ObjectType.ENEMY, new Position(xValue, yValue));
                        break;
                    case "door":
                        readObject = new GameObject(ObjectType.DOOR, new Position(xValue, yValue));
                        break;
                    case "player":
                        readObject = new GameObject(ObjectType.PLAYER, new Position(xValue, yValue));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid object type: " + objectType);
                }
                if(cellPositions().contains(readObject.position())){
                    maze.add(readObject);
                }
            }
        } catch (IOException e) {
            System.err.println("Errore nella lettura del file: " + e.getMessage());
        }
        return new HashSet<>();
    }

    private static Set<Position> cellPositions() {
        Set<Position> positions = new HashSet<>();
        for (int x = 0; x <= MAZEBLOCKWHID; x = x + 2) {
            for (int y = 0; y <= MAZEBLOCKHIGHT; y = y + 2) {
                positions.add(new Position(x*6, y*6));
            }
        }
        return positions;
    }
}
