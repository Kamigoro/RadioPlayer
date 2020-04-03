package models.players;

import java.util.ArrayList;

import models.Station;

public class DABPlayer implements IPlayer, ISignalPlayer {

	public DABPlayer() {
		listStations();
	}
	
	@Override
	public Station[] listStations() {
		// TODO Lire le fichier JSON des stations DAB et renvoyer la liste
		return null;
	}
	
	@Override
	public void leftClick() {
		goToPreviousStation();
	}

	@Override
	public void okClick() {
		// Ne fait rien car one ne peut pas mettre en pause une station
	}

	@Override
	public void rightClick() {
		goToPreviousStation();
	}

	@Override
	public Station goToNextStation() {
		// TODO Renvoyer la station suivante
		return null;
	}

	@Override
	public Station goToPreviousStation() {
		// TODO Renvoyer la station précédente
		return null;
	}

}
