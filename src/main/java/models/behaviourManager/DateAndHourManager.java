package models.behaviourManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import models.*;

public class DateAndHourManager extends Thread {
	
	private RadioPlayer radio; 
	private int currentHour, currentMinute, currentSecond, currentDay, currentMonth, currentYear;

	public DateAndHourManager(RadioPlayer radio) {
		System.out.println("BehaviourManager : Un gestionnaire de date et heure est attaché à  la radio");
		this.radio = radio;
		getCurrentTime();
		this.start();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				secondIncrement();
				checkIfAlarmMustBeTriggered();
				System.out.println("Année :" +currentYear +" Mois :" +currentMonth +" Jour :" +currentDay +" Heure :" +currentHour +" Minute : " +currentMinute);
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Permet d'effectuer 2 tests
	 * 	1) Si une l'option d'alarme est bien configurée pour la radio
	 * 	2) Si elle est configurée, est-il l'heure de la déclencher
	 */
	private void checkIfAlarmMustBeTriggered() {
		
		if(radio.getAlarmManager() != null && radio.getAlarmManager().getIsEnabled()) {
			if(radio.getAlarmManager().getTriggerHour() == currentHour && radio.getAlarmManager().getTriggerMinute() == currentMinute) {
				radio.getAlarmManager().trigger();
			}
		}
		
	}
	
	/**
	 * Permet de récupérer l'heure (currentHour) et la minute (currentMinute) actuelle à partir du pc
	 */
	private void getCurrentTime() {
		
		Calendar rightNow = Calendar.getInstance();
		currentSecond = rightNow.get(Calendar.SECOND);
		currentMinute = rightNow.get(Calendar.MINUTE);
		currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
		currentDay = rightNow.get(Calendar.DAY_OF_MONTH);
		currentMonth = rightNow.get(Calendar.MONTH) + 1;
		currentYear = rightNow.get(Calendar.YEAR);
	}
	
	private void secondIncrement() {
		if(currentSecond < 59) {
			currentSecond ++;
		} else {
			currentSecond = 0;
			minutIncrement();
		}
	}
	
	private void minutIncrement() {
		if(currentMinute < 59) {
			currentMinute ++;
		} else {
			currentMinute = 0;
			hourIncrement();
		}
	}
	
	private void hourIncrement() {
		if(currentHour < 23) {
			currentHour ++;
		} else {
			currentHour = 0;
			dayIncrement();
		}	
	}
	
	private void dayIncrement() {
		if(currentDay < howManyDaysInaMonth()) {
			currentDay ++;
		} else {
			currentDay = 1;
			monthIncrement();
		}
	}
	
	private void monthIncrement() {
		if(currentMonth < 12) {
			currentMonth ++;
		} else {
			currentMonth = 1;
			yearIncrement();
		}
	}
	
	private void yearIncrement() {
		currentYear++;
	}
	
	private int howManyDaysInaMonth() {
		if ( currentMonth == 4 || currentMonth == 6 || currentMonth == 9 || currentMonth == 11 ) {
			
			return 30;  	
		}  
		else if ( currentMonth == 2 )
		{
            if ((currentYear % 400 == 0) || ((currentYear % 4 == 0) && (currentYear % 100 != 0))) {
            	
                return 29;
            } else {
            	
                return 28;
            }	
		} else {
			
			return 31;
		}
	}
	
	public int[] getAllDateAndTimeProperties() {
		
		int[] dateAndTimeProperties = new int[5];
		
		dateAndTimeProperties[0] = currentMinute;
		dateAndTimeProperties[1] = currentHour;
		dateAndTimeProperties[2] = currentDay;
		dateAndTimeProperties[3] = currentMonth;
		dateAndTimeProperties[4] = currentYear;
		
		return dateAndTimeProperties;
		
	}
	
	public void setAllDateAndTimeProperties(int minut, int hour, int day, int month, int year) {
		currentSecond = 0;
		currentMinute = minut;
		currentHour = hour;
		currentDay = day;
		currentMonth = month;
		currentYear = year;
	}
}
