package models.players;

import java.util.ArrayList;


/**
 * Interface permettant de gérer les intéraction avec un player.
 * Appui sur la flèche de gauche/droite.
 * Appui sur le bouton centrale.
 * En fonction du type de player le comportement va changer.
 * @author Dylan
 */
public interface IPlayer {

	/**
	 * Méthode appelée par un controller graphique.
	 * Permet de passer à la précédente Station/Chanson
	 */
	public void leftClick();
	
	/**
	 * Méthode appelée par un controller graphique.
	 * Permet d'aller à la précédente Station/Chanson
	 */
	public void rightClick();
	
	/**
	 * Méthode appelée par un controller graphique.
	 * Permet de mettre en pause une chanson ou de la faire reprendre. Ne fait rien en cas de signal radio.
	 */
	public void okClick();
	
	/**
	 * Fonction permettant de lire une vraie musique
	 */
	public void playMusic();
	
	/**
	 * Fonction qui va être appelée quand on va vouloir détruire un player. Elle va permettre d'arrêter la lecture des chansons en cours avant de passer au player suivant.
	 */
	public void stopPlayer();
	
}
