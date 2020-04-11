package com.RadioPlayer.states;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.constants.Constant;


public class OffState implements IRadioState {

	private RadioPlayer radio;
	
	public OffState(RadioPlayer radio) {
		this.radio = radio;
		radio.getPlayer().stopPlayer();
		radio.openOffScreen();
	}
	
	@Override
	public void leftClick() {
	}

	@Override
	public void rightClick() {
	}

	@Override
	public void upClick() {
	}

	@Override
	public void downClick() {
	}

	@Override
	public void okClick() {
	}

	@Override
	public void menuClick() {
	}

	@Override
	public void autotuneClick() {
	}

	@Override
	public void onOffClick() {
		radio.openInitialScreen();
	}
	
	@Override
	public void alarmClick() {
		
	}

	@Override
	public void auxOutClick() {
	}
	
	@Override
	public void preset1Click(boolean isForSavingOrForLoading) {
	}

	@Override
	public void preset2Click(boolean isForSavingOrForLoading) {
	}

	@Override
	public void preset3Click(boolean isForSavingOrForLoading) {
	}
	
}
