package models.behaviourManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import models.*;

public class DateAndHourManager extends Thread {
	
	private RadioPlayer radio; 
	private int currentHour, currentMinute;

	public DateAndHourManager(RadioPlayer radio) {
		System.out.println("BehaviourManager : Un gestionnaire de date et heure est attach� � la radio");
		this.radio = radio;
		this.start();
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				getCurrentTime();
				checkIfAlarmMustBeTriggered();
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
	 * Permet de r�cup�rer l'heure (currentHour) et la minute (currentMinute) actuelle � partir du pc
	 */
	private void getCurrentTime() {
		
		Calendar rightNow = Calendar.getInstance();
		currentHour = rightNow.get(Calendar.HOUR_OF_DAY);
		currentMinute = rightNow.get(Calendar.MINUTE);
		
	}
	
	
}
