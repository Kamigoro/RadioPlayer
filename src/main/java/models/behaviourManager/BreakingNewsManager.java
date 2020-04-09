package models.behaviourManager;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import models.*;
import models.enums.DisplayType;

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
	private String[] breakingNewsInFile;
	private boolean isWorking;
	
	public BreakingNewsManager(RadioPlayer radio) {
		System.out.println("BehaviourManager : Un gestionnaire de breaking news est attaché à  la radio");
		this.radio = radio;
		isActivated = true;
		isWorking = true;
		listBreakingNews();
		this.start();
	}
	
	@Override
	public void run() {
		while(isActivated) {
				try {
					int randomTime = 20 + (int)(Math.random() * ((20 - 10) + 1));//Sortir une valeur aléatoire d'attente en secondes
					randomTime = randomTime * 1000;//Avoir la valeur en ms
					this.sleep(randomTime);
					int randomBreakingNewsIndex = 0 + (int)(Math.random() * ((breakingNewsInFile.length - 0) + 1));//Sortir une valeur aléatoire comprise entre 0 et le nombre de breaking news présentes dans le fichier
			        if(isWorking) {
			        	radio.displayMessageOnMainScreen(DisplayType.BreakingNews, breakingNewsInFile[randomBreakingNewsIndex-1]);//Envoyer la breaking news à l'écran
			        }else {
			        	radio.displayMessageOnMainScreen(DisplayType.BreakingNews, "}zs}}##££££d@@@@@343%*£µ+%%%*¨$+");
			        }
			        this.sleep(5000);
			        radio.displayMessageOnMainScreen(DisplayType.BreakingNews, "");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		System.out.println("Breaking News Manager : A été détruit");
	}

	/**
	 * Fonction qui nous permet de lister les breakings news dans un fichier
	 * @return
	 */
	private String[] listBreakingNews() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("breakingNews.xml");
			NodeList breakingNewsList = document.getElementsByTagName("breakingNews");
			breakingNewsInFile = new String[breakingNewsList.getLength()];
			for (int i = 0; i<breakingNewsList.getLength();i++) {
				Node breakingNews = breakingNewsList.item(i);
				if(breakingNews.getNodeType() == Node.ELEMENT_NODE) {
					Element news = (Element)breakingNews;
					breakingNewsInFile[i] = news.getAttribute("info");
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return breakingNewsInFile;
	}
	
	public void setIsActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}
	
	public void setIsWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}
	
}
