package it.unibo.michelito.controller.gamecontroller.keybinds;

import java.util.Optional;

import static java.awt.event.KeyEvent.VK_A;
import static java.awt.event.KeyEvent.VK_D;
import static java.awt.event.KeyEvent.VK_W;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_SPACE;

/**
 * Enum for key binds of the game.
 */
public enum KeyBinds {
    /**
     * Keybind for moving up.
     */
    UP(VK_W),

    /**
     * Keybind for moving down.
     */
    DOWN(VK_S),

    /**
     * Keybind for moving left.
     */
    LEFT(VK_A),

    /**
     * Keybind for moving right.
     */
    RIGHT(VK_D),

    /**
     * Keybind for placing a bomb.
     */
    PLACE_BOMB(VK_SPACE);

    private final int keyCode;

    KeyBinds(final int keyCode) {
        this.keyCode = keyCode;
    }

    int getKeyCode() {
        return keyCode;
    }

    /**
     * Method that returns the {@link KeyBinds} associated with the given keyCode.
     * @param keyCode the keyCode.
     * @return the {@link KeyBinds} associated with the given keyCode.
     */
    public static Optional<KeyBinds> getKeyBinds(final int keyCode) {
        for (final KeyBinds keyBinds : values()) {
            if (keyBinds.getKeyCode() == keyCode) {
                return Optional.of(keyBinds);
            }
        }
        return Optional.empty();
    }
}
