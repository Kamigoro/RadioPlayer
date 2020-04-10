package models.options;

import java.io.IOException;

import models.RadioPlayer;
import models.behaviourManager.BreakingNewsManager;

public class BreakingNewsOption implements IOption {

	private RadioPlayer radio;
	
	public BreakingNewsOption() {
	}
	
	@Override
	public void activate() {
		if(radio.getBreakingNewsManager()== null) {
			radio.setBreakingNewsManager(new BreakingNewsManager(radio));
		}
	}

	@Override
	public void desactivate() {
		if(radio.getBreakingNewsManager() != null) {
			radio.getBreakingNewsManager().setIsActivated(false);
			radio.setBreakingNewsManager(null);
		}
		
	}

	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}
	
	
}
