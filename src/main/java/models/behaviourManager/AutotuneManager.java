package models.behaviourManager;

import models.*;

public class AutotuneManager {

	private RadioPlayer radio;
	
	public AutotuneManager(RadioPlayer radio) {
		System.out.println("BehaviourManager : Un gestionnaire d'autotune est attaché à la radio");
		this.radio = radio;
	}
	
	public void autotune() {
		System.out.println("BehaviourManager : Un autotune a été demandé");
		radio.setPreset1(radio.getPlayer().getMediaAtIndex(0));
		radio.setPreset2(radio.getPlayer().getMediaAtIndex(1));
		radio.setPreset3(radio.getPlayer().getMediaAtIndex(2));
	}
}
