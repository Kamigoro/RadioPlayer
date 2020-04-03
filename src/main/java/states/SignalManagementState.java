package states;

import models.RadioPlayer;
import models.enums.InputSignalMenu;

//TODO Implémenter la navigation dans cet état
public class SignalManagementState implements IRadioState {

	private RadioPlayer radio;
	private InputSignalMenu selectedInputSignal;
	private InputSignalMenu inputSignalMenu;
	
	public SignalManagementState(RadioPlayer radio) {
		this.radio = radio;
		radio.openInputSignalMenu();
		selectedInputSignal = inputSignalMenu.DAB;
		radio.changeSelectedMenuInputSignal(selectedInputSignal);
		System.out.println("STATES : La radio est en état de gestion des signaux");
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

}
