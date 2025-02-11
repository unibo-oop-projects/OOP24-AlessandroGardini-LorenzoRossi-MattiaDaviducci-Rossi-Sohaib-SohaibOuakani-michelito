package it.unibo.michelito.controller.gamecontroller.impl;

import it.unibo.michelito.controller.gamecontroller.api.GameController;
import it.unibo.michelito.controller.gamecontroller.api.GameExceptionHandler;
import it.unibo.michelito.controller.gamecontroller.api.Switcher;
import it.unibo.michelito.controller.gamecontroller.directionbuilder.api.DirectionBuilder;
import it.unibo.michelito.controller.gamecontroller.directionbuilder.impl.DirectionBuilderImpl;
import it.unibo.michelito.controller.gamecontroller.keybinds.Keybindes;
import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.controller.maincontroller.api.GameParentController;
import it.unibo.michelito.controller.playercommand.impl.MoveCommand;
import it.unibo.michelito.controller.playercommand.impl.PlaceCommand;
import it.unibo.michelito.model.gamemanager.api.GameManager;
import it.unibo.michelito.model.gamemanager.impl.GameManagerImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.util.GameObject;
import it.unibo.michelito.view.gameview.api.GameView;
import it.unibo.michelito.view.gameview.impl.GameViewImpl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class GameControllerImpl implements GameController, Switcher, GameExceptionHandler {
    private static final int FPS = 60;
    private static final long TIME_PER_TICK = (long) 100_000.0 / FPS;
    private final GameParentController gameParentController;
    private boolean game;
    private final GameManager gameManager= new GameManagerImpl(new LevelGenerator(this));
    private final GameView gameView = new GameViewImpl(this);
    private final Loop looper = new Loop();

    public GameControllerImpl(GameParentController gameParentController) {
        this.gameParentController = gameParentController;
    }

    @Override
    public void startGame() {
        this.game = true;
        looper.start();
    }

    @Override
    public void stopGame() {
        this.game = false;
    }

    @Override
    public void quit() {
        gameParentController.quit();
    }

    @Override
    public void switchToHome() {
        gameParentController.switchToHome();
    }

    @Override
    public void handleException(Exception e) {

    }

    private class Loop extends Thread {
        public void run() {
            long previousTime = System.currentTimeMillis();
            while (game) {
                long currentTime = System.currentTimeMillis();
                long deltaTime = currentTime - previousTime;

                this.processInput(gameManager, gameView);

                gameManager.update(deltaTime);

                gameView.display(gameManager.getObjects(), gameManager.getRemainingLives());

                this.waitForNextFrame(currentTime);

                previousTime = currentTime;

                if (gameManager.isGameOver()) {
                    game = false;
                    //view.gameOver();
                }
                if (gameManager.isGameWon()) {
                    game = false;
                    //view.gameWon();
                }
            }
        }
        private void waitForNextFrame(long currentTime) {
            long dt = System.currentTimeMillis() - currentTime;
            if (dt < TIME_PER_TICK) {
                try {
                    Thread.sleep(TIME_PER_TICK - dt);
                } catch (Exception ex){throw new RuntimeException("Error in GameControllerImpl", ex);}
            }
        }

        private void processInput(GameManager gameManager, GameView gameView) {
            DirectionBuilder directionBuilder = new DirectionBuilderImpl();
            Set<Keybindes> pressedKeys = gameView.getPressedKeys().stream()
                    .map(Keybindes::getKeybindes)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());

            for (Keybindes keybindes : pressedKeys) {
                switch (keybindes) {
                    case UP -> directionBuilder.addDirection(Direction.UP);
                    case DOWN -> directionBuilder.addDirection(Direction.DOWN);
                    case RIGHT -> directionBuilder.addDirection(Direction.RIGHT);
                    case LEFT -> directionBuilder.addDirection(Direction.LEFT);
                    case PLACE_BOMB -> gameManager.setCommand(new PlaceCommand());
                }
            }
            gameManager.setCommand(new MoveCommand(directionBuilder.build()));
        }
    }
}
