package com.RadioPlayer.models.player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.players.DABPlayer;

public class DABPlayerTest {

	private RadioPlayer radio;
	private DABPlayer dabPlayer;
	
	@BeforeEach
	public void dabPlayerInit() {
		radio = new RadioPlayer();
		dabPlayer = new DABPlayer(radio);
	}
	
	@Test
	public void dabPlayerListPresetTest() {
		assertEquals(3, dabPlayer.getListOfPresets().size());
		
		
	}
}
