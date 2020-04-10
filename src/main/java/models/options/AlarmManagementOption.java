package models.options;

import models.RadioPlayer;
import models.behaviourManager.AlarmManager;

public class AlarmManagementOption implements IOption {

	private RadioPlayer radio;
	
	public AlarmManagementOption() {
	}
	
	@Override
	public void activate() {
		if(radio.getAlarmManager()==null) {
			radio.setAlarmManager(new AlarmManager(radio));
		}
	}

	@Override
	public void desactivate() {
		radio.setAlarmManager(null);
	}
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}

}
