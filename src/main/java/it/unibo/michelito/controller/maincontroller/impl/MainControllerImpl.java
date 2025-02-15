package it.unibo.michelito.controller.maincontroller.impl;

import it.unibo.michelito.controller.gamecontroller.api.GameController;
import it.unibo.michelito.controller.gamecontroller.impl.GameControllerImpl;
import it.unibo.michelito.controller.homecontroller.api.HomeController;
import it.unibo.michelito.controller.homecontroller.impl.HomeControllerImpl;
import it.unibo.michelito.controller.maincontroller.api.Controller;
import it.unibo.michelito.controller.maincontroller.api.GameParentController;
import it.unibo.michelito.controller.maincontroller.api.HomeParentController;
import it.unibo.michelito.controller.maincontroller.api.MainController;
import it.unibo.michelito.controller.soundcontroller.api.SoundController;
import it.unibo.michelito.controller.soundcontroller.impl.SoundControllerImpl;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the {@link MainController} interface.
 * <p>
 * This class acts as the central controller of the application, managing both
 * the home menu and the game. It coordinates transitions between the two
 * and handles application termination requests thought {@link HomeParentController}
 * and {@link GameParentController}.
 * </p>
 */
public final class MainControllerImpl implements MainController, HomeParentController, GameParentController {
    private static final Logger LOGGER = Logger.getLogger(MainControllerImpl.class.getName());

    private final HomeController homeController = new HomeControllerImpl(this);
    private final GameController gameController = new GameControllerImpl(this);
    private final SoundController soundController = SoundControllerImpl.getInstance();

    @Override
    public void start() {
        homeController.showMenu();
        soundController.playBackgroundMusic("src/main/resources/sounds/home.wav");
    }

    @Override
    public void switchToGame() {
        homeController.hideMenu();
        soundController.stopBackgroundMusic();
        gameController.startGame();
        soundController.playBackgroundMusic("src/main/resources/sounds/game.wav");
    }

    @Override
    public void switchToHome() {
        gameController.stopGame();
        soundController.stopBackgroundMusic();
        homeController.showMenu();
        soundController.playBackgroundMusic("src/main/resources/sounds/home.wav");
    }

    @Override
    public void quit() {
        soundController.stopBackgroundMusic();
        homeController.hideMenu();
        gameController.stopGame();
    }

    @Override
    public void handleException(final Exception exception) {
        LOGGER.log(Level.SEVERE, "An unexpected error occurred", exception);
        quit();
    }
}
