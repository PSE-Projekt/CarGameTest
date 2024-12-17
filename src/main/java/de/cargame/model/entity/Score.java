package de.cargame.model.entity;


import lombok.Getter;

@Getter
public class Score {

    private int value = 0;


    public int increaseScore(int value){
        value+=value;
        return value;
    }

    public void reset(){
        value = 0;
    }
}
