package states;

import models.RadioPlayer;
import models.enums.DateAndTimeMenu;

//TODO Implémenter la navigation dans cet état
public class DateAndHourManagementState implements IRadioState {

	private RadioPlayer radio;
	private DateAndTimeMenu selectedDateAndTime;
	private DateAndTimeMenu dateAndTimeMenu;
	
	public DateAndHourManagementState(RadioPlayer radio) {
		this.radio = radio;
		radio.openDateAndTimeMenu();
		selectedDateAndTime = dateAndTimeMenu.Hour;
		radio.changeSelectedMenuDateAndTime(selectedDateAndTime);
		System.out.println("STATES : La radio est en état de gestion des heures");
	}
	
	@Override
	public void leftClick() {
		if(selectedDateAndTime == dateAndTimeMenu.Year) {
			selectedDateAndTime = dateAndTimeMenu.Month;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTime);
		} else if(selectedDateAndTime == dateAndTimeMenu.Month) {
			selectedDateAndTime = dateAndTimeMenu.Day;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTime);
		} else if(selectedDateAndTime == dateAndTimeMenu.Day) {
			selectedDateAndTime = dateAndTimeMenu.Minut;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTime);
		} else if(selectedDateAndTime == dateAndTimeMenu.Minut) {
			selectedDateAndTime = dateAndTimeMenu.Hour;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTime);
		}
	}

	@Override
	public void rightClick() {
		if(selectedDateAndTime == dateAndTimeMenu.Hour) {
			selectedDateAndTime = dateAndTimeMenu.Minut;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTime);
		} else if(selectedDateAndTime == dateAndTimeMenu.Minut) {
			selectedDateAndTime = dateAndTimeMenu.Day;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTime);
		} else if(selectedDateAndTime == dateAndTimeMenu.Day) {
			selectedDateAndTime = dateAndTimeMenu.Month;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTime);
		} else if(selectedDateAndTime == dateAndTimeMenu.Month) {
			selectedDateAndTime = dateAndTimeMenu.Year;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTime);
		}
	}

	@Override
	public void upClick() {
	}

	@Override
	public void downClick() {
	}

	@Override
	public void okClick() {
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
