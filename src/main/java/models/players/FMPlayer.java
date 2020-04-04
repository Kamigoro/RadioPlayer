package models.players;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import models.Station;

public class FMPlayer implements IPlayer, ISignalPlayer {

	public FMPlayer(){
		listStations();
	}
	
	@Override
	public Station[] listStations(){
		// TODO Lire le fichier des stations FM et renvoyer la liste
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
	public void goToNextStation() {
	}

	@Override
	public void goToPreviousStation() {
	}

	@Override
	public void playMusic() {
	}
}
