package models.options;

import java.io.IOException;

import models.RadioPlayer;
import models.behaviourManager.BreakingNewsManager;

public class BreakingNewsOption implements IOption {

	private RadioPlayer radio;
	
	public BreakingNewsOption(RadioPlayer radio) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		System.out.println("Options : L'option Breaking news a �t� activ�e");
		radio.setBreakingNewsManager(new BreakingNewsManager(radio));
	}

	@Override
	public void desactivate() {
		System.out.println("Options : L'option Breaking news a �t� d�sactiv�e");
		//Peut g�n�rer une erreur si nous essayons de setter une variable � un objet qui n'a pas �t� instanci�
		//Nous devons donc check s'il a été instancié et ensuite essayé de set la variable
		if(radio.getBreakingNewsManager() != null) {
			radio.getBreakingNewsManager().setIsActivated(false);
			try {
				//Permet d'interrompe l'op�ration bloquante d'�coute
				radio.getBreakingNewsManager().getServerSocket().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			radio.setBreakingNewsManager(null);
		}
		
	}

}
