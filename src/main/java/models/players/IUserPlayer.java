package models.players;

import java.io.IOException;
import java.util.ArrayList;

import models.Song;

public interface IUserPlayer {

	/**
	 * Méthode appelée une fois quand on crée l'objet player.
	 * Elle permet de lister les chanson usb/aux contenue dans le fichier xml corrrespondant.
	 * @return
	 * @throws IOException 
	 */
	public Song[] listSongs();
	
	public void playSong();
	public void pauseSong();
	
	/**
	 * Méthode appelé quand on écoute normalement la radio
	 * et que l'on appuie sur la flèche de droite de la radio.
	 * Permet de prendre la chanson suivante et de la jouer.
	 * @return
	 */
	public Song playNextSong();
	
	/**
	 * Méthode appelée quand on écoute normalement la radio
	 * et que l'on appuie sur la flèche de droite de la radio.
	 * Permet de prendre la chanson précédente et de la jouer.
	 * @return
	 */
	public Song playPreviousSong();
	
	
}
