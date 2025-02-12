package it.unibo.michelito.controller.gamecontroller.directionbuilder;

import it.unibo.michelito.controller.gamecontroller.directionbuilder.api.MoveCommandBuilder;
import it.unibo.michelito.controller.gamecontroller.directionbuilder.impl.DirectionBuilderImpl;
import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.controller.playercommand.api.PlayerCommand;
import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.model.player.api.Player;
import it.unibo.michelito.model.player.impl.PlayerImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class TestMoveCommandBuilder {
    public static final double PLAYER_SPEED = 0.01;
    private MoveCommandBuilder directionBuilder;
    private static final  double X_SPAWN = 6;
    private static final double Y_SPAWN = 6;
    private static final long TICK = 1;
    Player player = new PlayerImpl(new Position(X_SPAWN,Y_SPAWN));
    private Maze maze = new MazeImpl(LevelGenerator.testLevel(), new LevelGenerator(e -> { }));

    @BeforeEach
    void setUp() {
        this.directionBuilder = new DirectionBuilderImpl();
    }

    @Test
    void testBuild() {
        assertInstanceOf(PlayerCommand.class, directionBuilder.build());
        directionBuilder.build().execute(player);
        player.update(TICK, maze);
        assertEquals(new Position(X_SPAWN, Y_SPAWN), player.position());

        this.directionBuilder.addDirection(Direction.UP).addDirection(Direction.RIGHT);
        directionBuilder.build().execute(player);
        player.update(TICK, maze);
        assertEquals(new Position(BigDecimal.valueOf(X_SPAWN )
                .add(BigDecimal.valueOf(Math.sqrt(0.5)).multiply(BigDecimal.valueOf(PLAYER_SPEED)))
                .doubleValue(),
                BigDecimal.valueOf(Y_SPAWN).
                        subtract(BigDecimal.valueOf(Math.sqrt(0.5)).multiply(BigDecimal
                                .valueOf(0.01))).doubleValue()), player.position());

        this.directionBuilder = new DirectionBuilderImpl();
        assertThrows(IllegalArgumentException.class, () -> this.directionBuilder
                .addDirection(Direction.RIGHT)
                .addDirection(Direction.RIGHT)
        );

        this.directionBuilder = new DirectionBuilderImpl();
        assertThrows(IllegalArgumentException.class, () -> this.directionBuilder
                .addDirection(Direction.LEFT)
                .addDirection(Direction.LEFT)
        );
    }
}
