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
import com.RadioPlayer.models.behaviourManager.AudioOutManager;

public class AudioOutOptionTest {
	
	private AudioOutOption audioOutOption;
	private RadioPlayer radio;
	
	@BeforeEach
	public void audioOutOptionInit() {
		audioOutOption = new AudioOutOption();
		radio = new RadioPlayer();
		audioOutOption.setRadioPlayer(radio);
	}
	
	@Test
	public void audioOutActivateDesactivateTest() {
		Assertions.assertThrows(NullPointerException.class, () -> { audioOutOption.activate();});
		assertNotNull(radio.getAudioOutManager());
		Assertions.assertThrows(NullPointerException.class, () -> {audioOutOption.desactivate();});
		assertNull(radio.getAudioOutManager());
	}
	
}
