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
			radio.setFmPlayer(new FMPlayer(radio));
		}
	}

	@Override
	public void desactivate() {
		if(radio.getFmPlayer()!=null) {
			radio.getFmPlayer().stopPlayer();
			radio.setFmPlayer(null);
		}
	}
	
	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}

}
