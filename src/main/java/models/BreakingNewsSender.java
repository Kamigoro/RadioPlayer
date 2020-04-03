package models;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe publique nous permettant d'atteindre une classe statique
 * @author Dylan
 *
 */
public class BreakingNewsSender {

	/**
	 * Classe qui ne contient qu'une seule méthode permettant d'envoyer une breaking news
	 * @author Dylan
	 *
	 */
	public static class Manager {
		
		//TODO appeler la fonction au bon endroit pour envoyer des breaking news
		/**
		 * Permet d'envoyer une breaking news sur le port écoutant les breaking news
		 * @param breakingNews
		 */
		public static void sendBreakingNews(String breakingNews) {
			Socket socket = null;
			OutputStream outputStream = null;
			
			//Connexion au socket
			try {
				socket = new Socket("localhost", 7777);
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
	        
	        //Récupérer le flux de sortie du socket
			try {
				outputStream = socket.getOutputStream();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
			//Récupérer le flux de données du flux de sortie du socket
	        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

	        //Envoyer le message sur le flux de données
	        //Le vider et le fermer
	        try {
				dataOutputStream.writeUTF(breakingNews);
				dataOutputStream.flush();
		        dataOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	        //Couper la connexion au socket serveur
	        try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
   }
}
