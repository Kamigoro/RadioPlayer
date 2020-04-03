package models.behaviourManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import models.*;

/**
 * Classe g�rant le comportement de r�ception de breaking news
 * Elle h�rite de thread et va constamment checker si une breaking news est pr�sente
 * S'il y'en a une elle la renvoit � la radio
 * Pour couper le thread il faut mettre close le Socket serveur et mettre isActivated � FALSE
 * @author Dylan
 *
 */
public class BreakingNewsManager extends Thread {

	private RadioPlayer radio;
	private boolean isActivated;
	private ServerSocket serverSocket = null;
	private Socket socket = null;
	
	public BreakingNewsManager(RadioPlayer radio) {
		System.out.println("BehaviourManager : Un gestionnaire de breaking news est attaché à la radio");
		isActivated = true;
		this.start();
	}
	
	@Override
	public void run() {
		
		try {
			System.out.println("Breaking News Manager : Ouverture du socket serveur");
			serverSocket = new ServerSocket(7777);
		} catch (IOException e) {
			System.out.println("Breaking News Manager : Nous n'avons pas réussi à ouvrir le socket serveur");
		}
		
		while(isActivated) {
			try {
		        System.out.println("Breaking News Manager : En attente d'une breaking news ");
		        socket = serverSocket.accept(); //Appel bloquant
		        System.out.println("Breaking News Manager : Une breaking news a été reçue ");
		        InputStream inputStream = socket.getInputStream();
		        DataInputStream dataInputStream = new DataInputStream(inputStream);
		        String breakingNews = dataInputStream.readUTF();
		        System.out.println("Breaking News Manager : FLASH INFO : "+breakingNews);
		        //TODO Impl�menter le renvoi de la breaking news vers l'interface graphique
			} catch (IOException e) {
				System.out.println("Breaking News Manager : Une erreur s'est produite ou l'option a �t� d�sactiv�");
			}
		}
		
		//Nous avons d�cid� d'arreter le Thread 
		//Nous devons donc fermer les sockets pour �viter les erreurs
		try {
			System.out.println("Breaking News Manager : Fermeture des sockets");
			serverSocket.close();
			if(socket != null)socket.close();
		} catch (IOException e) {
			System.out.println("Breaking News Manager : Une erreur s'est produite lors de la tentative de fermeture des sockets");
		}
        
		System.out.println("Breaking News Manager : A �t� d�truit");
	}
	
	public ServerSocket getServerSocket() {
		return this.serverSocket;
	}
	
	public void setIsActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
	
}
