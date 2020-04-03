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
		System.out.println("Options : L'option Breaking news a été activée");
		radio.setBreakingNewsManager(new BreakingNewsManager(radio));
	}

	@Override
	public void desactivate() {
		System.out.println("Options : L'option Breaking news a été désactivée");
		//Peut générer une erreur si nous essayons de setter une variable à  un objet qui n'a pas été instancié
		//Nous devons donc check s'il a Ã©tÃ© instanciÃ© et ensuite essayÃ© de set la variable
		if(radio.getBreakingNewsManager() != null) {
			radio.getBreakingNewsManager().setIsActivated(false);
			try {
				//Permet d'interrompe l'opération bloquante d'écoute
				radio.getBreakingNewsManager().getServerSocket().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			radio.setBreakingNewsManager(null);
		}
		
	}

}
