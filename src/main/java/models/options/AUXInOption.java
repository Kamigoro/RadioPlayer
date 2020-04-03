package models.options;

import models.RadioPlayer;

public class AUXInOption implements IOption{

	private RadioPlayer radio;
	
	public AUXInOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		// TODO Impl�menter l'activation de l'option
		System.out.println("Options : Option AUXIn activ�e");
	}

	@Override
	public void desactivate() {
		// TODO Impl�menter d�sactivation de l'option
		System.out.println("Options : Option AUXIn d�sactiv�e");
	}

}
