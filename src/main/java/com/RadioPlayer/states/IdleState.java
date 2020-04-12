package com.RadioPlayer.states;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.constants.Constant;

public class IdleState implements IRadioState{

	private RadioPlayer radio;
	
	public IdleState() {}
	
	public IdleState(RadioPlayer radio) {
		this.radio = radio;
		radio.getPlayer().launchPlayer();
	}

	@Override
	public void leftClick() {
		radio.getPlayer().leftClick();
	}

	@Override
	public void rightClick() {
		radio.getPlayer().rightClick();
	}

	@Override
	public void upClick() {
	}

	@Override
	public void downClick() {
	}

	@Override
	public void okClick() {
		radio.getPlayer().okClick();
	}

	@Override
	public void menuClick() {
		radio.setCurrentState(new MenuState(radio));
	}

	@Override
	public void autotuneClick() {
		if(radio.getAutotuneManager() != null) {
			radio.getAutotuneManager().autotune();
		}
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
	
	public RadioPlayer getRadio() {
		return radio;
	}

	public void setRadio(RadioPlayer radio) {
		this.radio = radio;
	}
}
