package models.options;

import models.RadioPlayer;

public class SecondarySpeakerOption implements IOption {

	private RadioPlayer radio;
	
	public SecondarySpeakerOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		System.out.println("Options : Option de deuxième haut parleur activée");
	}

	@Override
	public void desactivate() {
		System.out.println("Options : Option de deuxième haut parleur désactivée");
	}

}
