package it.unibo.michelito.controller.gamecontroller.api;

@FunctionalInterface
public interface GameExceptionHandler {
    void handleException(Exception e);
}
