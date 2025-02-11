package it.unibo.michelito.controller.gamecontroller.api;

/**
 * Interface that models an Entity that notify to switch to the home view
 */
public interface Switcher {

    /**
     * Method that notifies the {@link Switcher} to switch to home
     */
    void switchToHome();
}
