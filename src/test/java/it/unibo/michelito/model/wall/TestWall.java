package it.unibo.michelito.model.wall;

import it.unibo.michelito.model.wall.api.Wall;
import it.unibo.michelito.model.wall.impl.WallImpl;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.model.modelutil.hitbox.impl.HitBoxFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *  Test class for {@link WallImpl}.
 */
final class TestWall {
    /**
     * Tests {@link WallImpl}.
     */
    @Test
    void testWall() {
        final Position position = new Position(0, 0);
        final Wall wall = new WallImpl(position);
        assertNotNull(wall);
        assertEquals(wall.getHitBox(), new HitBoxFactoryImpl().squareHitBox(position));
        assertEquals(wall.position(), position);
    }
}
