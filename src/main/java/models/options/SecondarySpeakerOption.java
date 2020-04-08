package models.options;

import models.RadioPlayer;

public class SecondarySpeakerOption implements IOption {

	private RadioPlayer radio;
	
	public SecondarySpeakerOption() {
	}
	
	@Override
	public void activate() {
		System.out.println("Options : Option de deuxi�me haut parleur activ�e");
	}

	@Override
	public void desactivate() {
		System.out.println("Options : Option de deuxi�me haut parleur d�sactiv�e");
	}

	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}
	
}
