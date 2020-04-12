package com.RadioPlayer.models.failures;

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
import com.RadioPlayer.models.behaviourManager.AudioOutManager;

public class AudioOutFailureTest {
	
	private AudioOutFailure audioOutFailure;
	private RadioPlayer radio;
	
	@BeforeEach
	public void audioOutFailureInit() {
		radio = new RadioPlayer();
		audioOutFailure = new AudioOutFailure(radio);
		radio.setAudiOutManager(new AudioOutManager());
	}

	@Test
	public void activateDesactivateAudioOutFailureTest() {
		audioOutFailure.activate();
		assertFalse(radio.getAudioOutManager().isWorking());
		
		audioOutFailure.desactivate();
		assertTrue(radio.getAudioOutManager().isWorking());
	}
}
