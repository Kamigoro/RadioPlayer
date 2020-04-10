package models.options;

import models.RadioPlayer;
import models.behaviourManager.AudioOutManager;

public class AudioOutOption implements IOption {

	private RadioPlayer radio;
	
	public AudioOutOption() {
	}
	
	@Override
	public void activate() {
		if(radio.getAudioOutManager()==null) {
			radio.setAudiOutManager(new AudioOutManager(radio));
			radio.showAuxOut();
		}
	}

	@Override
	public void desactivate() {
		if(radio.getAudioOutManager()!=null) {
			radio.setAudiOutManager(null);
			radio.hideAuxOut();
		}
	}

	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}
	
}
