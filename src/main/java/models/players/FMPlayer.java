package models.players;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
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

public class FMPlayer implements IPlayer {

	private Media[] listOfFMStations;
	private int currentStationIndex;
	private boolean isPlaying;
	private Clip clip;
	private RadioPlayer radio;
	
	private ArrayList<Media> listOfPresets = new ArrayList<Media>(); 
	
	public FMPlayer(RadioPlayer radio){
		this.radio = radio;
		listFMStations();
		currentStationIndex = 0;
	}
	
	private void instanciatePreset() {
		
		for (int i = Constant.indexOfFirstPreset; i < Constant.maxPresetInRadio; i++) {
			listOfPresets.add(new Media());			
		}
		
	}
	
	public Media[] listFMStations() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse("fmStations.xml");
			NodeList usbSongsList = document.getElementsByTagName("fmStation");
			listOfFMStations = new Media[usbSongsList.getLength()];
			for (int i = 0; i<usbSongsList.getLength();i++) {
				Node song = usbSongsList.item(i);
				if(song.getNodeType() == Node.ELEMENT_NODE) {
					Element songElement = (Element)song;
					listOfFMStations[i] = new Media(
							i,
							songElement.getAttribute("stationName"),
							songElement.getAttribute("frequency"),
							songElement.getAttribute("fmLogo"),
							songElement.getAttribute("defaultImageForFMSong"),
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
		return listOfFMStations;
	}
	
	
	@Override
	public void leftClick() {
		clip.stop();//Arr�ter la musique actuelle
		isPlaying = true;//Quand on change de musique on la joue directement
		if(currentStationIndex>0) {//Jouer la chanson pr�c�dente s'il y'en a une
			currentStationIndex--;
		} else {
			currentStationIndex = listOfFMStations.length - 1;
		}
		playMusic();
	}

	@Override
	public void okClick() {
		// Ne fait rien car one ne peut pas mettre en pause une station
	}

	@Override
	public void rightClick() {
		clip.stop();//Arr�ter la station actuelle
		isPlaying = true;//Quand on change de station on la joue directement
		if(currentStationIndex<listOfFMStations.length-1) {//Jouer la station suivante s'il y'en a une
			currentStationIndex++;	
		} else {
			currentStationIndex = 0;
		}
		playMusic();
	}

	@Override
	public void playMusic() {
		try {
			String songPath = listOfFMStations[currentStationIndex].getSongPath();
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
		radio.editPlayerInformations(listOfFMStations[currentStationIndex]);
	}

	@Override
	public void setCurrentMediaIndex(int index) {
		clip.stop();
		currentStationIndex = index;
		playMusic();
	}
	
	@Override
	public Media getCurrentMedia() {
		return listOfFMStations[currentStationIndex];
	}
	
	@Override
	public void setPresetsWithAutotune() {
		for (int i = Constant.indexOfFirstPreset; i < Constant.maxPresetInRadio; i++) {
			listOfPresets.set(i, listOfFMStations[i]);
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
}
