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
	 * Permet de mettre en pause une chanson. Ne fait rien en cas de signal radio.
	 */
	public void okClick();
	
}
