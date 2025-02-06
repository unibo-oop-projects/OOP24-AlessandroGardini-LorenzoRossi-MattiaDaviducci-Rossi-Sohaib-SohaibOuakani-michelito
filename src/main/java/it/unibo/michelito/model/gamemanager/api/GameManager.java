package it.unibo.michelito.model.gamemanager.api;

public interface GameManager {
    void update(long currentTime);

    void start();

    void stop();

    void pause();

    void resume();
}
