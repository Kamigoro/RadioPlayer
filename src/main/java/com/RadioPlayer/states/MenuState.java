package com.RadioPlayer.states;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.constants.Constant;
import com.RadioPlayer.models.enums.MainMenu;
import com.RadioPlayer.models.options.DateAndTimeAutoOption;


public class MenuState implements IRadioState {
	
	private RadioPlayer radio;
	private MainMenu selectedMenu;
	private MainMenu mainMenu;
	
	public MenuState(RadioPlayer radio) {
		this.radio = radio;
		radio.openMenuScreen();
		selectedMenu = mainMenu.DateAndHour;
		radio.changeSelectedMenu(selectedMenu);
	}
	
	@Override
	public void leftClick() {
		if (selectedMenu == mainMenu.Alarm) {
			selectedMenu = mainMenu.InputSignal;
			radio.changeSelectedMenu(selectedMenu);
		} else if(selectedMenu == mainMenu.InputSignal) {
			selectedMenu = mainMenu.DateAndHour;
			radio.changeSelectedMenu(selectedMenu);
		}
	}

	@Override
	public void rightClick() {
		if (selectedMenu == mainMenu.DateAndHour) {
			selectedMenu = mainMenu.InputSignal ;
			radio.changeSelectedMenu(selectedMenu);
		} else if(selectedMenu == mainMenu.InputSignal) {
			selectedMenu = mainMenu.Alarm;
			radio.changeSelectedMenu(selectedMenu);
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
		
		switch(selectedMenu) {
	    	case DateAndHour :
	    		radio.setCurrentState(new DateAndHourManagementState(radio));
	    		break;
	    	
	    	case InputSignal : 
	    		radio.setCurrentState(new SignalManagementState(radio));
	    		break;
	    	
	    	case Alarm :
	    		if(radio.getAlarmManager() != null) {
		    		radio.setCurrentState(new AlarmManagementState(radio));
	    		}
	    		break;
		}
	}

	@Override
	public void menuClick() {
		radio.openInitialScreen();
	}

	@Override
	public void autotuneClick() {
	}

	@Override
	public void onOffClick() {
		radio.setCurrentState(new OffState(radio));
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

}