package it.unibo.michelito.model.wall;

import it.unibo.michelito.model.wall.api.Wall;
import it.unibo.michelito.model.wall.impl.WallImpl;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TestWall test.
 */
class TestWall {
    /**
     * Tests the Wall.
     */
    @Test void testWall() {
        Position position = new Position(0, 0);
        Wall wall = new WallImpl(position);
        assertNotNull(wall);
        assertEquals(wall.getHitBox(), new HitBoxFactoryImpl().squareHitBox(position));
        assertEquals(wall.getPosition(), position);
    }
}
