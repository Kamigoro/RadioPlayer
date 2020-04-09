package models.behaviourManager;

import models.RadioPlayer;

/**
 * Classe gérant le volume de la radio
 * @author Dylan
 *
 */
public class VolumeManager {

	private int volume;
	private boolean isWorking;
	private RadioPlayer radio;
	
	public VolumeManager(RadioPlayer radio) {
		this.radio = radio;
		volume = 0;
		isWorking = true;
	}
	
	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		if(isWorking) {
			this.volume = volume;
			radio.changeVolume(Integer.toString(volume));
		}
	}

	public boolean isWorking() {
		return isWorking;
	}

	public void setWorking(boolean isWorking) {
		this.isWorking = isWorking;
	}
}
