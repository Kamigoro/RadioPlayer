package models.options;

import models.RadioPlayer;

public class DateAndTimeAutoOption implements IOption {

	private RadioPlayer radio;
	
	public DateAndTimeAutoOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		// TODO Impl�menter l'activation de l'option
		System.out.println("Options : Option date et heure auto activ�e");
	}

	@Override
	public void desactivate() {
		// TODO Impl�menter la d�sactivation de l'option
		System.out.println("Options : Option date et heure auto d�sactiv�e");
	}
	
	
}
