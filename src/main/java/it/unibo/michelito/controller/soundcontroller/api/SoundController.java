package it.unibo.michelito.controller.soundcontroller.api;

/**
 * Interface for the sound controller.
 */
public interface SoundController {
    /**
     * Plays the background music.
     *
     * @param filePath the path to the music file.
     */
    void playBackgroundMusic(String filePath);

    /**
     * Stops the background music.
     */
    void stopBackgroundMusic();

    /**
     * Plays a sound effect.
     *
     * @param filePath the path to the sound effect file.
     */
    void playSoundEffect(String filePath);
}
