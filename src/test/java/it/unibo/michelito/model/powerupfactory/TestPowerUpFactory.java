package it.unibo.michelito.model.powerupfactory;

import it.unibo.michelito.model.powerups.powerupfactory.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.model.powerups.powerupfactory.impl.PowerUpFactoryImpl;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.fail;


final class TestPowerUpFactory {
    private PowerUpFactory factory;
    private static final int NUMBER_OF_TRIES = 10_000;
    @BeforeEach
    void setUp() {
        this.factory = new PowerUpFactoryImpl();
    }

    @Test
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

    }
}
