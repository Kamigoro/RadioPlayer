package models.behaviourManager;

import models.*;
import models.enums.DisplayType;

public class AlarmManager {

	private int triggerHour, triggerMinute;
	private RadioPlayer radio;
	private boolean isEnabled;

	//TODO Peut �tre impl�menter le Observer Pattern
	public AlarmManager(RadioPlayer radio) {
		System.out.println("BehaviourManager : Un gestionnaire d'alarme est attach� � la radio");
		this.radio = radio;
		//Quand on cr�e une nouvelle alarme elle ne se d�clenchera par d�faut
		triggerHour = 25;
		triggerMinute = 60;
		isEnabled = false;
	}
	
	public void trigger() {
		//TODO Impl�menter le vrai d�clenchement de l'alarme
		this.radio.displayMessageOnMainScreen(DisplayType.Alarm,"Il est "+triggerHour+":"+triggerMinute+", votre alarme se d�clenche");	}
	
	public int getTriggerHour() {
		return triggerHour;
	}

	public void setTriggerHour(int triggerHour) {
		//ne pas pouvoir mettre d'alarme avec des heures incoh�rentes
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
		//ne pas pouvoir mettre d'alarme avec des minutes incoh�rentes
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
