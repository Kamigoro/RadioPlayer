package models.players;

import java.io.IOException;
import java.util.ArrayList;

import models.Station;

public interface ISignalPlayer {

	/**
	 * Méthode appelée une fois quand on crée l'objet player.
	 * Elle permet de lister les Station FM/DAB contenue dans le fichier json corrrespondant.
	 * @return
	 * @throws IOException 
	 */
	public Station[] listStations();
	
	/**
	 * Méthode appelée quand on écoute normalement la radio
	 * et que l'on appuie sur la flèche de droite de la radio.
	 * Permet d'aller à la station suivante.
	 * @return
	 */
	public void goToNextStation();
	
	/**
	 * Méthode appelée quand on écoute normalement la radio
	 * et que l'on appuie sur la flèche de gauche de la radio.
	 * Permet d'aller à  la station précédente.
	 * @return
	 */
	public void goToPreviousStation();
	
}
