package it.unibo.michelito.controller.soundcontroller.impl;

import it.unibo.michelito.controller.soundcontroller.api.SoundController;

import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the {@link SoundController} interface.
 */
public final class SoundControllerImpl implements SoundController {
    private Clip backgroundMusic;
    private final Map<String, Clip> soundEffects = new HashMap<>();
    private static final Logger LOGGER = Logger.getLogger(SoundControllerImpl.class.getName());
    private static final float VOLUME = -15.0f;

    /**
     * Creates a new instance of {@link SoundControllerImpl}.
     */
    private SoundControllerImpl() {
    }

    /**
     * Holder for Singleton instance (Lazy Initialization + Thread Safe).
     */
    private static final class SingletonHelper {
        private static final SoundControllerImpl INSTANCE = new SoundControllerImpl();
    }

    /**
     * Returns the singleton instance of {@link SoundControllerImpl}.
     *
     * @return the singleton instance
     */
    public static SoundControllerImpl getInstance() {
        return SingletonHelper.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playBackgroundMusic(final String filePath) {
        try {
            if (backgroundMusic != null && backgroundMusic.isRunning()) {
                return;
            }
            backgroundMusic = play(filePath);
            backgroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
            backgroundMusic.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            LOGGER.log(Level.SEVERE, "Errore durante la riproduzione della musica di sottofondo: " + filePath, e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopBackgroundMusic() {
        if (backgroundMusic != null && backgroundMusic.isRunning()) {
            backgroundMusic.stop();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void playSoundEffect(final String filePath) {
        try {
            final Clip clip;
            if (soundEffects.containsKey(filePath)) {
                clip = soundEffects.get(filePath);
                if (clip.isRunning()) {
                    clip.stop();
                }
                clip.setFramePosition(0);
            } else {
                clip = play(filePath);
                soundEffects.put(filePath, clip);
            }
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            LOGGER.log(Level.SEVERE, "Errore durante la riproduzione dell'effetto sonoro: " + filePath, e);
        }
    }

    /**
     * Plays an audio file.
     *
     * @param filePath the path of the audio file
     * @return the loaded Clip object
     * @throws UnsupportedAudioFileException if the file is not a supported audio format
     * @throws IOException                   if an I/O error occurs
     * @throws LineUnavailableException      if the line is unavailable
     */
    private Clip play(final String filePath) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        final File soundFile = new File(filePath);
        final AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
        final Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        final FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(VOLUME);
        return clip;
    }
}
