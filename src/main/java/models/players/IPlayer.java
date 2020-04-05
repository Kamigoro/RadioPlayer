package models.players;

import java.util.ArrayList;


/**
 * Interface permettant de g�rer les int�raction avec un player.
 * Appui sur la fl�che de gauche/droite.
 * Appui sur le bouton centrale.
 * En fonction du type de player le comportement va changer.
 * @author Dylan
 */
public interface IPlayer {

	/**
	 * M�thode appel�e par un controller graphique.
	 * Permet de passer � la pr�c�dente Station/Chanson
	 */
	public void leftClick();
	
	/**
	 * M�thode appel�e par un controller graphique.
	 * Permet d'aller � la pr�c�dente Station/Chanson
	 */
	public void rightClick();
	
	/**
	 * M�thode appel�e par un controller graphique.
	 * Permet de mettre en pause une chanson ou de la faire reprendre. Ne fait rien en cas de signal radio.
	 */
	public void okClick();
	
	/**
	 * Fonction permettant de lire une vraie musique
	 */
	public void playMusic();
	
	/**
	 * Fonction qui va �tre appel�e quand on va vouloir d�truire un player. Elle va permettre d'arr�ter la lecture des chansons en cours avant de passer au player suivant.
	 */
	public void stopPlayer();
	
}
