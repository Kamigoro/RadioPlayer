package com.RadioPlayer.models.behaviourManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.RadioPlayer.models.*;

public class AudioOutManagerTest {
	
	AudioOutManager audioOutManager;
	
	@BeforeEach
	public void volumeManagerInit() {
		audioOutManager = new AudioOutManager();
	}
	
	@Test
	public void isWorkingSwitchTest() {
		audioOutManager.setIsWorking(true);
		assertTrue(audioOutManager.isWorking());

		audioOutManager.setIsWorking(false);
		assertFalse(audioOutManager.isWorking());
	}
	
	@Test
	public void isEnabledSwitchTest() {
		audioOutManager.setIsEnabled(true);
		assertTrue(audioOutManager.isEnabled());

		audioOutManager.setIsEnabled(false);
		assertFalse(audioOutManager.isEnabled());
	}
	
	@Test
	public void defaultValueForAudioOutProperties() {
		AudioOutManager audioOutManager = new AudioOutManager(null);
		
		assertTrue(audioOutManager.isWorking());
		assertFalse(audioOutManager.isEnabled());
	}

}
