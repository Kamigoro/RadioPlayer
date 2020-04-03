package models.options;

import models.RadioPlayer;

public class USBOption implements IOption {

	private RadioPlayer radio;
	
	public USBOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		// TODO Implémenter l'activation de l'option
		System.out.println("Options : Option USB activée");
	}

	@Override
	public void desactivate() {
		// TODO Implémenter la désactivation de l'option
		System.out.println("Options : Option USB désactivée");
	}

}
