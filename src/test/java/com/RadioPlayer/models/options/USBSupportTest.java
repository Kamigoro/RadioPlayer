package com.RadioPlayer.models.options;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.RadioPlayer.models.*;
import com.RadioPlayer.models.players.USBPlayer;

public class USBSupportTest {

	private USBSupport usbSupport;
	private RadioPlayer radio;
	
	@BeforeEach
	public void usbSupportOptionInit() {
		usbSupport = new USBSupport();
		radio = new RadioPlayer();
		usbSupport.setRadioPlayer(radio);
	}
	
	@Test
	public void fmSupportActivateDesactivateTest() {
		usbSupport.activate();
		assertNotNull(radio.getUsbPlayer());
		Assertions.assertThrows(NullPointerException.class, () -> {usbSupport.desactivate();});
		assertNull(radio.getPlayer());
	}
}
