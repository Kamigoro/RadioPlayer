package states;

import models.RadioPlayer;
import models.enums.InputSignalMenu;
import models.players.DABPlayer;
import models.players.FMPlayer;

//TODO Impl�menter la navigation dans cet �tat
public class SignalManagementState implements IRadioState {

	private RadioPlayer radio;
	private InputSignalMenu selectedInputSignal;
	private InputSignalMenu inputSignalMenu;
	
	public SignalManagementState(RadioPlayer radio) {
		this.radio = radio;
		radio.openInputSignalMenu();
		selectedInputSignal = inputSignalMenu.DAB;
		radio.changeSelectedMenuInputSignal(selectedInputSignal);
		System.out.println("STATES : La radio est en �tat de gestion des signaux");
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
			radio.setCurrentState(new IdleState(radio));
			radio.openInitialScreen();
			break;
		case FM:
			if(radio.getFmPlayer()!=null) {
				radio.getPlayer().stopPlayer();
				radio.setPlayer(radio.getFmPlayer());
				radio.setCurrentState(new IdleState(radio));
				radio.openInitialScreen();
			}
			break;
		case AuxIn:
			if(radio.getAuxPlayer()!=null) {
				radio.getPlayer().stopPlayer();
				radio.setPlayer(radio.getAuxPlayer());
				radio.setCurrentState(new IdleState(radio));
				radio.openInitialScreen();
			}
			break;
		case USB:
			if(radio.getUsbPlayer()!=null) {
				radio.getPlayer().stopPlayer();
				radio.setPlayer(radio.getUsbPlayer());
				radio.setCurrentState(new IdleState(radio));
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
			radio.getAudioOutManager().setIsEnabled(!radio.getAudioOutManager().isEnabled());//Passer de activ� � non activ� et invers�ment
			radio.changeAuxOutStatus(radio.getAudioOutManager().isEnabled());//Changer l'interface graphique
		}
	}

}
