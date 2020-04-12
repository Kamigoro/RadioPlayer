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
import com.RadioPlayer.models.behaviourManager.BreakingNewsManager;

public class BreakingNewsOptionTest {
	
	private BreakingNewsOption breakingNewsOption;
	private RadioPlayer radio;
	private BreakingNewsManager breakingNewsManager;
	
	@BeforeEach
	public void breakingNewsOptionInit() {
		breakingNewsOption = new BreakingNewsOption();
		breakingNewsManager = new BreakingNewsManager();
		radio = new RadioPlayer();
		breakingNewsOption.setRadioPlayer(radio);
	}
	
	@Test
	public void breakingNewsActivatedTest() {
		breakingNewsOption.activate();
		assertNotNull(breakingNewsManager);
		
		breakingNewsOption.desactivate();
		assertFalse(breakingNewsManager.isActivated());
		assertNull(radio.getBreakingNewsManager());
	}

}
