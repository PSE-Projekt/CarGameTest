package de.cargame.model.service.entity;

public class ChangeSelectionSound extends SoundClip {


    public ChangeSelectionSound(){
        setPath();
    }
    @Override
    protected void setPath() {
        this.path = "src/main/resources/sounds/change_selection.wav";
    }
}