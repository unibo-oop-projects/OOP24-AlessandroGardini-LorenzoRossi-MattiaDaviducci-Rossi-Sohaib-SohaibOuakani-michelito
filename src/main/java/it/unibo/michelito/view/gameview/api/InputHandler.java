package it.unibo.michelito.view.gameview.api;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Set;

public interface InputHandler extends KeyListener {
    public Set<Integer> keyPressed();
}
