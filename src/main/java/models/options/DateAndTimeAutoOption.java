package models.options;

import models.RadioPlayer;

public class DateAndTimeAutoOption implements IOption {

	private RadioPlayer radio;
	
	public DateAndTimeAutoOption() {
	}
	
	@Override
	public void activate() {
		System.out.println("Options : Option date et heure auto activée");
		radio.getDateAndHourManager().setIsInAutoMode(true);
		radio.getDateAndHourManager().getCurrentTime();
	}

	@Override
	public void desactivate() {
		// TODO Implémenter la désactivation de l'option
		radio.getDateAndHourManager().setIsInAutoMode(false);
		System.out.println("Options : Option date et heure auto désactivée");
	}
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}

}
