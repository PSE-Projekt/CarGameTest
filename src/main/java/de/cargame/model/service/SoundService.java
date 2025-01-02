package de.cargame.model.service;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundService {

    private Clip crashSoundClip;
    private Clip selectSoundClip;
    private Clip changeSelectionSoundClip;
    private Clip collectRewardSoundClip;
    private final String CRASH_SOUND_FILE_PATH = "src/main/resources/sounds/crash.wav";
    private final String SELECT_SOUND_FILE_PATH = "src/main/resources/sounds/select.wav";
    private final String CHANGE_SELECTION_SOUND_FILE_PATH = "src/main/resources/sounds/change_selection.wav";
    private final String COLLECT_REWARD_SOUND_FILE_PATH = "src/main/resources/sounds/collect_reward.wav";

    public SoundService(){
        this.crashSoundClip = loadSoundFile(CRASH_SOUND_FILE_PATH);
        this.selectSoundClip = loadSoundFile(SELECT_SOUND_FILE_PATH);
        this.changeSelectionSoundClip = loadSoundFile(CHANGE_SELECTION_SOUND_FILE_PATH);
        this.collectRewardSoundClip = loadSoundFile(COLLECT_REWARD_SOUND_FILE_PATH);
    }


    public void playCrashSound() {
        crashSoundClip.setMicrosecondPosition(0);
        playSound(crashSoundClip);
    }

    public void playSelectSound() {
        selectSoundClip.setMicrosecondPosition(0);
        playSound(selectSoundClip);
    }

    public void changeSelectionSound() {
        changeSelectionSoundClip.setMicrosecondPosition(0);
        playSound(changeSelectionSoundClip);
    }

    public void collectRewardSound() {
        collectRewardSoundClip.setMicrosecondPosition(0);
        playSound(collectRewardSoundClip);
    }




    private Clip loadSoundFile(String soundFilePath)  {
        File f = new File(soundFilePath);
        Clip clip;
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
        return clip;
    }

    private void playSound(Clip clip) {
        new Thread(clip::start).start();
    }
}
