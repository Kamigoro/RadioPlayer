package com.RadioPlayer.models.options;

import com.RadioPlayer.models.RadioPlayer;

public interface IOption {

	/**
	 * Active une option et rattache un gestionnaire d'options uniquement si l'option n'est déjà pas activé.
	 */
	public void activate();
	
	/**
	 * Désactive une option, et donc rendre le gestionnaire d'option attaché null
	 */
	public void desactivate();
	
	public void setRadioPlayer(RadioPlayer radio);
}
