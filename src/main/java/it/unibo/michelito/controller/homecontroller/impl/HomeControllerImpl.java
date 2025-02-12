package it.unibo.michelito.controller.homecontroller.impl;

import it.unibo.michelito.controller.homecontroller.api.HomeController;
import it.unibo.michelito.controller.homecontroller.api.ViewControllerListener;
import it.unibo.michelito.controller.maincontroller.api.HomeParentController;
import it.unibo.michelito.view.homeview.HomeView;

public class HomeControllerImpl implements HomeController, ViewControllerListener {

    private final HomeParentController homeParentController;
    private HomeView homeView;

    public HomeControllerImpl(HomeParentController homeParentController) {
        this.homeParentController = homeParentController;
    }

    @Override
    public void switchToGame() {
        homeParentController.switchToGame();
    }

    @Override
    public void showMenu() {
        homeView = new HomeView(this);
    }

    @Override
    public void hideMenu() {
        homeView.close();
    }

    @Override
    public void quit() {
        homeParentController.quit();
    }

    @Override
    public void handleException(Exception exception) {
        homeParentController.handleException(exception);
    }
}
