package com.RadioPlayer.models.options;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.players.AUXPlayer;

public class AUXInSupport implements IOption {

	private RadioPlayer radio;
	
	public AUXInSupport() {
	}
	
	@Override
	public void activate() {
		if(radio.getAuxPlayer()==null) {
			radio.setAuxPlayer(new AUXPlayer(radio));
		}
	}

	@Override
	public void desactivate() {
		if(radio.getAuxPlayer()!=null) {
			radio.getAuxPlayer().stopPlayer();
			radio.setAuxPlayer(null);
		}	
	}
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}

}
