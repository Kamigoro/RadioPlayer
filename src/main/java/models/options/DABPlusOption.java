package models.options;

import models.RadioPlayer;

public class DABPlusOption implements IOption {

	private RadioPlayer radio;
	
	public DABPlusOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		// TODO Implémenter l'activation de l'option
		System.out.println("Options : Option DAB activée");
	}

	@Override
	public void desactivate() {
		// Implémenter la désactivation de l'option
		System.out.println("Options : Option DAB désactivée");
	}


}
