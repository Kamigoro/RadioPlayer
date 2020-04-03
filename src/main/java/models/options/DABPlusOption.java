package models.options;

import models.RadioPlayer;

public class DABPlusOption implements IOption {

	private RadioPlayer radio;
	
	public DABPlusOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		// TODO Impl�menter l'activation de l'option
		System.out.println("Options : Option DAB activ�e");
	}

	@Override
	public void desactivate() {
		// Impl�menter la d�sactivation de l'option
		System.out.println("Options : Option DAB d�sactiv�e");
	}


}
