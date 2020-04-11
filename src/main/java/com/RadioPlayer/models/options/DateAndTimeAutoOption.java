package com.RadioPlayer.models.options;

import com.RadioPlayer.models.RadioPlayer;

public class DateAndTimeAutoOption implements IOption {

	private RadioPlayer radio;
	
	public DateAndTimeAutoOption() {
	}
	
	@Override
	public void activate() {
		radio.getDateAndHourManager().setIsInAutoMode(true);
		radio.getDateAndHourManager().adjustToSystemTime();
	}

	@Override
	public void desactivate() {
		radio.getDateAndHourManager().setIsInAutoMode(false);
	}
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}

}
