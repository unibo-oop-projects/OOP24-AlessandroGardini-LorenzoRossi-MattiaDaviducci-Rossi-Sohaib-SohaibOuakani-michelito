package it.unibo.michelito.controller.maincontroller.impl;

import it.unibo.michelito.controller.gamecontroller.api.GameController;
import it.unibo.michelito.controller.homecontroller.api.HomeController;
import it.unibo.michelito.controller.maincontroller.api.GameParentController;
import it.unibo.michelito.controller.maincontroller.api.HomeParentController;
import it.unibo.michelito.controller.maincontroller.api.MainController;

/**
 * Implementation of the {@link MainController} interface.
 * <p>
 * This class acts as the central controller of the application, managing both
 * the home menu and the game. It coordinates transitions between the two
 * and handles application termination requests.
 * </p>
 */
public class MainControllerImpl implements MainController, HomeParentController, GameParentController {
    private HomeController homeController; // = new HomeControllerImpl(this); can be final
    private GameController gameController; // = new GameControllerImpl(this); can be final

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        homeController.showMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToGame() {
        homeController.hideMenu();
        gameController.startGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToHome() {
        gameController.stopGame();
        homeController.showMenu();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        System.exit(0);
    }

}
