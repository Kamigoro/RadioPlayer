package models.failures;

import models.RadioPlayer;

public class DateAndTimeFailure implements IFailure {

	private RadioPlayer radio;
	
	public DateAndTimeFailure(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		radio.getDateAndHourManager().setAllDateAndTimeProperties(0, 0, 1, 1, 1970);
	}

	@Override
	public void desactivate() {
		radio.getDateAndHourManager().getCurrentTime();
	}

}
