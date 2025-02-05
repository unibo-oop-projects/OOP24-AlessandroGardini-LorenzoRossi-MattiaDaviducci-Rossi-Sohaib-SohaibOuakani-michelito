package it.unibo.michelito.util.hitbox;

import it.unibo.michelito.util.Position;
import it.unibo.michelito.util.hitbox.api.HitBox;
import it.unibo.michelito.util.hitbox.api.HitBoxFactory;
import it.unibo.michelito.util.hitbox.impl.HitBoxFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Hitbox test.
 */
class TestHitbox {
    /**
     * Test about the entity type hitbox.
     */
    @Test void testEntityHitbox() {
        HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        HitBox h1 = hitBoxFactory.entityeHitBox(new Position(0,0));
        assertNotNull(h1);
        assertNotNull(h1.getHitBox());
        assertEquals(new Position(0,0), h1.getCenter());

        assertTrue(h1.inner(new Position(0,0)));
        assertTrue(h1.inner(new Position(1,1)));
        assertTrue(h1.inner(new Position(-1,-1)));
        assertTrue(h1.inner(new Position(1,2)));
        assertTrue(h1.inner(new Position(-1.5,-2)));
        assertFalse(h1.inner(new Position(-1.6,-2)));
        assertFalse(h1.inner(new Position(1.6,2)));
        assertFalse(h1.inner(new Position(1.6,-2.1)));
        assertFalse(h1.inner(new Position(1.6,2.1)));

        HitBox h2 = hitBoxFactory.entityeHitBox(new Position(0,0));
        assertNotNull(h2);
        assertNotNull(h2.getHitBox());
        assertTrue(h1.equals(h2));

    }

    /**
     * test about the square type hitbox.
     */
    @Test void testSquareHitbox() {
        HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        HitBox h1 = hitBoxFactory.squareHitBox(new Position(0,0));
        assertNotNull(h1);
        assertNotNull(h1.getHitBox());
        assertEquals(new Position(0,0), h1.getCenter());

        assertTrue(h1.inner(new Position(0,0)));
        assertTrue(h1.inner(new Position(1,1)));
        assertTrue(h1.inner(new Position(-1,-1)));
        assertTrue(h1.inner(new Position(1,2)));
        assertTrue(h1.inner(new Position(-2.5,-2.5)));
        assertFalse(h1.inner(new Position(2.6,-2.6)));
        assertFalse(h1.inner(new Position(2.6,2.6)));
        assertFalse(h1.inner(new Position(2.6,-2.6)));

        HitBox h2 = hitBoxFactory.squareHitBox(new Position(0,0));
        assertNotNull(h2);
        assertNotNull(h2.getHitBox());
        assertTrue(h1.equals(h2));
    }

    /**
     * test interaction between two entity type hitbox.
     */
    @Test void testTwoEntityHitbox() {
        HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        HitBox h1 = hitBoxFactory.entityeHitBox(new Position(0,0));
        HitBox h2 = hitBoxFactory.entityeHitBox(new Position(2.75,0));
        HitBox h3 = hitBoxFactory.entityeHitBox(new Position(3,0));
        HitBox h4 = hitBoxFactory.entityeHitBox(new Position(3.1,0));
        HitBox h5 = hitBoxFactory.entityeHitBox(new Position(-2.75,-3.75));
        assertTrue(h1.collision(h2));
        assertTrue(h1.collision(h3));
        assertFalse(h1.collision(h4));
        assertTrue(h1.collision(h5));
    }

    /**
     * test interaction between two square type hitbox.
     */
    @Test void testTwoSquareHitbox() {
        HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        HitBox h1 = hitBoxFactory.squareHitBox(new Position(0,0));
        HitBox h2 = hitBoxFactory.squareHitBox(new Position(4.75,0));
        HitBox h3 = hitBoxFactory.squareHitBox(new Position(5,0));
        HitBox h4 = hitBoxFactory.squareHitBox(new Position(5.1,0));
        HitBox h5 = hitBoxFactory.squareHitBox(new Position(-4.75,-4.75));
        assertTrue(h1.collision(h2));
        assertTrue(h1.collision(h3));
        assertFalse(h1.collision(h4));
        assertTrue(h1.collision(h5));
    }

    /**
     * test interaction between a entity and a square type hitbox.
     */
    @Test void testEntityAndSquareHitbox() {
        HitBoxFactory hitBoxFactory = new HitBoxFactoryImpl();
        HitBox h1 = hitBoxFactory.entityeHitBox(new Position(0,0));
        HitBox h2 = hitBoxFactory.squareHitBox(new Position(3.75,0));
        HitBox h3 = hitBoxFactory.squareHitBox(new Position(4,0));
        HitBox h4 = hitBoxFactory.squareHitBox(new Position(4.1,0));
        HitBox h5 = hitBoxFactory.squareHitBox(new Position(-3.75,-4.25));
        HitBox h6 = hitBoxFactory.squareHitBox(new Position(0,4.51));
        assertTrue(h1.collision(h2));
        assertTrue(h1.collision(h3));
        assertFalse(h1.collision(h4));
        assertTrue(h1.collision(h5));
        assertFalse(h1.collision(h6));
    }

}
