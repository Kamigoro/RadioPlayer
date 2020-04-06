package models.options;

import models.RadioPlayer;

public class AudioOutOption implements IOption {

	private RadioPlayer radio;
	
	public AudioOutOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		System.out.println("Options : Option de sortie ext�rieur activ�e");
	}

	@Override
	public void desactivate() {
		System.out.println("Options : Option de sortie ext�rieur d�sactiv�e");
	}

}
