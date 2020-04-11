package com.RadioPlayer.models.failures;

import com.RadioPlayer.models.RadioPlayer;

public class VolumeFailure implements IFailure{

	private RadioPlayer radio;
	
	public VolumeFailure(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		radio.getVolumeManager().setWorking(false);
	}

	@Override
	public void desactivate() {
		radio.getVolumeManager().setWorking(true);
	}
}
