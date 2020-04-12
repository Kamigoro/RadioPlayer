package com.RadioPlayer.states;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.constants.Constant;
import com.RadioPlayer.models.enums.AlarmMenu;

public class AlarmManagementState implements IRadioState {

	private RadioPlayer radio;
	private AlarmMenu selectedAlarmProperty;
	private AlarmMenu alarmMenu;
	private int alarmHour, alarmMinute;
	
	public AlarmManagementState() {}
	
	public AlarmManagementState(RadioPlayer radio) {
		this.radio = radio;
		radio.openAlarmMenu();
		selectedAlarmProperty = alarmMenu.Hour;
		radio.changeSelectedMenuAlarm(selectedAlarmProperty);
		alarmHour = radio.getAlarmManager().getTriggerHour();
		alarmMinute = radio.getAlarmManager().getTriggerMinute();
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
	public AlarmMenu getSelectedAlarmProperty() {
		return selectedAlarmProperty;
	}

	public void setSelectedAlarmProperty(AlarmMenu selectedAlarmProperty) {
		this.selectedAlarmProperty = selectedAlarmProperty;
	}
	
	public int getAlarmHour() {
		return alarmHour;
	}

	public void setAlarmHour(int alarmHour) {
		this.alarmHour = alarmHour;
	}

	public int getAlarmMinute() {
		return alarmMinute;
	}

	public void setAlarmMinute(int alarmMinute) {
		this.alarmMinute = alarmMinute;
	}

	public void setRadio(RadioPlayer radio) {
		this.radio = radio;
	}

	public RadioPlayer getRadioPlayer() {
		return this.radio;
	}

}
