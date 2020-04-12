package com.RadioPlayer.states;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.constants.Constant;
import com.RadioPlayer.models.enums.AlarmMenu;
import com.RadioPlayer.models.enums.InputSignalMenu;
import com.RadioPlayer.models.players.DABPlayer;
import com.RadioPlayer.models.players.FMPlayer;


public class SignalManagementState implements IRadioState {

	private RadioPlayer radio;
	private InputSignalMenu selectedInputSignal;
	private InputSignalMenu inputSignalMenu;
	
	public SignalManagementState() {}
	
	public SignalManagementState(RadioPlayer radio) {
		this.radio = radio;
		radio.openInputSignalMenu();
		selectedInputSignal = inputSignalMenu.DAB;
		radio.changeSelectedMenuInputSignal(selectedInputSignal);
	}
	
	@Override
	public void leftClick() {
		if (selectedInputSignal == inputSignalMenu.AuxIn) {
			selectedInputSignal = inputSignalMenu.USB;
			radio.changeSelectedMenuInputSignal(selectedInputSignal);
		} else if(selectedInputSignal == inputSignalMenu.USB) {
			selectedInputSignal = inputSignalMenu.FM;
			radio.changeSelectedMenuInputSignal(selectedInputSignal);
		} else if(selectedInputSignal == inputSignalMenu.FM) {
			selectedInputSignal = inputSignalMenu.DAB;
			radio.changeSelectedMenuInputSignal(selectedInputSignal);
		}
	}

	@Override
	public void rightClick() {
		if (selectedInputSignal == inputSignalMenu.DAB) {
			selectedInputSignal = inputSignalMenu.FM;
			radio.changeSelectedMenuInputSignal(selectedInputSignal);
		} else if(selectedInputSignal == inputSignalMenu.FM) {
			selectedInputSignal = inputSignalMenu.USB;
			radio.changeSelectedMenuInputSignal(selectedInputSignal);
		} else if(selectedInputSignal == inputSignalMenu.USB) {
			selectedInputSignal = inputSignalMenu.AuxIn;
			radio.changeSelectedMenuInputSignal(selectedInputSignal);
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
		switch (selectedInputSignal) {
		case DAB: 
			radio.getPlayer().stopPlayer();
			radio.setPlayer(new DABPlayer(radio));
			radio.openInitialScreen();
			break;
		case FM:
			if(radio.getFmPlayer()!=null) {
				radio.getPlayer().stopPlayer();
				radio.setPlayer(radio.getFmPlayer());
				radio.openInitialScreen();
			}
			break;
		case AuxIn:
			if(radio.getAuxPlayer()!=null) {
				radio.getPlayer().stopPlayer();
				radio.setPlayer(radio.getAuxPlayer());
				radio.openInitialScreen();
			}
			break;
		case USB:
			if(radio.getUsbPlayer()!=null) {
				radio.getPlayer().stopPlayer();
				radio.setPlayer(radio.getUsbPlayer());
				radio.openInitialScreen();
			}
			break;
		}
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

	public RadioPlayer getRadio() {
		return radio;
	}

	public void setRadio(RadioPlayer radio) {
		this.radio = radio;
	}

	public InputSignalMenu getSelectedInputSignal() {
		return selectedInputSignal;
	}

	public void setSelectedInputSignal(InputSignalMenu selectedInputSignal) {
		this.selectedInputSignal = selectedInputSignal;
	}

}
