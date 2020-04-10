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

import models.Media;
import models.RadioPlayer;
import models.constants.Constant;

public class USBPlayer implements IPlayer {

	private Media[] listOfUSBSongs;
	private int currentSongIndex;
	private boolean isPlaying;
	private Clip clip;
	private long currentSongTimer;
	private RadioPlayer radio;
	private boolean isWorking;
	
	private ArrayList<Media> listOfPresets = new ArrayList<Media>(); 
	
	public USBPlayer(RadioPlayer radio) {
		isPlaying = false;
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
	
	public Media[] listSongs() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("usbSongs.xml");
			NodeList usbSongsList = document.getElementsByTagName("usbsong");
			listOfUSBSongs = new Media[usbSongsList.getLength()];
			for (int i = 0; i<usbSongsList.getLength();i++) {
				Node song = usbSongsList.item(i);
				if(song.getNodeType() == Node.ELEMENT_NODE) {
					Element songElement = (Element)song;
					listOfUSBSongs[i] = new Media(
							i,
							songElement.getAttribute("name"),
							songElement.getAttribute("artist"),
							songElement.getAttribute("usbLogo"),
							songElement.getAttribute("defaultImageForUsbSong"),
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
		return listOfUSBSongs;
	}
	
	@Override
	public void leftClick() {
		clip.stop();//Arr�ter la musique actuelle
		isPlaying = true;//Quand on change de musique on la joue directement
		if(currentSongIndex>0) {//Jouer la chanson pr�c�dente s'il y'en a une
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
		clip.stop();//Arr�ter la musique actuelle
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
			if(isWorking) {
				String songPath = listOfUSBSongs[currentSongIndex].getSongPath();
				File songFile = new File(songPath);
				AudioInputStream audioInput = AudioSystem.getAudioInputStream(songFile);
				clip = AudioSystem.getClip();
				clip.open(audioInput);
				clip.loop(Clip.LOOP_CONTINUOUSLY);
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
		isPlaying = false;
		clip.stop();
	}

	@Override
	public void launchPlayer() {
		if(isPlaying == false) {
			isPlaying = true;
			playMusic();
		}
	}

	@Override
	public void sendMediaToRadio() {
		radio.editPlayerInformations(listOfUSBSongs[currentSongIndex]);
	}

	@Override
	public void setCurrentMediaIndex(int index) {
		clip.stop();
		currentSongIndex = index;
		playMusic();
	}
	
	@Override
	public Media getCurrentMedia() {
		return listOfUSBSongs[currentSongIndex];
	}
	
	@Override
	public void setPresetsWithAutotune() {
		for (int i = Constant.indexOfFirstPreset; i < Constant.maxPresetInRadio; i++) {
			listOfPresets.set(i, listOfUSBSongs[i]);
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
		if(!isWorking) {
			stopPlayer();
		}
	}
	
	@Override
	public boolean isPlaying() {
		return isPlaying;
	}
	
}
