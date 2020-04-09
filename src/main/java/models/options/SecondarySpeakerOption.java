package models.options;

import models.RadioPlayer;

public class SecondarySpeakerOption implements IOption {

	private RadioPlayer radio;
	
	public SecondarySpeakerOption() {
	}
	
	@Override
	public void activate() {
		System.out.println("Options : Option de deuxième haut parleur activée");
		radio.enableSecondarySpeaker();
	}

	@Override
	public void desactivate() {
		System.out.println("Options : Option de deuxième haut parleur désactivée");
		radio.disableSecondarySpeaker();
	}

	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}
	
}
