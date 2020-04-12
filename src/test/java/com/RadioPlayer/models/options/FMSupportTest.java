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
import com.RadioPlayer.models.players.FMPlayer;

public class FMSupportTest {

	private FMSupport fmSupport;
	private RadioPlayer radio;
	
	@BeforeEach
	public void fmSupportOptionInit() {
		fmSupport = new FMSupport();
		radio = new RadioPlayer();
		fmSupport.setRadioPlayer(radio);
	}
	
	@Test
	public void fmSupportActivateDesactivateTest() {
		fmSupport.activate();
		assertNotNull(radio.getFmPlayer());
		Assertions.assertThrows(NullPointerException.class, () -> {fmSupport.desactivate();});
		assertNull(radio.getPlayer());
	}
}
