package de.cargame.model.service;

import de.cargame.model.service.entity.sound.*;

public class SoundService {

    private final String CRASH_SOUND_FILE_PATH = "src/main/resources/sounds/crash.wav";
    private final String SELECT_SOUND_FILE_PATH = "src/main/resources/sounds/select.wav";
    private final String CHANGE_SELECTION_SOUND_FILE_PATH = "src/main/resources/sounds/change_selection.wav";
    private final String COLLECT_REWARD_SOUND_FILE_PATH = "src/main/resources/sounds/collect_reward.wav";

    private final SoundClip crashSoundClip;
    private final SoundClip selectSoundClip;
    private final SoundClip changeSelectionSoundClip;
    private final SoundClip collectRewardSoundClip;

    public SoundService() {
        this.crashSoundClip = new CrashSound();
        this.selectSoundClip = new SelectSound();
        this.changeSelectionSoundClip = new ChangeSelectionSound();
        this.collectRewardSoundClip = new CollectRewardSound();
    }


    //https://sfxr.me/
    public void playCrashSound() {
        playSound(crashSoundClip);
    }

    public void playSelectSound() {
        playSound(selectSoundClip);
    }

    public void changeSelectionSound() {
        playSound(changeSelectionSoundClip);
    }

    public void collectRewardSound() {
        playSound(collectRewardSoundClip);
    }




    private void playSound(SoundClip soundClip) {
        soundClip.reset();
        soundClip.play();
    }
}
