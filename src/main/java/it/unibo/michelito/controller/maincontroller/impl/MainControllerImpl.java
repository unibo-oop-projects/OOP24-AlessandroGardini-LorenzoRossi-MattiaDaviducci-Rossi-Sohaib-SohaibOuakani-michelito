package it.unibo.michelito.controller.maincontroller.impl;

import it.unibo.michelito.controller.gamecontroller.api.GameController;
import it.unibo.michelito.controller.gamecontroller.impl.GameControllerImpl;
import it.unibo.michelito.controller.homecontroller.api.HomeController;
import it.unibo.michelito.controller.maincontroller.api.GameParentController;
import it.unibo.michelito.controller.maincontroller.api.HomeParentController;
import it.unibo.michelito.controller.maincontroller.api.MainController;

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
    private HomeController homeController; // = new HomeControllerImpl(this); can be final
    private final GameController gameController = new GameControllerImpl(this);

    @Override
    public void start() {
        //homeController.showMenu();
        gameController.startGame();
    }

    @Override
    public void switchToGame() {
        homeController.hideMenu();
        gameController.startGame();
    }

    @Override
    public void switchToHome() {
        gameController.stopGame();
        homeController.showMenu();
    }

    @Override
    public void quit() {
        System.exit(0);
    }
}
