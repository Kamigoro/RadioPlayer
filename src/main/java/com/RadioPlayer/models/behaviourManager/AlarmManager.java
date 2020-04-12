package com.RadioPlayer.models.behaviourManager;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.enums.DisplayType;

public class AlarmManager {

	private int triggerHour, triggerMinute;
	private RadioPlayer radio;
	private boolean isEnabled;
	
	public AlarmManager() {
		
	}

	public AlarmManager(RadioPlayer radio) {
		this.radio = radio;
		//Quand on crée une nouvelle alarme elle ne se déclenchera par défaut
		triggerHour = 25;
		triggerMinute = 60;
		isEnabled = false;
	}
	
	public void trigger() {
		this.radio.displayMessageOnMainScreen(DisplayType.Alarm,"Il est "+triggerHour+":"+triggerMinute+", votre alarme se déclenche");	
	}
	
	public void stopAlarm() {
		this.radio.displayMessageOnMainScreen(DisplayType.Alarm,"");	
	}
	
	public int getTriggerHour() {
		return triggerHour;
	}

	public void setTriggerHour(int triggerHour) {
		//ne pas pouvoir mettre d'alarme avec des heures incohérentes
		if(triggerHour>=0 && triggerHour<24) {
			this.triggerHour = triggerHour;
		}else {
			throw new IllegalArgumentException("La valeur d'heure est incorrecte");
		}
	}

	public int getTriggerMinute() {
		return triggerMinute;
	}

	public void setTriggerMinute(int triggerMinute) {
		//ne pas pouvoir mettre d'alarme avec des minutes incohérentes
		if(triggerMinute>=0 && triggerMinute<60) {
			this.triggerMinute = triggerMinute;
		}else {
			throw new IllegalArgumentException("La valeur de minute est incorrecte");
		}
	}
	
	public boolean getIsEnabled() {
		return isEnabled;
	}
	
	public void setIsEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	
}
