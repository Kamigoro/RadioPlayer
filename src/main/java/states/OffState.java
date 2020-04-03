package states;

import models.RadioPlayer;

//TODO Impl�menter la navigation dans cet �tat
public class OffState implements IRadioState {

	private RadioPlayer radio;
	
	public OffState(RadioPlayer radio) {
		this.radio = radio;
		System.out.println("STATES : La radio est en �tat OFF");
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
		System.out.println("STATES : Passage de la radio en �tat ON");
		radio.setCurrentState(new IdleState(radio));
	}
	
	@Override
	public void alarmClick() {
		
	}

}
