package it.unibo.michelito.model.powerupfactory;

import it.unibo.michelito.model.powerups.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.model.powerups.api.PowerUpType;
import it.unibo.michelito.model.powerups.impl.PowerUpFactoryImpl;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


final class TestPowerUpFactory {
    private PowerUpFactory factory;
    private static final int NUMBER_OF_TRIES = 10_000;
    private final Position spawn = new Position(0, 0);
    @BeforeEach
    void setUp() {
        this.factory = new PowerUpFactoryImpl();
    }

    /*@Test
    void testRandomPowerUp() {
        int tryNumber = NUMBER_OF_TRIES;
        final Optional<PowerUp> powerUp = this.factory.generateRandomPowerUp(new Position(0, 0));
        assertInstanceOf(Optional.class, powerUp);

        while (tryNumber > 0) {
            final Optional<PowerUp> randomPowerUp = this.factory.generateRandomPowerUp(new Position(0, 0));
            if (randomPowerUp.isPresent()) {
                assertInstanceOf(PowerUp.class, randomPowerUp.get());
                break;
            }
            tryNumber--;
        }
        if (tryNumber == 0) {
            fail();
        }

    }*/
    @Test
    void testPowerUpCreation() {
        assertInstanceOf(PowerUp.class, factory.createPowerUp(spawn, PowerUpType.SPEED_POWERUP));
        assertInstanceOf(PowerUp.class, factory.createPowerUp(spawn, PowerUpType.BOMB_TYPE_POWERUP));
        assertInstanceOf(PowerUp.class, factory.createPowerUp(spawn, PowerUpType.BOMB_LIMIT_POWERUP));
    }
}
