package it.unibo.michelito.model.gamemanager.api;

/**
 * This interface models the GameManager of the Michelito Application.
 * This is the single entry point of the Model.
 */
public interface GameManager {
    void update(long currentTime);

    void start();

    void stop();

    void pause();

    void resume();
}
