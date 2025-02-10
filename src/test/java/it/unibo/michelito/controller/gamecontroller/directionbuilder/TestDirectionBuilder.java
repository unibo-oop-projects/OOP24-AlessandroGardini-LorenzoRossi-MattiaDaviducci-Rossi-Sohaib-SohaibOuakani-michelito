package it.unibo.michelito.controller.gamecontroller.directionbuilder;

import it.unibo.michelito.controller.gamecontroller.directionbuilder.api.DirectionBuilder;
import it.unibo.michelito.controller.gamecontroller.directionbuilder.impl.DirectionBuilderImpl;
import it.unibo.michelito.util.Direction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class TestDirectionBuilder {
    private DirectionBuilder directionBuilder;

    @BeforeEach
    void setUp() {
        this.directionBuilder = new DirectionBuilderImpl();
    }

    @Test
    void testBuild() {
        assertEquals(Direction.NONE, directionBuilder.build());

        this.directionBuilder.addDirection(Direction.UP).addDirection(Direction.RIGHT);
        assertEquals(Direction.UP_RIGHT, directionBuilder.build());

        this.directionBuilder = new DirectionBuilderImpl();
        this.directionBuilder.addDirection(Direction.DOWN).addDirection(Direction.LEFT);
        assertEquals(Direction.DOWN_LEFT, directionBuilder.build());

        this.directionBuilder = new DirectionBuilderImpl();
        this.directionBuilder.addDirection(Direction.UP).addDirection(Direction.LEFT);
        assertEquals(Direction.UP_LEFT, directionBuilder.build());

        this.directionBuilder = new DirectionBuilderImpl();
        this.directionBuilder.addDirection(Direction.DOWN).addDirection(Direction.RIGHT);
        assertEquals(Direction.DOWN_RIGHT, directionBuilder.build());

        this.directionBuilder = new DirectionBuilderImpl();
        this.directionBuilder.addDirection(Direction.RIGHT);
        assertEquals(Direction.RIGHT, directionBuilder.build());

        this.directionBuilder = new DirectionBuilderImpl();
        this.directionBuilder.addDirection(Direction.UP);
        assertEquals(Direction.UP, directionBuilder.build());

        this.directionBuilder = new DirectionBuilderImpl();
        assertEquals(Direction.NONE, this.directionBuilder.addDirection(Direction.RIGHT).addDirection(Direction.LEFT).build());


        this.directionBuilder = new DirectionBuilderImpl();
        assertThrows(IllegalArgumentException.class, () -> this.directionBuilder.addDirection(Direction.RIGHT).addDirection(Direction.RIGHT));

        this.directionBuilder = new DirectionBuilderImpl();
        assertThrows(IllegalArgumentException.class, () -> this.directionBuilder.addDirection(Direction.LEFT).addDirection(Direction.LEFT));
    }
}
