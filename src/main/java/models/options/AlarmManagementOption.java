package models.options;

import models.BreakingNewsSender;
import models.RadioPlayer;
import models.behaviourManager.AlarmManager;

public class AlarmManagementOption implements IOption {

	private RadioPlayer radio;
	
	public AlarmManagementOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		System.out.println("Options : Option Alarme activ�e");
		radio.setAlarmManager(new AlarmManager(radio));
	}

	@Override
	public void desactivate() {
		System.out.println("Options : Option Alarme d�sactiv�e");
		radio.setAlarmManager(null);
	}

}
