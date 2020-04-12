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
import com.RadioPlayer.models.behaviourManager.AutotuneManager;
import com.RadioPlayer.models.players.AUXPlayer;

public class AuxInSupportTest {

	private AUXInSupport auxInSupport;
	private RadioPlayer radio;
	
	@BeforeEach
	public void auxInSupportOptionInit() {
		auxInSupport = new AUXInSupport();
		radio = new RadioPlayer();
		auxInSupport.setRadioPlayer(radio);
	}
	
	@Test
	public void auxInSupportActivateDesactivateTest() {
		auxInSupport.activate();
		assertNotNull(radio.getAuxPlayer());
		Assertions.assertThrows(NullPointerException.class, () -> {auxInSupport.desactivate();});
		assertNull(radio.getPlayer());
	}
}
