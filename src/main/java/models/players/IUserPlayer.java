package models.players;

import java.io.IOException;
import java.util.ArrayList;

import models.Song;

public interface IUserPlayer {

	/**
	 * M�thode appel�e une fois quand on cr�e l'objet player.
	 * Elle permet de lister les chanson usb/aux contenue dans le fichier xml corrrespondant.
	 * @return
	 * @throws IOException 
	 */
	public Song[] listSongs();
	
	public void playSong();
	public void pauseSong();
	
	/**
	 * M�thode appel� quand on �coute normalement la radio
	 * et que l'on appuie sur la fl�che de droite de la radio.
	 * Permet de prendre la chanson suivante et de la jouer.
	 * @return
	 */
	public Song playNextSong();
	
	/**
	 * M�thode appel�e quand on �coute normalement la radio
	 * et que l'on appuie sur la fl�che de droite de la radio.
	 * Permet de prendre la chanson pr�c�dente et de la jouer.
	 * @return
	 */
	public Song playPreviousSong();
	
	
}
