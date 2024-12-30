package de.cargame.model;

import de.cargame.model.entity.player.PlayerObserver;
import de.cargame.model.entity.player.PlayerUpdate;

public interface PlayerObservable {

    public void addObserver(PlayerObserver playerObserver);

    public void removeObserver(PlayerObserver observer);

    public void notifyObservers(PlayerUpdate playerUpdate);
}
