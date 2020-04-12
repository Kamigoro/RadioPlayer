package com.RadioPlayer.models.failures;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RadioPlayer.models.RadioPlayer;

public class ScreenFailureTest {

	private RadioPlayer radio;
	private ScreenFailure screenFailure;
	
	@BeforeEach
	public void screenFailureInit() {
		radio = new RadioPlayer();
		screenFailure = new ScreenFailure(radio);
	}

	@Test
	public void activateDesactivateScreenFailureTest() {
		Assertions.assertThrows(NullPointerException.class, () -> { screenFailure.activate();});
		assertFalse(radio.isScreenWorking());
		
		screenFailure.desactivate();
		assertTrue(radio.isScreenWorking());
	}
}
