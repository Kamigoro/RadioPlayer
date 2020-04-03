package states;

import models.RadioPlayer;

public interface IRadioState {
	
	public void leftClick();
	public void rightClick();
	public void upClick();
	public void downClick();
	public void okClick();
	public void menuClick();
	public void autotuneClick();
	public void onOffClick();
	
	/**
	 * Fonction ex�cut�e lors de l'appui sur le bouton alarme de l'interface principal permet l'activation / d�sactivation
	 * d'une alarme
	 */
	public void alarmClick();

}
