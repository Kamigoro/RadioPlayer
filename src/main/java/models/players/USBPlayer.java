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

public class USBPlayer implements IPlayer {

	private Song[] listOfUSBSongs;
	private int currentSongIndex;
	private boolean isPlaying;
	private Clip clip;
	private long currentSongTimer;
	
	public USBPlayer() {
		listSongs();
		currentSongIndex = 0;
		isPlaying = true;
		playMusic();
	}
	
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
		clip.stop();//Arrêter la musique actuelle
		isPlaying = true;//Quand on change de musique on la joue directement
		if(currentSongIndex>0) {//Jouer la chanson précédente s'il y'en a une
			currentSongIndex--;
		} else {
			currentSongIndex = listOfUSBSongs.length - 1;
		}
		playMusic();
	}

	@Override
	public void okClick() {
		isPlaying = !isPlaying;
		if(isPlaying) {
			clip.setMicrosecondPosition(currentSongTimer);
			clip.start();
		}else {
			currentSongTimer = clip.getMicrosecondPosition();
			clip.stop();
		}
	}

	@Override
	public void rightClick() {
		clip.stop();//Arrêter la musique actuelle
		isPlaying = true;//Quand on change de musique on la joue directement
		if(currentSongIndex<listOfUSBSongs.length-1) {//Jouer la chanson suivante s'il y'en a une
			currentSongIndex++;	
		} else {
			currentSongIndex = 0;
		}
		playMusic();
	}

	@Override
	public void playMusic() {
		try {
			String songPath = listOfUSBSongs[currentSongIndex].getSongPath();
			File songFile = new File(songPath);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(songFile);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stopPlayer() {
		clip.stop();
	}

}
