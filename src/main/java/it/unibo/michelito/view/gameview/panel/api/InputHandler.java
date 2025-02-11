package it.unibo.michelito.view.gameview.panel.api;

import java.awt.event.KeyListener;
import java.util.Set;

public interface InputHandler extends KeyListener {
    public Set<Integer> keysPressed();
}
