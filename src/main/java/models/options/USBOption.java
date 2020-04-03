package models.options;

import models.RadioPlayer;

public class USBOption implements IOption {

	private RadioPlayer radio;
	
	public USBOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		// TODO Impl�menter l'activation de l'option
		System.out.println("Options : Option USB activ�e");
	}

	@Override
	public void desactivate() {
		// TODO Impl�menter la d�sactivation de l'option
		System.out.println("Options : Option USB d�sactiv�e");
	}

}
