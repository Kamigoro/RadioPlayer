package models.players;

import java.util.ArrayList;

import models.Media;


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
	
	/**
	 * Fonction qui va �tre appel�e pour lancer un player pour la premi�re fois.
	 */
	public void launchPlayer();
	
	/**
	 * Fonction qui va modifier l'interface graphique de la radio correspondant à la musique
	 */
	public void sendMediaToRadio();
	
	/**
	 * Méthode qui va nous renvoyer le média actuel
	 */
	public Media getCurrentMedia();	
	
	/**
	 * Méthode qui va permettre de dire au player à quel media se placer pour le jouer. Utile dans le cas où l'on appuie sur un preset.
	 * @param index
	 */
	public void setCurrentMediaIndex(int index);
	
	public Media getPreset1();
	public void setPreset1();
	
	public Media getPreset2();
	public void setPreset2();
	
	public Media getPreset3();
	public void setPreset3();
}
