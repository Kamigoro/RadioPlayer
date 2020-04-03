package models;

public class Song {
	
	private String name;
	private String artist;
	private String imagePath;
	private int durationMN;
	private int durationSec;
	
	public Song(String name, String artist, String imagePath, int durationMN, int durationSec) {
		this.name = name;
		this.artist = artist;
		this.imagePath = imagePath;
		this.durationMN = durationMN;
		this.durationSec = durationSec;
	}
	
	public String getName() {
		return name;
	}
	public String getArtist() {
		return artist;
	}
	public String getImagePath() {
		return imagePath;
	}
	public int getDurationMN() {
		return durationMN;
	}
	public int getDurationSec() {
		return durationSec;
	}
	
}
