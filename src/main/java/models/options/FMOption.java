package models.options;

import models.RadioPlayer;

public class FMOption implements IOption {

	private RadioPlayer radio;
	
	public FMOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		// TODO Impl�menter l'activation de l'option
		System.out.println("Options : Option FM activ�e");
	}

	@Override
	public void desactivate() {
		// TODO Impl�menter la d�sactivation de l'option
		System.out.println("Options : Option FM désactiv�e");
	}
	
}
