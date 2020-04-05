package models;

import java.util.ArrayList;

import models.options.SignalType;

public class Station {
	
	private String name;
	private float frequency;
	private String logoPath;
	private String radioSongPath;
	
	
	public Station(String name, float frequency, String logoPath) {
		this.name = name;
		this.frequency = frequency;
		this.logoPath = logoPath;
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
	public String getLogoPath() {
		return logoPath;
	}
	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	
	
	
}
