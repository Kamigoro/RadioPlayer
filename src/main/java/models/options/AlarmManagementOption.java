package models.options;

import models.BreakingNewsSender;
import models.RadioPlayer;
import models.behaviourManager.AlarmManager;

public class AlarmManagementOption implements IOption {

	private RadioPlayer radio;
	
	public AlarmManagementOption() {
	}
	
	@Override
	public void activate() {
		System.out.println("Options : Option Alarme activée");
		if(radio.getAlarmManager()==null) {
			radio.setAlarmManager(new AlarmManager(radio));
		}
	}

	@Override
	public void desactivate() {
		System.out.println("Options : Option Alarme désactivée");
		radio.setAlarmManager(null);
	}
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}

}
