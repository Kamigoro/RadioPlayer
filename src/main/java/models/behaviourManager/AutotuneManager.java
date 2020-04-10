package models.behaviourManager;

import models.*;

public class AutotuneManager {

	private RadioPlayer radio;
	
	public AutotuneManager(RadioPlayer radio) {
		this.radio = radio;
	}
	
	public void autotune() {
		radio.getPlayer().setPresetsWithAutotune();
	}
}
