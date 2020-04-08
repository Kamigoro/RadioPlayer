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
		System.out.println("Options : Option Autotune activée");
		if(radio.getAutotuneManager()==null) {
			radio.setAutotuneManager(new AutotuneManager(radio));
		}
	}

	@Override
	public void desactivate() {
		System.out.println("Options : Option Autotune désactivée");
		radio.setAutotuneManager(null);
	}	
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}
}
