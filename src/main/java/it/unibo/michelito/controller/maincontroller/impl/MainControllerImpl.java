package it.unibo.michelito.controller.maincontroller.impl;

import it.unibo.michelito.controller.gamecontroller.api.GameController;
import it.unibo.michelito.controller.homecontroller.api.HomeController;
import it.unibo.michelito.controller.maincontroller.api.GameParentController;
import it.unibo.michelito.controller.maincontroller.api.HomeParentController;
import it.unibo.michelito.controller.maincontroller.api.MainController;

public class MainControllerImpl implements MainController, HomeParentController, GameParentController {
    private HomeController homeController; // = new HomeControllerImpl(this); can be final
    private GameController gameController; // = new GameControllerImpl(this); can be final

    @Override
    public void start() {
        homeController.showMenu();
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
