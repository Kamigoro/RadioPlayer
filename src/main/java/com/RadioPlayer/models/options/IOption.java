package com.RadioPlayer.models.options;

import com.RadioPlayer.models.RadioPlayer;

public interface IOption {

	/**
	 * Active une option et rattache un gestionnaire d'options uniquement si l'option n'est d�j� pas activ�.
	 */
	public void activate();
	
	/**
	 * D�sactive une option, et donc rendre le gestionnaire d'option attach� null
	 */
	public void desactivate();
	
	public void setRadioPlayer(RadioPlayer radio);
}
