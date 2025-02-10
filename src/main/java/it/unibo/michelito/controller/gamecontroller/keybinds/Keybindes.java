package it.unibo.michelito.controller.gamecontroller.keybinds;

import java.util.Optional;

import static java.awt.event.KeyEvent.*;

public enum Keybindes {
    UP (VK_UP),
    DOWN (VK_DOWN),
    LEFT (VK_LEFT),
    RIGHT (VK_RIGHT),
    PLACE_BOMB (VK_SPACE),;

    private final int keyCode;

    Keybindes(final int keyCode) {
        this.keyCode = keyCode;
    }

    int getKeyCode() {
        return keyCode;
    }

    public static Optional<Keybindes> getKeybindes(final int keyCode) {
        for (final Keybindes keybindes : Keybindes.values()) {
            if(keybindes.getKeyCode() == keyCode) {
                return Optional.of(keybindes);
            }
        }
        return Optional.empty();
    }
}
