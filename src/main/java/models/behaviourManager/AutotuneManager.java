package models.behaviourManager;

import models.*;

public class AutotuneManager {

	private RadioPlayer radio;
	
	public AutotuneManager(RadioPlayer radio) {
		System.out.println("BehaviourManager : Un gestionnaire d'autotune est attaché à la radio");
		this.radio = radio;
	}
	
	//TODO Impl�menter le comportement d'autotune
	public void autotune() {
		System.out.println("J'autotune");
	}
}
