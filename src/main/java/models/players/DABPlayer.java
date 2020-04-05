package models.players;

import java.util.ArrayList;

import models.Station;

public class DABPlayer implements IPlayer {

	public DABPlayer() {
		listStations();
	}
	
	public Station[] listStations() {
		// TODO Lire le fichier JSON des stations DAB et renvoyer la liste
		return null;
	}
	
	@Override
	public void leftClick() {
	}

	@Override
	public void okClick() {
		// Ne fait rien car one ne peut pas mettre en pause une station
	}

	@Override
	public void rightClick() {
	}


	@Override
	public void playMusic() {		
	}

	@Override
	public void stopPlayer() {
		// TODO Auto-generated method stub
		
	}

}
