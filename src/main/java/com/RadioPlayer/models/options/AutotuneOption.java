package com.RadioPlayer.models.options;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.behaviourManager.AutotuneManager;

public class AutotuneOption implements IOption {

	private RadioPlayer radio;
	
	public AutotuneOption() {
	}
	
	@Override
	public void activate() {
		if(radio.getAutotuneManager()==null) {
			radio.setAutotuneManager(new AutotuneManager(radio));
		}
	}

	@Override
	public void desactivate() {
		radio.setAutotuneManager(null);
	}	
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}
}
