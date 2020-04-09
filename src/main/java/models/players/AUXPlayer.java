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
import models.constants.Constant;

public class AUXPlayer implements IPlayer{

	private Media[] listOfAUXSongs;
	private int currentSongIndex;
	private boolean isPlaying;
	private Clip clip;
	private long currentSongTimer;
	private RadioPlayer radio;
	private boolean isWorking;
	
	private ArrayList<Media> listOfPresets = new ArrayList<Media>(); 
	
	public AUXPlayer(RadioPlayer radio) {
		this.radio = radio;
		isWorking = true;
		listSongs();
		currentSongIndex = 0;
		instanciatePreset();
	}
	
	private void instanciatePreset() {
		
		for (int i = Constant.indexOfFirstPreset; i < Constant.maxPresetInRadio; i++) {
			listOfPresets.add(new Media());			
		}
		
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
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			if(isWorking) {
				clip.start();
			}
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
	public void setPresetsWithAutotune() {
		for (int i = Constant.indexOfFirstPreset; i < Constant.maxPresetInRadio; i++) {
			listOfPresets.set(i, listOfAUXSongs[i]);
		}
		
	}

	@Override
	public Media getPreset(int index) {
		return listOfPresets.get(index);
	}

	@Override
	public void setPreset(int index) {
		listOfPresets.set(index, getCurrentMedia());
		
	}

	@Override
	public void setIsWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}


}
