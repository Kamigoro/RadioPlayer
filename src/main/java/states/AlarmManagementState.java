package states;

import models.RadioPlayer;
import models.enums.AlarmMenu;

public class AlarmManagementState implements IRadioState {

	private RadioPlayer radio;
	private AlarmMenu selectedAlarmProperty;
	private AlarmMenu alarmMenu;
	private int alarmHour, alarmMinut;
	
	public AlarmManagementState(RadioPlayer radio) {
		this.radio = radio;
		radio.openAlarmMenu();
		selectedAlarmProperty = alarmMenu.Hour;
		radio.changeSelectedMenuAlarm(selectedAlarmProperty);
		alarmHour = radio.getAlarmManager().getTriggerHour();
		alarmMinut = radio.getAlarmManager().getTriggerMinute();
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
			if(alarmHour < 24)
			{
				alarmHour ++;
			} else {
				alarmHour = 0;
			}
			radio.editAlarmProperties(selectedAlarmProperty, alarmHour);
			break;
		case Minut :
			if(alarmMinut < 60)
			{
				alarmMinut ++;
			} else {
				alarmMinut = 0;
			}
			radio.editAlarmProperties(selectedAlarmProperty, alarmMinut);
			break;
		}
	}

	@Override
	public void downClick() {
		switch (selectedAlarmProperty) {
		case Hour:
			if(alarmHour > 0)
			{
				alarmHour --;
			} else {
				alarmHour = 24;
			}
			radio.editAlarmProperties(selectedAlarmProperty, alarmHour);
			break;
		case Minut :
			if(alarmMinut > 0)
			{
				alarmMinut --;
			} else {
				alarmMinut = 60;
			}
			radio.editAlarmProperties(selectedAlarmProperty, alarmMinut);
			break;
	
	}
	}

	@Override
	public void okClick() {
		radio.getAlarmManager().setTriggerHour(alarmHour);
		radio.getAlarmManager().setTriggerMinute(alarmMinut);
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
