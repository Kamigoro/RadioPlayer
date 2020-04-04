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
import org.xml.sax.SAXException;

import models.Song;

public class AUXPlayer extends Thread implements IPlayer, IUserPlayer {

	private Song[] listOfAUXSongs;
	private int currentSongIndex;
	private boolean isPlaying;
	private Clip clip;
	private long currentSongTimer;
	
	public AUXPlayer() {
		listSongs();
		currentSongIndex = 0;
	}
	
	@Override
	public void run() {
		 
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
		return listOfAUXSongs;
	}
	
	@Override
	public void leftClick() {
		playPreviousSong();
	}

	@Override
	public void okClick() {
		isPlaying = !isPlaying;
		if(isPlaying) {
			resumeSong();
		}else {
			pauseSong();
		}
	}

	@Override
	public void rightClick() {
		playNextSong();
	}

	@Override
	public void resumeSong() {
		clip.setMicrosecondPosition(currentSongTimer);
		clip.start();
	}

	@Override
	public void pauseSong() {
		currentSongTimer = clip.getMicrosecondPosition();
		clip.stop();
	}

	@Override
	public void playNextSong() {
		if(currentSongIndex<listOfAUXSongs.length) {
			currentSongIndex++;
			playMusic(listOfAUXSongs[currentSongIndex].getSongPath());
		}
	}

	@Override
	public void playPreviousSong() {
		if(currentSongIndex>0) {
			currentSongIndex--;
			playMusic(listOfAUXSongs[currentSongIndex].getSongPath());
		}
	}
	

	@Override
	public void playMusic(String songPath) {
		try {
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
}
