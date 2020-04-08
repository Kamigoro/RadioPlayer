package models.behaviourManager;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import models.*;
import models.enums.DisplayType;
import models.enums.MonthInYear;
import models.constants.Constant;

public class DateAndHourManager extends Thread {
	
	private RadioPlayer radio; 
	private int currentHour, currentMinute, currentSecond, currentDay, currentMonth, currentYear;
	private MonthInYear month;
	DecimalFormat formatter = new DecimalFormat("00");
	private boolean isInAutoMode;//Booleen pour voir si on activ� l'heure auto
	
	public DateAndHourManager(RadioPlayer radio) {
		System.out.println("BehaviourManager : Un gestionnaire de date et heure est attach� �  la radio");
		this.radio = radio;
		getCurrentTime();
		this.start();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1000);
				secondIncrement();
				checkIfAlarmMustBeTriggered();
				// formatter permet de passer les informations de 1 digit � 2 example � la place d'afficher '0:2' pour l'heure il sera affich� '00:02'
				this.radio.displayMessageOnMainScreen(DisplayType.DateAndTime, formatter.format(currentHour) +":" +formatter.format(currentMinute) +" " +formatter.format(currentDay) +"/"+formatter.format((currentMonth+1))+"/"+currentYear);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				
			}
		}
	}
	
	/**
	 * Permet d'effectuer 2 tests
	 * 	1) Si une l'option d'alarme est bien configur�e pour la radio
	 * 	2) Si elle est configur�e, est-il l'heure de la d�clencher
	 */
	private void checkIfAlarmMustBeTriggered() {
		
		if(radio.getAlarmManager() != null && radio.getAlarmManager().getIsEnabled()) {
			if(radio.getAlarmManager().getTriggerHour() == currentHour && radio.getAlarmManager().getTriggerMinute() == currentMinute) {
				radio.getAlarmManager().trigger();
			}
		}
		
	}
	
	/**
	 * Permet de r�cup�rer l'heure et la date actuelle � partir du pc
	 */
	public void getCurrentTime() {
		
		Calendar rightNow = Calendar.getInstance();
		currentSecond = rightNow.get(Calendar.SECOND);
		currentMinute = rightNow.get(Calendar.MINUTE);
		currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
		currentDay = rightNow.get(Calendar.DAY_OF_MONTH);
		currentMonth = rightNow.get(Calendar.MONTH);
		currentYear = rightNow.get(Calendar.YEAR);
	}
	
	/**
	 * Permet d'incr�menter les secondes et si d�passement appel de la m�thode d'incr�mentation des minutes
	 */
	private void secondIncrement() {
		if(currentSecond < Constant.maxSecondInAMinut) {
			currentSecond ++;
		} else {
			currentSecond = Constant.minSecondInAMinut;
			minuteIncrement();
		}
	}
	
	/**
	 * Permet d'incr�menter les minutes et si d�passement appel de la m�thode d'incr�mentation des heures
	 */
	private void minuteIncrement() {
		if(currentMinute < Constant.maxMinuteInAnHour) {
			currentMinute ++;
		} else {
			currentMinute = Constant.minMinuteInAnHour;
			hourIncrement();
		}
	}
	
	/**
	 * Permet d'incr�menter les heures et si d�passement appel de la m�thode d'incr�mentation des jours
	 */
	private void hourIncrement() {
		if(currentHour < Constant.maxHourInADay) {
			currentHour ++;
		} else {
			currentHour = Constant.minHourInADay;
			dayIncrement();
		}	
	}
	
	/**
	 * Permet d'incr�menter les jours et si d�passement appel de la m�thode d'incr�mentation des mois
	 */
	private void dayIncrement() {
		if(currentDay < howManyDaysInAMonth()) {
			currentDay ++;
		} else {
			currentDay = Constant.minDayInAMonth;
			monthIncrement();
		}
	}
	
	/**
	 * Permet d'incr�menter les mois et si d�passement appel de la m�thode d'incr�mentation des ann�es
	 */
	private void monthIncrement() {
		if(currentMonth < Constant.maxMonthInAYear) {
			currentMonth ++;
		} else {
			currentMonth = Constant.minMonthInAYear;
			yearIncrement();
		}
	}
	
	/**
	 * Permet d'incr�menter l'ann�e 
	 */
	private void yearIncrement() {
		currentYear++;
	}
	
	/**
	 * Permet de d�terminer le nombre de jour dans un mois en tenant compte des ann�es bissextile
	 * @return retourne le nombre de jour dans un mois
	 */
	private int howManyDaysInAMonth() {
		if ( currentMonth == month.APRIL.ordinal() || currentMonth == month.JUNE.ordinal() || currentMonth == month.SEPTEMBER.ordinal() || currentMonth == month.NOVEMBER.ordinal()) {
			
			return 30;  	
		}  
		else if ( currentMonth == month.FEBRUARY.ordinal() )
		{
			// Une ann�e bissextile est soit une ann�e multiple de 400 soit une ann�e multiple de 4 et non de 100
            if ((currentYear % 400 == 0) || ((currentYear % 4 == 0) && (currentYear % 100 != 0))) {
    	
                return 29;
            } else {
            	
                return 28;
            }	
		} else {
			
			return 31;
		}
	}
	
	/**
	 * M�thode permettant d'obtenir toutes les propri�t�s de la date et l'heure sous la forme d'un vecteur d'entier
	 * @return un vecteur d'entier o� le premier �l�ment 0 correspond aux minutes et le dernier �l�ment 4 correspond � l'ann�e
	 */
	public int[] getAllDateAndTimeProperties() {
		
		int[] dateAndTimeProperties = new int[5];
		
		dateAndTimeProperties[0] = currentMinute;
		dateAndTimeProperties[1] = currentHour;
		dateAndTimeProperties[2] = currentDay;
		// Le + 1 permet de r�cup�rer les informations dans un format o� Javnvier est le mois 1 et non le z�ro
		dateAndTimeProperties[3] = currentMonth + 1;
		dateAndTimeProperties[4] = currentYear;
		
		return dateAndTimeProperties;
		
	}
	
	/**
	 * M�thode permettant de mettre � jour les propri�t�s de la date et l'heure en une fois
	 * @param minute
	 * @param hour
	 * @param day
	 * @param month
	 * @param year
	 */
	public void setAllDateAndTimeProperties(int minute, int hour, int day, int month, int year) {
		if(!isInAutoMode) {
			currentSecond = Constant.minSecondInAMinut;
			currentMinute = minute;
			currentHour = hour;
			currentDay = day;
			currentMonth = month;
			currentYear = year;
		}
	}

	public void setIsInAutoMode(boolean isInAutoMode) {
		this.isInAutoMode = isInAutoMode;
	}
	
	public boolean isInAutoMode() {
		return isInAutoMode;
	}
	
}