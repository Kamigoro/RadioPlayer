package models.options;

import models.RadioPlayer;

public class DateAndTimeAutoOption implements IOption {

	private RadioPlayer radio;
	
	public DateAndTimeAutoOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		// TODO Implémenter l'activation de l'option
		System.out.println("Options : Option date et heure auto activée");
	}

	@Override
	public void desactivate() {
		// TODO Implémenter la désactivation de l'option
		System.out.println("Options : Option date et heure auto désactivée");
	}
	
	
}
