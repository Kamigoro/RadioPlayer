package models.options;

import models.RadioPlayer;
import models.players.USBPlayer;

public class USBSupport implements IOption {

	private RadioPlayer radio;
	
	public USBSupport() {
		
	}
	
	@Override
	public void activate() {
		if(radio.getUsbPlayer()==null) {
			System.out.println("Options : Un player USB a été créé");
			radio.setUsbPlayer(new USBPlayer(radio));
		}
	}

	@Override
	public void desactivate() {
		if(radio.getUsbPlayer()!=null) {
			radio.getUsbPlayer().stopPlayer();
			radio.setUsbPlayer(null);	
		}
		System.out.println("Options : Un player USB a été désactivé");
	}

	@Override
	public void setRadioPlayer(RadioPlayer radio) {
		this.radio = radio;
	}

}
