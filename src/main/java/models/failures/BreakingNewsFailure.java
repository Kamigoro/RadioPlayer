package models.failures;

import models.RadioPlayer;

public class BreakingNewsFailure implements IFailure {

	private RadioPlayer radio;
	
	public BreakingNewsFailure(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		radio.getBreakingNewsManager().setIsWorking(false);
	}

	@Override
	public void desactivate() {
		if(radio.getBreakingNewsManager() != null) {
			radio.getBreakingNewsManager().setIsWorking(true);
		}
	}

}
