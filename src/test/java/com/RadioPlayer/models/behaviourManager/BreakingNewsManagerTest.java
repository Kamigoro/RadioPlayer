package com.RadioPlayer.models.behaviourManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.RadioPlayer.models.*;

public class BreakingNewsManagerTest {
	
	BreakingNewsManager breakingNewsManager;
	
	@BeforeEach
	public void volumeManagerInit() {
		breakingNewsManager = new BreakingNewsManager();
	}
	
	@Test
	public void isWorkingSwitchTest() {
		breakingNewsManager.setIsWorking(true);
		assertTrue(breakingNewsManager.isWorking());

		breakingNewsManager.setIsWorking(false);
		assertFalse(breakingNewsManager.isWorking());
	}
	
	@Test
	public void isActivatedSwitchTest() {
		breakingNewsManager.setIsActivated(true);
		assertTrue(breakingNewsManager.isActivated());

		breakingNewsManager.setIsActivated(false);
		assertFalse(breakingNewsManager.isActivated());
	}	
	
	@Test
	public void defaultValueForBreakingNewsProperties() {
		BreakingNewsManager breakingNewsManager =  new BreakingNewsManager(null);
		
		assertTrue(breakingNewsManager.isWorking());
		assertTrue(breakingNewsManager.isActivated());
		breakingNewsManager.stop();
	}
}
