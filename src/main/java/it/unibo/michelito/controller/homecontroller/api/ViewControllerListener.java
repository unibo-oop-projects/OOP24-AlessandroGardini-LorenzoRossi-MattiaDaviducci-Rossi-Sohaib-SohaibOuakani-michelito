package it.unibo.michelito.controller.homecontroller.api;

import it.unibo.michelito.controller.maincontroller.api.Controller;

/**
 * Represents a controller for managing the home menu of the game.
 * Provides methods to display and hide the game menu.
 */
public interface ViewControllerListener extends Controller {
    /**
     * Switches to the game view.
     */
    void switchToGame();
}
