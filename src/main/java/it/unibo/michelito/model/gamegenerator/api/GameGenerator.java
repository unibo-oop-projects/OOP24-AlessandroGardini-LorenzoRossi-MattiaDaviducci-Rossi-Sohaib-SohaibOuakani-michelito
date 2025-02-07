package it.unibo.michelito.model.gamegenerator.api;

import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;

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
         Set<GameObject> maze = new HashSet<>();
         for(int x = 0; x < MAZEBLOCKWHID; x++){
             for(int y = 0; y < MAZEBLOCKHIGHT; y++){

             }
         }
         for(int x = 0; x < MAZEBLOCKWHID; x++){
             maze.add(new GameObject(ObjectType.WALL, new Position(x*6, 0)));
             maze.add(new GameObject(ObjectType.WALL, new Position(x*6, MAZEBLOCKHIGHT*6)));
         }
         for(int x = 0; x < MAZEBLOCKHIGHT; x++){
             maze.add(new GameObject(ObjectType.WALL, new Position(0, x*6)));
             maze.add(new GameObject(ObjectType.WALL, new Position(MAZEBLOCKWHID*6, x*6)));
         }

         return new HashSet<>();
    }

    /**
     *
     * @return
     */
    Integer getLevelNumber(){
        return this.levelNumber;
    }
}
