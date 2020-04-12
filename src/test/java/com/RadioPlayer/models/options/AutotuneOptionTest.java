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

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.behaviourManager.AutotuneManager;

public class AutotuneOptionTest {

	private AutotuneOption autotuneOption;
	private RadioPlayer radio;
	
	@BeforeEach
	public void autotuneOptionInit() {
		autotuneOption = new AutotuneOption();
		radio = new RadioPlayer();
		autotuneOption.setRadioPlayer(radio);
	}
	
	@Test
	public void autotuneActivateDesactivateTest() {
		autotuneOption.activate();
		assertNotNull(radio.getAutotuneManager());
		autotuneOption.desactivate();
		assertNull(radio.getAutotuneManager());
	}
	
	
}
