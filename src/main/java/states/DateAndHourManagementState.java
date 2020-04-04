package states;

import models.RadioPlayer;
import models.enums.DateAndTimeMenu;

//TODO Implémenter la navigation dans cet état
public class DateAndHourManagementState implements IRadioState {

	private RadioPlayer radio;
	private DateAndTimeMenu selectedDateAndTimeProperty;
	private DateAndTimeMenu dateAndTimeMenu;
	private int dateAndTimeHour,dateAndTimeMinut,dateAndTimeDay,dateAndTimeMonth,dateAndTimeYear;
	
	public DateAndHourManagementState(RadioPlayer radio) {
		this.radio = radio;
		radio.openDateAndTimeMenu();
		selectedDateAndTimeProperty = dateAndTimeMenu.Hour;
		radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		configureDateAndTimeProperties();
		System.out.println("STATES : La radio est en état de gestion des heures");
	}
	
	@Override
	public void leftClick() {
		if(selectedDateAndTimeProperty == dateAndTimeMenu.Year) {
			selectedDateAndTimeProperty = dateAndTimeMenu.Month;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		} else if(selectedDateAndTimeProperty == dateAndTimeMenu.Month) {
			selectedDateAndTimeProperty = dateAndTimeMenu.Day;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		} else if(selectedDateAndTimeProperty == dateAndTimeMenu.Day) {
			selectedDateAndTimeProperty = dateAndTimeMenu.Minut;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		} else if(selectedDateAndTimeProperty == dateAndTimeMenu.Minut) {
			selectedDateAndTimeProperty = dateAndTimeMenu.Hour;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		}
	}

	@Override
	public void rightClick() {
		if(selectedDateAndTimeProperty == dateAndTimeMenu.Hour) {
			selectedDateAndTimeProperty = dateAndTimeMenu.Minut;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		} else if(selectedDateAndTimeProperty == dateAndTimeMenu.Minut) {
			selectedDateAndTimeProperty = dateAndTimeMenu.Day;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		} else if(selectedDateAndTimeProperty == dateAndTimeMenu.Day) {
			selectedDateAndTimeProperty = dateAndTimeMenu.Month;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		} else if(selectedDateAndTimeProperty == dateAndTimeMenu.Month) {
			selectedDateAndTimeProperty = dateAndTimeMenu.Year;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		}
	}

	@Override
	public void upClick() {
		switch (selectedDateAndTimeProperty) {
		case Minut :
			minutIncrementOrDecrement(true);
			break;
		case Hour:
			hourIncrementOrDecrement(true);
			break;
		case Day :
			dayIncrementOrDecrement(true);
			break;
		case Month:
			monthIncrementOrDecrement(true);
			break;
		case Year:
			yearIncrementOrDecrement(true);
			break;
		}
	}

	@Override
	public void downClick() {
		switch (selectedDateAndTimeProperty) {
		case Minut :
			minutIncrementOrDecrement(false);
			break;
		case Hour:
			hourIncrementOrDecrement(false);
			break;
		case Day :
			dayIncrementOrDecrement(false);
			break;
		case Month:
			monthIncrementOrDecrement(false);
			break;
		case Year:
			yearIncrementOrDecrement(false);
			break;
		}
	}

	@Override
	public void okClick() {
		radio.getDateAndHourManager().setAllDateAndTimeProperties(dateAndTimeMinut, dateAndTimeHour, dateAndTimeDay, dateAndTimeMonth, dateAndTimeYear);
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
	
	private void configureDateAndTimeProperties() {
		int[] dateAndTimeProperties = radio.getDateAndHourManager().getAllDateAndTimeProperties();
		
		radio.editAllDateAndTimeProperties(dateAndTimeProperties);
		dateAndTimeMinut = dateAndTimeProperties[0];
		dateAndTimeHour = dateAndTimeProperties[1];
		dateAndTimeDay = dateAndTimeProperties[2];
		dateAndTimeMonth = dateAndTimeProperties[3];
		dateAndTimeYear = dateAndTimeProperties[4];
	}
	
	private void minutIncrementOrDecrement(boolean isIncremented) {
		if(isIncremented) {
			if(dateAndTimeMinut < 59) {
				dateAndTimeMinut ++;
			} else {
				dateAndTimeMinut = 0;
				hourIncrementOrDecrement(true);
			}
		} else {
			if(dateAndTimeMinut > 0) {
				dateAndTimeMinut --;
			} else {
				dateAndTimeMinut = 59;
				hourIncrementOrDecrement(false);
			}
		}
		radio.editDateAndTimeProperty(dateAndTimeMenu.Minut, dateAndTimeMinut);
	}
	
	private void hourIncrementOrDecrement(boolean isIncremented) {
		if(isIncremented) {
			if(dateAndTimeHour < 23) {
				dateAndTimeHour ++;
			} else {
				dateAndTimeHour = 0;
				dayIncrementOrDecrement(true);
			}
		} else {
			if(dateAndTimeHour > 0) {
				dateAndTimeHour --;
			} else {
				dateAndTimeHour = 23;
				dayIncrementOrDecrement(false);
			}
		}
		radio.editDateAndTimeProperty(dateAndTimeMenu.Hour, dateAndTimeHour);
	}
	
	private void dayIncrementOrDecrement(boolean isIncremented) {
		if(isIncremented) {
			if(dateAndTimeDay < howManyDaysInAMonth()) {
				dateAndTimeDay ++;
			} else {
				dateAndTimeDay = 1;
				monthIncrementOrDecrement(true);
			}
		} else {
			if(dateAndTimeDay > 0) {
				dateAndTimeDay --;
			} else {
				dateAndTimeDay = howManyDaysInAMonth();
				monthIncrementOrDecrement(false);
			}
		}
		radio.editDateAndTimeProperty(dateAndTimeMenu.Day, dateAndTimeDay);
	}
	
	private void monthIncrementOrDecrement(boolean isIncremented) {
		if(isIncremented) {
			if(dateAndTimeMonth < 12) {
				dateAndTimeMonth ++;
			} else {
				dateAndTimeMonth = 1;
				yearIncrementOrDecrement(true);
			}
		} else {
			if(dateAndTimeMonth > 0) {
				dateAndTimeMonth --;
			} else {
				dateAndTimeMonth = 12;
				yearIncrementOrDecrement(false);
			}
		}
		radio.editDateAndTimeProperty(dateAndTimeMenu.Month, dateAndTimeMonth);
	}
	
	private void yearIncrementOrDecrement(boolean isIncremented) {
		if(isIncremented) {
			dateAndTimeYear++;
		} else {
			dateAndTimeYear --;
		}
		radio.editDateAndTimeProperty(dateAndTimeMenu.Year, dateAndTimeYear);
	}
	
	private int howManyDaysInAMonth() {
		if ( dateAndTimeMonth == 4 || dateAndTimeMonth == 6 || dateAndTimeMonth == 9 || dateAndTimeMonth == 11 ) {
			
			return 30;  	
		}  
		else if ( dateAndTimeMonth == 2 )
		{
            if ((dateAndTimeYear % 400 == 0) || ((dateAndTimeYear % 4 == 0) && (dateAndTimeYear % 100 != 0))) {
            	
                return 29;
            } else {
            	
                return 28;
            }	
		} else {
			
			return 31;
		}
	}

}
