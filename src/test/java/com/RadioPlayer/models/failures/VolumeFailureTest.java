package com.RadioPlayer.models.failures;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.behaviourManager.VolumeManager;

public class VolumeFailureTest {
	
	private RadioPlayer radio;
	private VolumeFailure volumeFailure;
	
	@BeforeEach
	public void volumeFailureInit() {
		radio = new RadioPlayer();
		volumeFailure = new VolumeFailure(radio);
		radio.setVolumeManager(new VolumeManager());
	}

	@Test
	public void activateDesactivateVolumeFailureTest() {
		volumeFailure.activate();
		assertFalse(radio.getVolumeManager().isWorking());
		
		volumeFailure.desactivate();
		assertTrue(radio.getVolumeManager().isWorking());
	}

}
