package it.unibo.michelito.model.player;

import it.unibo.michelito.model.box.impl.BoxImpl;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.modelutil.MazeObject;
import it.unibo.michelito.model.modelutil.Temporary;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.model.wall.impl.WallImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestPlayer {

    Player player;
    int levelNumber = -1;
    final Maze maze = new MazeImpl(levelNumber);

    @BeforeEach
    void setUp() {
        this.player = maze.getPlayer();
        /*spwans at (6, 6)*/
    }

    @Test
    void testMovement() {

        assertEquals(new Position(6, 6), player.position());
        player.update(10, this.maze);
        assertEquals(new Position(6, 6), player.position());

        player.setDirection(Direction.RIGHT);
        player.update(10, this.maze);
        assertEquals(new Position(16, 6), player.position());

        player.update(10, this.maze);
        assertEquals(new Position(16, 6), player.position());

        player.setDirection(Direction.LEFT);
        player.update(10, this.maze);
        assertEquals(new Position(6, 6), player.position());

        player.setDirection(Direction.DOWN);
        player.update(10, this.maze);
        assertEquals(new Position(6, 16), player.position());

        player.setDirection(Direction.UP);
        player.update(10, this.maze);
        assertEquals(new Position(6, 6), player.position());
    }

    @Test
    void testBumpInToBox() {

        final Temporary box1 = new BoxImpl(new Position(5, 0));
        maze.addMazeObject(box1);
        player.setDirection(Direction.RIGHT);
        player.update(1, maze);
        assertEquals(new Position(0, 0), player.position());
        maze.removeMazeObject(box1);

        final Temporary box2 = new BoxImpl(new Position(0, 6));
        maze.addMazeObject(box2);
        player.setDirection(Direction.DOWN);
        player.update(1, maze);
        assertEquals(new Position(0, 0), player.position());

        player.setDirection(Direction.RIGHT);
        player.update(10, maze);
        assertEquals(new Position(10, 0), player.position());

    }
}