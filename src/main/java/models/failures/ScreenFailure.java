package models.failures;

import models.RadioPlayer;

public class ScreenFailure implements IFailure {

	private RadioPlayer radio;
	
	public ScreenFailure(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		radio.setIsScreenWorking(false);
	}

	@Override
	public void desactivate() {
		radio.setIsScreenWorking(true);
	}

}
