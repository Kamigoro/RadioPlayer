package models.options;

import models.RadioPlayer;

public class SecondarySpeakerOption implements IOption {

	private RadioPlayer radio;
	
	public SecondarySpeakerOption() {
	}
	
	@Override
	public void activate() {
		radio.enableSecondarySpeaker();
	}

	@Override
	public void desactivate() {
		radio.disableSecondarySpeaker();
	}

	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}
	
}
