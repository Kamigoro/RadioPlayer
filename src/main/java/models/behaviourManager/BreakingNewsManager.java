package models.behaviourManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import models.*;

/**
 * Classe gérant le comportement de réception de breaking news
 * Elle hérite de thread et va constamment checker si une breaking news est présente
 * S'il y'en a une elle la renvoit à la radio
 * Pour couper le thread il faut mettre close le Socket serveur et mettre isActivated à FALSE
 * @author Dylan
 *
 */
public class BreakingNewsManager extends Thread {

	private RadioPlayer radio;
	private boolean isActivated;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	
	public BreakingNewsManager(RadioPlayer radio) {
		System.out.println("BehaviourManager : Un gestionnaire de breaking news est attachÃ© Ã  la radio");
		isActivated = true;
		this.start();
	}
	
	@Override
	public void run() {
		
		try {
			System.out.println("Breaking News Manager : Ouverture du socket serveur");
			serverSocket = new ServerSocket(7777);
		} catch (IOException e) {
			System.out.println("Breaking News Manager : Nous n'avons pas rÃ©ussi Ã  ouvrir le socket serveur");
		}
		
		while(isActivated) {
			try {
		        System.out.println("Breaking News Manager : En attente d'une breaking news ");
		        socket = serverSocket.accept(); //Appel bloquant
		        System.out.println("Breaking News Manager : Une breaking news a Ã©tÃ© reÃ§ue ");
		        InputStream inputStream = socket.getInputStream();
		        DataInputStream dataInputStream = new DataInputStream(inputStream);
		        String breakingNews = dataInputStream.readUTF();
		        System.out.println("Breaking News Manager : FLASH INFO : "+breakingNews);
		        //TODO Implémenter le renvoi de la breaking news vers l'interface graphique
			} catch (IOException e) {
				System.out.println("Breaking News Manager : Une erreur s'est produite ou l'option a été désactivé");
			}
		}
		
		//Nous avons décidé d'arreter le Thread 
		//Nous devons donc fermer les sockets pour éviter les erreurs
		try {
			System.out.println("Breaking News Manager : Fermeture des sockets");
			serverSocket.close();
			if(socket != null)socket.close();
		} catch (IOException e) {
			System.out.println("Breaking News Manager : Une erreur s'est produite lors de la tentative de fermeture des sockets");
		}
        
		System.out.println("Breaking News Manager : A été détruit");
	}
	
	public ServerSocket getServerSocket() {
		return this.serverSocket;
	}
	
	public void setIsActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
	
}
