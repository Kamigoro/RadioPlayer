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
import com.RadioPlayer.models.enums.AlarmMenu;

public class AlarmManagementStateTest {

	private AlarmManagementState alarmManagementState;
	private RadioPlayer radio;
	private AlarmManager alarmManager;
	
	@BeforeEach
	public void alarmManagementStateInit() {
		radio = new RadioPlayer();
		radio.setCurrentState(new AlarmManagementState());
		alarmManager = new AlarmManager(radio);
		radio.setAlarmManager(alarmManager);
		alarmManagementState = new AlarmManagementState();
		alarmManagementState.setRadio(radio);
	}
	
	@Test
	public void alarmManagementStateSwitchTest() {
		alarmManagementState.setSelectedAlarmProperty(AlarmMenu.Hour);
		
		Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.rightClick();});
		
		assertEquals(AlarmMenu.Minut, alarmManagementState.getSelectedAlarmProperty());
		
		alarmManagementState.rightClick();
		
		assertEquals(AlarmMenu.Minut, alarmManagementState.getSelectedAlarmProperty());
		
		Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.leftClick();});
		
		assertEquals(AlarmMenu.Hour, alarmManagementState.getSelectedAlarmProperty());
		
		alarmManagementState.leftClick();
		
		assertEquals(AlarmMenu.Hour, alarmManagementState.getSelectedAlarmProperty());
	}
	
	@Test
	public void changeAlarmValuesTest() {
		
		alarmManagementState.setSelectedAlarmProperty(AlarmMenu.Minut);
		alarmManagementState.setAlarmHour(0);
		alarmManagementState.setAlarmMinute(0);
		
		// Incrementation des minutes + 70 cela donne 10 minutes  
		for (int i = 0; i < 70; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.upClick();});
		}	
		assertEquals(10, alarmManagementState.getAlarmMinute());
		
		// Décrémentation des minutes - 50 cela donne 20 minutes
		for (int i = 0; i < 50; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.downClick();});
		}	
		assertEquals(20, alarmManagementState.getAlarmMinute());
		
		// Incrémentation des heures + 38 cela donne 14H
		alarmManagementState.setSelectedAlarmProperty(AlarmMenu.Hour);
		for (int i = 0; i < 38; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.upClick();});
		}
		assertEquals(14, alarmManagementState.getAlarmHour());
		
		// Incrémentation des heures - 18 cela donne 20H
		alarmManagementState.setSelectedAlarmProperty(AlarmMenu.Hour);
		for (int i = 0; i < 18; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.downClick();});
		}
		assertEquals(20, alarmManagementState.getAlarmHour());
		
	}
	
	@Test
	public void setAlarmManagerPropertiesTest() {
		
		// On règle une alarme pour 14H29
		alarmManagementState.setSelectedAlarmProperty(AlarmMenu.Hour);
		alarmManagementState.setAlarmHour(14);
		alarmManagementState.setAlarmMinute(29);

		Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.okClick();});
		
		//Test des propriétés du Manager de l'alarme
		assertEquals(alarmManagementState.getAlarmHour(), alarmManager.getTriggerHour());
		assertEquals(alarmManagementState.getAlarmMinute(), alarmManager.getTriggerMinute());
		assertTrue(alarmManager.getIsEnabled());
		
	}
	
	@Test
	public void alarmClickTest() {
		
		Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.alarmClick();});
		assertTrue(alarmManager.getIsEnabled());
		
		Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.alarmClick();});
		assertFalse(alarmManager.getIsEnabled());
		
		Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.alarmClick();});
		assertTrue(alarmManager.getIsEnabled());
	}
	
	@Test
	public void auxOutClickTest() {
		AudioOutManager audioOutManager = new AudioOutManager(radio);
		radio.setAudiOutManager(audioOutManager);
		alarmManagementState.setRadio(radio);
		
		Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.auxOutClick();});
		assertTrue(audioOutManager.isEnabled());
		

		Assertions.assertThrows(NullPointerException.class, () -> {alarmManagementState.auxOutClick();});
		assertFalse(audioOutManager.isEnabled());
	}
	
}
