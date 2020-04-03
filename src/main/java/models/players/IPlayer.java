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
	 * Permet de mettre en pause une chanson. Ne fait rien en cas de signal radio.
	 */
	public void okClick();
	
}
