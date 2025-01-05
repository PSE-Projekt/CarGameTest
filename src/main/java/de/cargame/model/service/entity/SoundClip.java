package de.cargame.model.service.entity;

import lombok.Getter;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


@Getter
public abstract class SoundClip {

    protected String path;
    private Clip clip;


    public SoundClip() {
        setPath();
        loadSoundFile(path);
    }



    protected abstract void setPath();

    public void play() {
        new Thread(clip::start).start();
    }

    public void reset(){
        clip.setMicrosecondPosition(0);
    }


    protected void loadSoundFile(String soundFilePath) {
        File f = new File(soundFilePath);
        try {
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
            this.clip = AudioSystem.getClip();
            this.clip.open(audioIn);
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
