package models.options;

import models.RadioPlayer;
import models.players.FMPlayer;

public class FMSupport implements IOption {

	
	private RadioPlayer radio;
	
	public FMSupport() {
	}
	
	@Override
	public void activate() {
		if(radio.getFmPlayer()==null) {
			System.out.println("Options : Un player FM a été créé");
			radio.setFmPlayer(new FMPlayer(radio));
		}
	}

	@Override
	public void desactivate() {
		if(radio.getFmPlayer()!=null) {
			radio.getFmPlayer().stopPlayer();
			radio.setFmPlayer(null);
		}
		System.out.println("Options : Un player FM a été désactivé");
	}
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}

}
