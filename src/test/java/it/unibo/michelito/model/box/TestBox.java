package it.unibo.michelito.model.box;

import it.unibo.michelito.model.box.impl.BoxImpl;
import it.unibo.michelito.util.ObjectType;
import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the {@link BoxImpl} class.
 */
final class TestBox {
    private BoxImpl box;
    private Position position;

    /**
     * Sets up the test environment before each test.
     * Initializes a new {@link BoxImpl} instance with a predefined position.
     */
    @BeforeEach
    void setUp() {
        position = new Position(5, 5);
        box = new BoxImpl(position);
    }

    /**
     * Tests the {@code getHitBox} method to ensure it returns the correct hitbox.
     */
    @Test
    void testGetHitBox() {
        HitBox expectedHitBox = new HitBoxFactoryImpl().squareHitBox(position);
        assertEquals(expectedHitBox, box.getHitBox(), "HitBox should match the expected one");
    }

    /**
     * Tests the {@code getType} method to ensure it returns the correct object type.
     */
    @Test
    void testGetType() {
        assertEquals(ObjectType.BOX, box.getType(), "Type should be BOX");
    }
}
