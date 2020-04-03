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
	 * Fonction exécutée lors de l'appui sur le bouton alarme de l'interface principal permet l'activation / désactivation
	 * d'une alarme
	 */
	public void alarmClick();

}
