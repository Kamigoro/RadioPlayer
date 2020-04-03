package models.players;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import models.Song;

public class AUXPlayer implements IPlayer, IUserPlayer {

	private Song[] listOfAUXSongs;
	
	public AUXPlayer() {
		listSongs();
	}
	
	@Override
	public Song[] listSongs() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("auxSongs.xml");
			NodeList usbSongsList = document.getElementsByTagName("auxsong");
			listOfAUXSongs = new Song[usbSongsList.getLength()];
			for (int i = 0; i<usbSongsList.getLength();i++) {
				Node song = usbSongsList.item(i);
				if(song.getNodeType() == Node.ELEMENT_NODE) {
					Element songElement = (Element)song;
					listOfAUXSongs[i] = new Song(
							songElement.getAttribute("name"),
							songElement.getAttribute("artist"),
							songElement.getAttribute("imagePath"),
							Integer.parseInt(songElement.getAttribute("durationMN")),
							Integer.parseInt(songElement.getAttribute("durationSec")));
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listOfAUXSongs;
	}
	
	@Override
	public void leftClick() {
		playPreviousSong();
	}

	@Override
	public void okClick() {
		// TODO Gérer la mise en pause et la reprise d'une chanson
	}

	@Override
	public void rightClick() {
		playNextSong();
	}

	@Override
	public void playSong() {
		// TODO Reprendre une chanson
		
	}

	@Override
	public void pauseSong() {
		// TODO Mettre en pause la chanson actuelle
		
	}

	@Override
	public Song playNextSong() {
		// TODO Renvoyer la chanson suivante
		return null;
	}

	@Override
	public Song playPreviousSong() {
		// TODO Renvoyer la chanson précédente
		return null;
	}
}
