package models.players;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSInput;
import org.xml.sax.SAXException;

import models.Song;

public class USBPlayer implements IPlayer, IUserPlayer {

	private Song[] listOfUSBSongs;
	
	
	public USBPlayer() {
		System.out.println("PLAYER : Un USBPlayer a été crée");
		listSongs();
	}
	
	@Override
	public Song[] listSongs() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("usbSongs.xml");
			NodeList usbSongsList = document.getElementsByTagName("usbsong");
			listOfUSBSongs = new Song[usbSongsList.getLength()];
			for (int i = 0; i<usbSongsList.getLength();i++) {
				Node song = usbSongsList.item(i);
				if(song.getNodeType() == Node.ELEMENT_NODE) {
					Element songElement = (Element)song;
					listOfUSBSongs[i] = new Song(
							songElement.getAttribute("name"),
							songElement.getAttribute("artist"),
							songElement.getAttribute("imagePath"),
							songElement.getAttribute("songPath"),
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
		return listOfUSBSongs;
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
	public void resumeSong() {
		
	}

	@Override
	public void pauseSong() {
		
	}

	@Override
	public void playNextSong() {
	}

	@Override
	public void playPreviousSong() {
	}

	@Override
	public void playMusic() {
	}

}
