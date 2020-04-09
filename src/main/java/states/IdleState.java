package states;

import models.RadioPlayer;

//TODO Implémenter la navigation dans cet état
public class IdleState implements IRadioState{

	private RadioPlayer radio;
	
	public IdleState(RadioPlayer radio) {
		this.radio = radio;
		System.out.println("STATES : La radio est en état IDLE");
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

}
