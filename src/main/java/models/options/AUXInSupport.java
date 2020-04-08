package models.options;

import models.RadioPlayer;
import models.players.AUXPlayer;

public class AUXInSupport implements IOption {

	private RadioPlayer radio;
	
	public AUXInSupport(RadioPlayer radioPlayer) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		if(radio.getAuxPlayer()==null) {
			System.out.println("Player : Un player auxilière a été créé");
			radio.setAuxPlayer(new AUXPlayer());
		}
	}

	@Override
	public void desactivate() {
		if(radio.getAuxPlayer()!=null) {
			System.out.println("Player : Un player auxilière a été désactivé");
			radio.getAuxPlayer().stopPlayer();
			radio.setAuxPlayer(null);
		}
	}

}
