package it.unibo.michelito.model.box;

import it.unibo.michelito.model.box.api.Box;
import it.unibo.michelito.model.box.impl.BoxImpl;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link BoxImpl}.
 */
final class TestBox {
    /**
     * Tests {@link BoxImpl}.
     */
    @Test
    void testBox() {
        final Position position = new Position(0, 0);
        final Box box = new BoxImpl(position);
        assertNotNull(box);
        assertEquals(box.getHitBox(), new HitBoxFactoryImpl().squareHitBox(position));
        assertEquals(box.position(), position);
    }
}
