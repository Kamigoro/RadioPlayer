package models;

import models.failures.*;

public class FailureManager {
	
	private RadioPlayer radio;
	private VolumeFailure volumeFailure;
	private AudioOutFailure audioOutFailure;
	private BreakingNewsFailure breakingNewsFailure;
	private DateAndTimeFailure dateAndTimeFailure;
	private PlayerFailure playerFailure;
	private ScreenFailure screenFailure;
	
	public FailureManager(RadioPlayer radio) {
		this.radio = radio;
		volumeFailure = new VolumeFailure(radio);
		audioOutFailure = new AudioOutFailure(radio);
		breakingNewsFailure = new BreakingNewsFailure(radio);
		dateAndTimeFailure = new DateAndTimeFailure(radio);
		playerFailure = new PlayerFailure(radio);
		screenFailure = new ScreenFailure(radio);
	}
	
	/////////////////////////////////////
	//			Getters et setters	   //
	/////////////////////////////////////
	
	public VolumeFailure getVolumeFailure() {
		return volumeFailure;
	}

	public void setVolumeFailure(VolumeFailure volumeFailure) {
		this.volumeFailure = volumeFailure;
	}

	public AudioOutFailure getAudioOutFailure() {
		return audioOutFailure;
	}

	public void setAudioOutFailure(AudioOutFailure audioOutFailure) {
		this.audioOutFailure = audioOutFailure;
	}

	public BreakingNewsFailure getBreakingNewsFailure() {
		return breakingNewsFailure;
	}

	public void setBreakingNewsFailure(BreakingNewsFailure breakingNewsFailure) {
		this.breakingNewsFailure = breakingNewsFailure;
	}

	public DateAndTimeFailure getDateAndTimeFailure() {
		return dateAndTimeFailure;
	}

	public void setDateAndTimeFailure(DateAndTimeFailure dateAndTimeFailure) {
		this.dateAndTimeFailure = dateAndTimeFailure;
	}

	public PlayerFailure getPlayerFailure() {
		return playerFailure;
	}

	public void setPlayerFailure(PlayerFailure playerFailure) {
		this.playerFailure = playerFailure;
	}

	public ScreenFailure getScreenFailure() {
		return screenFailure;
	}

	public void setScreenFailure(ScreenFailure screenFailure) {
		this.screenFailure = screenFailure;
	}
	
	
	
}
