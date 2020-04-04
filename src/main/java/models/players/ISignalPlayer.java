package models.players;

import java.io.IOException;
import java.util.ArrayList;

import models.Station;

public interface ISignalPlayer {

	/**
	 * M�thode appel�e une fois quand on cr�e l'objet player.
	 * Elle permet de lister les Station FM/DAB contenue dans le fichier json corrrespondant.
	 * @return
	 * @throws IOException 
	 */
	public Station[] listStations();
	
	/**
	 * M�thode appel�e quand on �coute normalement la radio
	 * et que l'on appuie sur la fl�che de droite de la radio.
	 * Permet d'aller � la station suivante.
	 * @return
	 */
	public void goToNextStation();
	
	/**
	 * M�thode appel�e quand on �coute normalement la radio
	 * et que l'on appuie sur la fl�che de gauche de la radio.
	 * Permet d'aller � la station pr�c�dente.
	 * @return
	 */
	public void goToPreviousStation();
	
}
