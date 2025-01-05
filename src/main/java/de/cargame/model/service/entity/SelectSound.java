package de.cargame.model.service.entity;

public class SelectSound extends SoundClip {

    public SelectSound(){
        setPath();
    }
    @Override
    protected void setPath() {
        this.path = "src/main/resources/sounds/select.wav";
    }
}
