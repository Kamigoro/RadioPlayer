package com.RadioPlayer.models.failures;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.behaviourManager.BreakingNewsManager;

public class BreakingNewsFailureTest {

	private BreakingNewsFailure breakingNewsFailure;
	private RadioPlayer radio;
	
	@BeforeEach
	public void breakingNewsFailureInit() {
		radio = new RadioPlayer();
		breakingNewsFailure = new BreakingNewsFailure(radio);
		radio.setBreakingNewsManager(new BreakingNewsManager());
	}

	@Test
	public void activateDesactivateBreakingNewsFailureTest() {
		breakingNewsFailure.activate();
		assertFalse(radio.getBreakingNewsManager().isWorking());
		
		breakingNewsFailure.desactivate();
		assertTrue(radio.getBreakingNewsManager().isWorking());
	}
}
