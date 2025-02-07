package it.unibo.michelito.model.door;

import it.unibo.michelito.model.door.api.Door;
import it.unibo.michelito.model.door.impl.DoorImpl;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test class for {@link DoorImpl}.
 */
final class TestDoor {
    private Door door;
    private Position position;

    /**
     * Creates a Door to be used in each test.
     */
    @BeforeEach
    void setUp() {
        position = new Position(0, 0);
        door = new DoorImpl(position);
    }

    /**
     * Tests {@link Position} and {@link HitBox}.
     */
    @Test
    void testPosition() {
        assertNotNull(door);
        assertEquals(door.getHitBox(), new HitBoxFactoryImpl().squareHitBox(position));
        assertEquals(door.position(), position);
    }

    /**
     * Test opening.
     */
    @Test
    void testOpening(){
        //TODO: when enemy has constructor.
    }
}
