package models.failures;

import models.RadioPlayer;

public class AudioOutFailure implements IFailure{

	private RadioPlayer radio;
	
	public AudioOutFailure(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		radio.getAudioOutManager().setIsWorking(false);
	}

	@Override
	public void desactivate() {
		if(radio.getAudioOutManager() != null) {
			radio.getAudioOutManager().setIsWorking(true);
		}
	}

}
