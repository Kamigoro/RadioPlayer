package com.RadioPlayer.states;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.behaviourManager.AlarmManager;
import com.RadioPlayer.models.behaviourManager.AudioOutManager;

public class IdleStateTest {

	private IdleState idleState;
	private RadioPlayer radio;
	private AlarmManager alarmManager;
	
	@BeforeEach
	public void idleStateInit() {
		radio = new RadioPlayer();
		alarmManager = new AlarmManager(radio);
		radio.setAlarmManager(alarmManager);
		idleState = new IdleState();
		idleState.setRadio(radio);
	}
	
	@Test
	public void alarmClickTest() {
		
		Assertions.assertThrows(NullPointerException.class, () -> {idleState.alarmClick();});
		assertTrue(alarmManager.getIsEnabled());
		
		Assertions.assertThrows(NullPointerException.class, () -> {idleState.alarmClick();});
		assertFalse(alarmManager.getIsEnabled());
		
		Assertions.assertThrows(NullPointerException.class, () -> {idleState.alarmClick();});
		assertTrue(alarmManager.getIsEnabled());
	}
	
	@Test
	public void auxOutClickTest() {
		AudioOutManager audioOutManager = new AudioOutManager(radio);
		radio.setAudiOutManager(audioOutManager);
		idleState.setRadio(radio);
		
		Assertions.assertThrows(NullPointerException.class, () -> {idleState.auxOutClick();});
		assertTrue(audioOutManager.isEnabled());
		

		Assertions.assertThrows(NullPointerException.class, () -> {idleState.auxOutClick();});
		assertFalse(audioOutManager.isEnabled());
	}
}
