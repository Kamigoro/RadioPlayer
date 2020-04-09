package models.behaviourManager;

import models.RadioPlayer;

public class AudioOutManager {

	private boolean isWorking;
	private boolean isEnabled;
	private RadioPlayer radio;
	
	public AudioOutManager(RadioPlayer radio) {
		this.radio = radio;
		isWorking = true;
		isEnabled = false;
	}
	
	public boolean isWorking() {
		return isWorking;
	}
	
	public void setIsWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}
	
	public boolean isEnabled() {
		return this.isEnabled;
	}
	
	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}
