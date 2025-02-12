package it.unibo.michelito.controller.gamecontroller.keybinds;

import java.util.Optional;

import static java.awt.event.KeyEvent.*;

public enum KeyBinds {
    UP (VK_W),
    DOWN (VK_S),
    LEFT (VK_A),
    RIGHT (VK_D),
    PLACE_BOMB (VK_SPACE);

    private final int keyCode;

    KeyBinds(final int keyCode) {
        this.keyCode = keyCode;
    }

    int getKeyCode() {
        return keyCode;
    }

    public static Optional<KeyBinds> getKeyBinds(final int keyCode) {
        for (final KeyBinds keyBinds : KeyBinds.values()) {
            if(keyBinds.getKeyCode() == keyCode) {
                return Optional.of(keyBinds);
            }
        }
        return Optional.empty();
    }
}
