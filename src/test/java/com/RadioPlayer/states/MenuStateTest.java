package com.RadioPlayer.states;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Menu;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.behaviourManager.AlarmManager;
import com.RadioPlayer.models.behaviourManager.AudioOutManager;
import com.RadioPlayer.models.enums.MainMenu;

public class MenuStateTest {

	private RadioPlayer radio;
	private AlarmManager alarmManager;
	private MenuState menuState;
	
	@BeforeEach
	public void menuStateInit() {
		radio = new RadioPlayer();
		alarmManager = new AlarmManager(radio);
		radio.setAlarmManager(alarmManager);
		menuState = new MenuState();
		menuState.setRadio(radio);
	}
	
	@Test
	public void menuStateSwitchTest() {
		menuState.setSelectedMenu(MainMenu.DateAndHour);
		
		// Déplacement vers la droite
		Assertions.assertThrows(NullPointerException.class, () -> {menuState.rightClick();});
		assertEquals(MainMenu.InputSignal, menuState.getSelectedMenu());
		
		Assertions.assertThrows(NullPointerException.class, () -> {menuState.rightClick();});
		assertEquals(MainMenu.Alarm, menuState.getSelectedMenu());
		
		// Déplacement vers la gauche
		Assertions.assertThrows(NullPointerException.class, () -> {menuState.leftClick();});
		assertEquals(MainMenu.InputSignal, menuState.getSelectedMenu());
		
		Assertions.assertThrows(NullPointerException.class, () -> {menuState.leftClick();});
		assertEquals(MainMenu.DateAndHour, menuState.getSelectedMenu());
		
	}
	
	@Test
	public void alarmClickTest() {
		
		Assertions.assertThrows(NullPointerException.class, () -> {menuState.alarmClick();});
		assertTrue(alarmManager.getIsEnabled());
		
		Assertions.assertThrows(NullPointerException.class, () -> {menuState.alarmClick();});
		assertFalse(alarmManager.getIsEnabled());
		
		Assertions.assertThrows(NullPointerException.class, () -> {menuState.alarmClick();});
		assertTrue(alarmManager.getIsEnabled());
	}
	
	@Test
	public void auxOutClickTest() {
		AudioOutManager audioOutManager = new AudioOutManager(radio);
		radio.setAudiOutManager(audioOutManager);
		menuState.setRadio(radio);
		
		Assertions.assertThrows(NullPointerException.class, () -> {menuState.auxOutClick();});
		assertTrue(audioOutManager.isEnabled());
		

		Assertions.assertThrows(NullPointerException.class, () -> {menuState.auxOutClick();});
		assertFalse(audioOutManager.isEnabled());
	}
}
