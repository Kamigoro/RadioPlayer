package models.options;

import models.RadioPlayer;
import models.players.USBPlayer;

public class USBSupport implements IOption {

	private RadioPlayer radio;
	
	public USBSupport(RadioPlayer radioPlayer) {
		this.radio = radio;
	}
	
	@Override
	public void activate() {
		if(radio.getUsbPlayer()==null) {
			System.out.println("Player : Un player USB a été créé");
			radio.setUsbPlayer(new USBPlayer());
		}
	}

	@Override
	public void desactivate() {
		if(radio.getUsbPlayer()!=null) {
			radio.getUsbPlayer().stopPlayer();
			radio.setUsbPlayer(null);
			System.out.println("Player : Un player USB a été désactivé");
		}
	}

}
