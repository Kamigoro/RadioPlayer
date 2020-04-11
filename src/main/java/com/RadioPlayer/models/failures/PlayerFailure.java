package com.RadioPlayer.models.failures;

import com.RadioPlayer.models.RadioPlayer;

public class PlayerFailure implements IFailure {
	
	private RadioPlayer radio;
	
	public PlayerFailure(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		radio.getPlayer().setIsWorking(false);
	}

	@Override
	public void desactivate() {
		radio.getPlayer().setIsWorking(true);
	}
}
