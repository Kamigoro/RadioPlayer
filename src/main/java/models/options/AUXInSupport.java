package models.options;

import models.RadioPlayer;
import models.players.AUXPlayer;

public class AUXInSupport implements IOption {

	private RadioPlayer radio;
	
	public AUXInSupport() {
	}
	
	@Override
	public void activate() {
		if(radio.getAuxPlayer()==null) {
			System.out.println("Options : Un player AUXIn a été créé");
			radio.setAuxPlayer(new AUXPlayer(radio));
		}
	}

	@Override
	public void desactivate() {
		if(radio.getAuxPlayer()!=null) {
			radio.getAuxPlayer().stopPlayer();
			radio.setAuxPlayer(null);
			System.out.println("Options : Un player AUXIn a été désactivé");
		}	
	}
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}

}
