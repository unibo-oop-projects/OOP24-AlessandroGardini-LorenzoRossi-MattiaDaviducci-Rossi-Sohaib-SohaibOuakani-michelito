package it.unibo.michelito.model.box;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.model.box.impl.BoxImpl;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TestBox test.
 */
class TestBox {
    /**
     * Tests the Box.
     */
    @Test void testBox() {
        Position position = new Position(0, 0);
        Box box = new BoxImpl(position);
        assertNotNull(box);
        assertEquals(box.getHitBox(), new HitBoxFactoryImpl().squareHitBox(position));
        assertEquals(box.position(), position);
    }
}
