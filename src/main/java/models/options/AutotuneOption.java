package models.options;

import models.BreakingNewsSender;
import models.RadioPlayer;
import models.behaviourManager.AutotuneManager;

public class AutotuneOption implements IOption {

	private RadioPlayer radio;
	
	public AutotuneOption() {
	}
	
	@Override
	public void activate() {
		System.out.println("Options : Option Autotune activ�e");
		if(radio.getAutotuneManager()==null) {
			radio.setAutotuneManager(new AutotuneManager(radio));
		}
	}

	@Override
	public void desactivate() {
		System.out.println("Options : Option Autotune d�sactiv�e");
		radio.setAutotuneManager(null);
	}	
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}
}
