package com.RadioPlayer.models.behaviourManager;

import com.RadioPlayer.models.RadioPlayer;

public class AutotuneManager {

	private RadioPlayer radio;
	
	public AutotuneManager(RadioPlayer radio) {
		this.radio = radio;
	}
	
	public void autotune() {
		radio.getPlayer().setPresetsWithAutotune();
	}
}
