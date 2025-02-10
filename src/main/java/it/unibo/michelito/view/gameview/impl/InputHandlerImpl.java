package it.unibo.michelito.view.gameview.impl;

import it.unibo.michelito.view.gameview.api.InputHandler;

import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

class InputHandlerImpl implements InputHandler {
    private final Set<Integer> keysPressed = new HashSet<>();

    @Override
    public synchronized Set<Integer> keysPressed() {
        return Set.copyOf(this.keysPressed);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        this.keysPressed.add(e.getKeyCode());
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        this.keysPressed.remove(e.getKeyCode());
    }
}
