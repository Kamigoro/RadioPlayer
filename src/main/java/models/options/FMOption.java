package models.options;

import models.RadioPlayer;

public class FMOption implements IOption {

	private RadioPlayer radio;
	
	public FMOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		// TODO Implémenter l'activation de l'option
		System.out.println("Options : Option FM activée");
	}

	@Override
	public void desactivate() {
		// TODO Implémenter la désactivation de l'option
		System.out.println("Options : Option FM dÃ©sactivée");
	}
	
}
