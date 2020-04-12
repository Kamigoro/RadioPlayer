package com.RadioPlayer.models.behaviourManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.RadioPlayer.models.*;

public class VolumeManagerTest {

	VolumeManager volumeManager;
	
	@BeforeEach
	public void volumeManagerInit() {
		volumeManager = new VolumeManager();
	}
	
	@Test
	public void isWorkingSwitchTest() {
		volumeManager.setWorking(true);
		assertTrue(volumeManager.isWorking());

		volumeManager.setWorking(false);
		assertFalse(volumeManager.isWorking());
	}
	
	@Test
	public void defaultValueForVolumeProperties() {
		VolumeManager volumeManager = new VolumeManager(null);
		
		assertEquals(0, volumeManager.getVolume());
		assertTrue(volumeManager.isWorking());
	}
	
	@Test
	public void setVolumeTest() {
		volumeManager.setWorking(true);
		
		Assertions.assertThrows(NullPointerException.class, () -> { volumeManager.setVolume(50);});
		assertEquals(50, volumeManager.getVolume());
	}
}
