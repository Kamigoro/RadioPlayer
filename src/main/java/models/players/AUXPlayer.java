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

import models.Media;
import models.RadioPlayer;

public class AUXPlayer implements IPlayer{

	private Media[] listOfAUXSongs;
	private int currentSongIndex;
	private boolean isPlaying;
	private Clip clip;
	private long currentSongTimer;
	private RadioPlayer radio;
	
	private Media preset1;
	private Media preset2;
	private Media preset3;
	
	public AUXPlayer(RadioPlayer radio) {
		this.radio = radio;
		listSongs();
		currentSongIndex = 0;
	}
	
	/**
	 * Liste les musisques pr�sentes dans le fichier 
	 * @return
	 */
	public Media[] listSongs() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("auxSongs.xml");
			NodeList auxSongsList = document.getElementsByTagName("auxsong");
			listOfAUXSongs = new Media[auxSongsList.getLength()];
			for (int i = 0; i<auxSongsList.getLength();i++) {
				Node song = auxSongsList.item(i);
				if(song.getNodeType() == Node.ELEMENT_NODE) {
					Element songElement = (Element)song;
					listOfAUXSongs[i] = new Media(
							i,
							songElement.getAttribute("name"),
							songElement.getAttribute("artist"),
							songElement.getAttribute("auxLogo"),
							songElement.getAttribute("defaultImageForAuxSong"),
							songElement.getAttribute("songPath"));
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
		clip.stop();//Arr�ter la musique actuelle
		isPlaying = true;//Quand on change de musique on la joue directement
		if(currentSongIndex>0) {//Jouer la chanson pr�c�dente s'il y'en a une
			currentSongIndex--;
			playMusic();
		}
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
		clip.stop();//Arr�ter la musique actuelle
		isPlaying = true;//Quand on change de musique on la joue directement
		if(currentSongIndex<listOfAUXSongs.length) {//Jouer la chanson suivante s'il y'en a une
			currentSongIndex++;
			playMusic();
		}
	}
	
	@Override
	public void playMusic() {
		try {
			String songPath = listOfAUXSongs[currentSongIndex].getSongPath();
			File songFile = new File(songPath);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(songFile);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();
			sendMediaToRadio();
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

	@Override
	public void launchPlayer() {
		isPlaying = true;
		playMusic();
	}

	@Override
	public void sendMediaToRadio() {
		radio.editPlayerInformations(listOfAUXSongs[currentSongIndex]);
	}

	@Override
	public void setCurrentMediaIndex(int index) {
		clip.stop();
		currentSongIndex = index;
		playMusic();
	}

	@Override
	public Media getCurrentMedia() {
		return listOfAUXSongs[currentSongIndex];
	}

	@Override
	public Media getPreset1() {
		return preset1;
	}

	@Override
	public void setPreset1() {
		preset1 = getCurrentMedia();
	}

	@Override
	public Media getPreset2() {
		return preset2;
	}

	@Override
	public void setPreset2() {
		preset2 = getCurrentMedia();
	}

	@Override
	public Media getPreset3() {
		return preset3;
	}

	@Override
	public void setPreset3() {
		preset3 = getCurrentMedia();
	}
}
