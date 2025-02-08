package it.unibo.michelito.controller.gamecontroller.api;

import it.unibo.michelito.controller.maincontroller.api.Controller;

public interface GameController extends Controller {
    void startGame();

    void stopGame();
}
