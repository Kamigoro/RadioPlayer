package states;

import models.RadioPlayer;

//TODO Implémenter la navigation dans cet état
public class OffState implements IRadioState {

	private RadioPlayer radio;
	
	public OffState(RadioPlayer radio) {
		this.radio = radio;
		System.out.println("STATES : La radio est en état OFF");
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
		System.out.println("STATES : Passage de la radio en état ON");
		radio.setCurrentState(new IdleState(radio));
	}
	
	@Override
	public void alarmClick() {
		
	}

}
