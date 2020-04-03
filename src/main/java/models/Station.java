package models;

import java.util.ArrayList;

import models.options.SignalType;

public class Station {
	
	private String name;
	private float frequency;
	private String imagePath;
	private ArrayList<Song> listOfSongs;
	
	
	public Station(String name, float frequency, String imagePath, ArrayList<Song> listOfSongs) {
		this.name = name;
		this.frequency = frequency;
		this.imagePath = imagePath;
		this.listOfSongs = listOfSongs;
	}
	
	//Getters et setters//
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getFrequency() {
		return frequency;
	}
	public void setFrequency(float frequency) {
		this.frequency = frequency;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public ArrayList<Song> getListOfSongs() {
		return listOfSongs;
	}
	public void setListOfSongs(ArrayList<Song> listOfSongs) {
		this.listOfSongs = listOfSongs;
	}
	
	
	
}
