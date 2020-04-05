package states;

import models.RadioPlayer;
import models.constants.Constant;
import models.enums.AlarmMenu;
import models.constants.Constant;

public class AlarmManagementState implements IRadioState {

	private RadioPlayer radio;
	private AlarmMenu selectedAlarmProperty;
	private AlarmMenu alarmMenu;
	private int alarmHour, alarmMinute;
	
	public AlarmManagementState(RadioPlayer radio) {
		this.radio = radio;
		radio.openAlarmMenu();
		selectedAlarmProperty = alarmMenu.Hour;
		radio.changeSelectedMenuAlarm(selectedAlarmProperty);
		alarmHour = radio.getAlarmManager().getTriggerHour();
		alarmMinute = radio.getAlarmManager().getTriggerMinute();
		System.out.println("STATES : La radio est en état de gestion des alarmes");
	}
	
	@Override
	public void leftClick() {
		if(selectedAlarmProperty == alarmMenu.Minut) {
			selectedAlarmProperty = alarmMenu.Hour;
			radio.changeSelectedMenuAlarm(selectedAlarmProperty);
		}
	}

	@Override
	public void rightClick() {
		if(selectedAlarmProperty == alarmMenu.Hour) {
			selectedAlarmProperty = alarmMenu.Minut;
			radio.changeSelectedMenuAlarm(selectedAlarmProperty);
		}
	}

	@Override
	public void upClick() {
		switch (selectedAlarmProperty) {
		case Hour:
			if(alarmHour < Constant.maxHourInADay)
			{
				alarmHour ++;
			} else {
				alarmHour = Constant.minHourInADay;
			}
			radio.editAlarmProperties(selectedAlarmProperty, alarmHour);
			break;
		case Minut :
			if(alarmMinute < Constant.maxMinuteInAnHour)
			{
				alarmMinute ++;
			} else {
				alarmMinute = Constant.minMinuteInAnHour;
			}
			radio.editAlarmProperties(selectedAlarmProperty, alarmMinute);
			break;
		}
	}

	@Override
	public void downClick() {
		switch (selectedAlarmProperty) {
		case Hour:
			if(alarmHour > Constant.minHourInADay)
			{
				alarmHour --;
			} else {
				alarmHour = Constant.maxHourInADay;
			}
			radio.editAlarmProperties(selectedAlarmProperty, alarmHour);
			break;
		case Minut :
			if(alarmMinute > Constant.minMinuteInAnHour)
			{
				alarmMinute --;
			} else {
				alarmMinute = Constant.maxMinuteInAnHour;
			}
			radio.editAlarmProperties(selectedAlarmProperty, alarmMinute);
			break;
	
	}
	}

	@Override
	public void okClick() {
		radio.getAlarmManager().setTriggerHour(alarmHour);
		radio.getAlarmManager().setTriggerMinute(alarmMinute);
		radio.getAlarmManager().setIsEnabled(true);
		radio.changeAlarmStatus(radio.getAlarmManager().getIsEnabled());
		radio.setCurrentState(new IdleState(radio));
		radio.openInitialScreen();
	}

	@Override
	public void menuClick() {
		radio.setCurrentState(new MenuState(radio));
	}

	@Override
	public void autotuneClick() {
	}

	@Override
	public void onOffClick() {
	}
	
	@Override
	public void alarmClick() {
		if(radio.getAlarmManager()!=null) {
			if (radio.getAlarmManager().getIsEnabled()) {
				radio.getAlarmManager().setIsEnabled(false);		
			} else {
				radio.getAlarmManager().setIsEnabled(true);
			}
			radio.changeAlarmStatus(radio.getAlarmManager().getIsEnabled());
		}
	}

}
