package it.unibo.michelito.controller.gamecontroller.api;

@FunctionalInterface
public interface GameExceptionHandler {
    void gameControllerHandleException(Exception e);
}
