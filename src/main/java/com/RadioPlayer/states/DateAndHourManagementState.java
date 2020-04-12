package com.RadioPlayer.states;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.constants.Constant;
import com.RadioPlayer.models.enums.DateAndTimeMenu;
import com.RadioPlayer.models.enums.MonthInYear;

public class DateAndHourManagementState implements IRadioState {

	private RadioPlayer radio;
	private DateAndTimeMenu selectedDateAndTimeProperty;
	private DateAndTimeMenu dateAndTimeMenu;
	private int dateAndTimeHour,dateAndTimeMinute,dateAndTimeDay,dateAndTimeMonth,dateAndTimeYear;
	private MonthInYear monthInYear;
	
	public DateAndHourManagementState() {}

	public DateAndHourManagementState(RadioPlayer radio) {
		this.radio = radio;
		radio.openDateAndTimeMenu();
		selectedDateAndTimeProperty = dateAndTimeMenu.Hour;
		radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		configureDateAndTimeProperties();
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
			selectedDateAndTimeProperty = dateAndTimeMenu.Minute;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		} else if(selectedDateAndTimeProperty == dateAndTimeMenu.Minute) {
			selectedDateAndTimeProperty = dateAndTimeMenu.Hour;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		}
	}

	@Override
	public void rightClick() {
		if(selectedDateAndTimeProperty == dateAndTimeMenu.Hour) {
			selectedDateAndTimeProperty = dateAndTimeMenu.Minute;
			radio.changeSelectedMenuDateAndTime(selectedDateAndTimeProperty);
		} else if(selectedDateAndTimeProperty == dateAndTimeMenu.Minute) {
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
		case Minute :
			minuteIncrementOrDecrement(true);
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
		case Minute :
			minuteIncrementOrDecrement(false);
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
		radio.getDateAndHourManager().setAllDateAndTimeProperties(dateAndTimeMinute, dateAndTimeHour, dateAndTimeDay, (dateAndTimeMonth -1), dateAndTimeYear);
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
	
	@Override
	public void auxOutClick() {
		if(radio.getAudioOutManager()!= null && radio.getAudioOutManager().isWorking()) {
			radio.getAudioOutManager().setIsEnabled(!radio.getAudioOutManager().isEnabled());//Passer de activé à non activé et inversément
			radio.changeAuxOutStatus(radio.getAudioOutManager().isEnabled());//Changer l'interface graphique
		}
	}
	
	@Override
	public void preset1Click(boolean isForSavingOrForLoading) {
		if (isForSavingOrForLoading == Constant.savingPreset) {
			radio.saveCurrentMediaAsPreset1();
		}else {
			radio.loadPreset1();
		}
	}

	@Override
	public void preset2Click(boolean isForSavingOrForLoading) {
		if (isForSavingOrForLoading == Constant.savingPreset) {
			radio.saveCurrentMediaAsPreset2();
		}else {
			radio.loadPreset2();
		}
	}

	@Override
	public void preset3Click(boolean isForSavingOrForLoading) {
		if (isForSavingOrForLoading == Constant.savingPreset) {
			radio.saveCurrentMediaAsPreset3();
		}else {
			radio.loadPreset3();
		}
	}
	
	/**
	 * Permet d'initaliser les valeurs de l'interface du menu date et heure à partir des informations de la radio
	 */
	private void configureDateAndTimeProperties() {
		int[] dateAndTimeProperties = radio.getDateAndHourManager().getAllDateAndTimeProperties();
		
		radio.editAllDateAndTimeProperties(dateAndTimeProperties);
		dateAndTimeMinute = dateAndTimeProperties[0];
		dateAndTimeHour = dateAndTimeProperties[1];
		dateAndTimeDay = dateAndTimeProperties[2];
		dateAndTimeMonth = dateAndTimeProperties[3];
		dateAndTimeYear = dateAndTimeProperties[4];
	}
	
	/**
	 * Permet l'incrémentation ou la décrémentation des minutes si report supérieur ou inférieur appel à la méthode d'incrémentation/décrémentation des heures
	 * @param isIncremented si vrai alors incrémentation sinon décrementation
	 */
	private void minuteIncrementOrDecrement(boolean isIncremented) {
		if(isIncremented) {
			if(dateAndTimeMinute < Constant.maxMinuteInAnHour) {
				dateAndTimeMinute ++;
			} else {
				dateAndTimeMinute = Constant.minMinuteInAnHour;
				hourIncrementOrDecrement(true);
			}
		} else {
			if(dateAndTimeMinute > Constant.minMinuteInAnHour) {
				dateAndTimeMinute --;
			} else {
				dateAndTimeMinute = Constant.maxMinuteInAnHour;
				hourIncrementOrDecrement(false);
			}
		}
		radio.editDateAndTimeProperty(dateAndTimeMenu.Minute, dateAndTimeMinute);
	}
	
	/**
	 * Permet l'incrémentation ou la décrémentation des heures si report supérieur ou inférieur appel à la méthode d'incrémentation/décrémentation des jours
	 * @param isIncremented si vrai alors incrémentation sinon décrementation
	 */
	private void hourIncrementOrDecrement(boolean isIncremented) {
		if(isIncremented) {
			if(dateAndTimeHour < Constant.maxHourInADay) {
				dateAndTimeHour ++;
			} else {
				dateAndTimeHour = Constant.minHourInADay;
				dayIncrementOrDecrement(true);
			}
		} else {
			if(dateAndTimeHour > Constant.minHourInADay) {
				dateAndTimeHour --;
			} else {
				dateAndTimeHour = Constant.maxHourInADay;
				dayIncrementOrDecrement(false);
			}
		}
		radio.editDateAndTimeProperty(dateAndTimeMenu.Hour, dateAndTimeHour);
	}
	
	/**
	 * Permet l'incrémentation ou la décrémentation des jours si report supérieur ou inférieur appel à la méthode d'incrémentation/décrémentation des mois
	 * @param isIncremented si vrai alors incrémentation sinon décrementation
	 */
	private void dayIncrementOrDecrement(boolean isIncremented) {
		if(isIncremented) {
			if(dateAndTimeDay < howManyDaysInAMonth(dateAndTimeMonth - 1)) {
				dateAndTimeDay ++;
			} else {
				dateAndTimeDay = Constant.minDayInAMonth;
				monthIncrementOrDecrement(true);
			}
		} else {
			if(dateAndTimeDay > Constant.minDayInAMonth) {
				dateAndTimeDay --;
			} else {
				dateAndTimeDay = howManyDaysInAMonth(dateAndTimeMonth - 2);
				monthIncrementOrDecrement(false);
			}
		}
		radio.editDateAndTimeProperty(dateAndTimeMenu.Day, dateAndTimeDay);
	}
	
	/**
	 * Permet l'incrémentation ou la décrémentation des mois si report supérieur ou inférieur appel à la méthode d'incrémentation/décrémentation des années
	 * @param isIncremented si vrai alors incrémentation sinon décrementation
	 */
	private void monthIncrementOrDecrement(boolean isIncremented) {
		if(isIncremented) {
			if(dateAndTimeMonth < Constant.maxMonthInAYear) {
				dateAndTimeMonth ++;
			} else {
				dateAndTimeMonth = Constant.minMonthInAYear;
				yearIncrementOrDecrement(true);
			}
		} else {
			if(dateAndTimeMonth > Constant.minMonthInAYear) {
				dateAndTimeMonth --;
			} else {
				dateAndTimeMonth = Constant.maxMonthInAYear;
				yearIncrementOrDecrement(false);
			}
		}
		radio.editDateAndTimeProperty(dateAndTimeMenu.Month, dateAndTimeMonth);
	}
	
	/**
	 * Permet l'incrémentation ou la décrémentation des années
	 * @param isIncremented si vrai alors incrémentation sinon décrementation
	 */
	private void yearIncrementOrDecrement(boolean isIncremented) {
		if(isIncremented) {
			dateAndTimeYear++;
		} else {
			dateAndTimeYear --;
		}
		radio.editDateAndTimeProperty(dateAndTimeMenu.Year, dateAndTimeYear);
	}
	
	private int howManyDaysInAMonth(int month) {
		if ( month == monthInYear.APRIL.ordinal() || month == monthInYear.JUNE.ordinal() || month == monthInYear.SEPTEMBER.ordinal() || month == monthInYear.NOVEMBER.ordinal() ) {
			
			return 30;  	
		}  
		else if ( month == monthInYear.FEBRUARY.ordinal() )
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

	public RadioPlayer getRadio() {
		return radio;
	}

	public void setRadio(RadioPlayer radio) {
		this.radio = radio;
	}

	public DateAndTimeMenu getSelectedDateAndTimeProperty() {
		return selectedDateAndTimeProperty;
	}

	public void setSelectedDateAndTimeProperty(DateAndTimeMenu selectedDateAndTimeProperty) {
		this.selectedDateAndTimeProperty = selectedDateAndTimeProperty;
	}

	public DateAndTimeMenu getDateAndTimeMenu() {
		return dateAndTimeMenu;
	}

	public void setDateAndTimeMenu(DateAndTimeMenu dateAndTimeMenu) {
		this.dateAndTimeMenu = dateAndTimeMenu;
	}

	public int getDateAndTimeHour() {
		return dateAndTimeHour;
	}

	public void setDateAndTimeHour(int dateAndTimeHour) {
		this.dateAndTimeHour = dateAndTimeHour;
	}

	public int getDateAndTimeMinute() {
		return dateAndTimeMinute;
	}

	public void setDateAndTimeMinute(int dateAndTimeMinute) {
		this.dateAndTimeMinute = dateAndTimeMinute;
	}

	public int getDateAndTimeDay() {
		return dateAndTimeDay;
	}

	public void setDateAndTimeDay(int dateAndTimeDay) {
		this.dateAndTimeDay = dateAndTimeDay;
	}

	public int getDateAndTimeMonth() {
		return dateAndTimeMonth;
	}

	public void setDateAndTimeMonth(int dateAndTimeMonth) {
		this.dateAndTimeMonth = dateAndTimeMonth;
	}

	public int getDateAndTimeYear() {
		return dateAndTimeYear;
	}

	public void setDateAndTimeYear(int dateAndTimeYear) {
		this.dateAndTimeYear = dateAndTimeYear;
	}
}
