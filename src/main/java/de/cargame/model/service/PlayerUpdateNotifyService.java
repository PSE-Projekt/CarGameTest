package de.cargame.model.service;

import de.cargame.model.PlayerObservable;
import de.cargame.model.entity.player.PlayerObserver;
import de.cargame.model.entity.player.PlayerUpdate;

import java.util.ArrayList;
import java.util.Collection;


public class PlayerUpdateNotifyService implements PlayerObservable {

    private final Collection<PlayerObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(PlayerObserver playerObserver) {
        this.observers.add(playerObserver);
    }

    @Override
    public void removeObserver(PlayerObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers(PlayerUpdate playerUpdate) {
        for (PlayerObserver observer : observers) {
            observer.update(playerUpdate);
        }
    }
}
