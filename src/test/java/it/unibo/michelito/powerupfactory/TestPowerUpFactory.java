package it.unibo.michelito.powerupfactory;

import it.unibo.michelito.model.powerups.api.PowerUpFactory;
import it.unibo.michelito.model.powerups.api.PowerUp;
import it.unibo.michelito.model.powerups.impl.PowerUpFactoryImpl;
import it.unibo.michelito.util.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

final class TestPowerUpFactory {
    private PowerUpFactory factory;
    @BeforeEach
    void setUp() {
        this.factory = new PowerUpFactoryImpl();
    }

    @Test
    void testRandomPowerUp() {
        int tryNumber = 10000;
        final Optional<PowerUp> powerUp = this.factory.generateRandomPowerUp(new Position(0, 0));
        assertInstanceOf(Optional.class, powerUp);

        while (tryNumber > 0) {
            Optional<PowerUp> randomPowerUp = this.factory.generateRandomPowerUp(new Position(0, 0));
            if (randomPowerUp.isPresent()) {
                assertInstanceOf(PowerUp.class, randomPowerUp.get());
                break;
            }
            tryNumber--;
        }
        if(tryNumber == 0) {
            Assertions.fail();
        }

    }
}
