package it.unibo.michelito.view.gameview.impl;

import it.unibo.michelito.view.gameview.api.InputHandler;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class InputHandlerImpl implements InputHandler {
    private final Set<Integer> keypressed = new HashSet<>();
    @Override
    public synchronized Set<Integer> keyPressed() {
        return Set.copyOf(this.keypressed);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        this.keypressed.add(e.getKeyCode());
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        this.keypressed.remove(e.getKeyCode());
    }
}
