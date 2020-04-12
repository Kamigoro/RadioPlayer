package com.RadioPlayer.states;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.behaviourManager.AlarmManager;
import com.RadioPlayer.models.behaviourManager.AudioOutManager;
import com.RadioPlayer.models.enums.AlarmMenu;
import com.RadioPlayer.models.enums.InputSignalMenu;

public class SignalManagementStateTest {

	private SignalManagementState signalManagementState;
	private RadioPlayer radio;
	private AlarmManager alarmManager;
	
	@BeforeEach
	public void signalManagementStateInit() {
		radio = new RadioPlayer();
		alarmManager = new AlarmManager(radio);
		radio.setAlarmManager(alarmManager);
		signalManagementState = new SignalManagementState();
		signalManagementState.setRadio(radio);
	}
	
	@Test
	public void signalManagementStateSwitchTest() {
		signalManagementState.setSelectedInputSignal(InputSignalMenu.DAB);
		
		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.rightClick();});
		assertEquals(InputSignalMenu.FM, signalManagementState.getSelectedInputSignal());
		
		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.rightClick();});
		assertEquals(InputSignalMenu.USB, signalManagementState.getSelectedInputSignal());
		
		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.rightClick();});
		assertEquals(InputSignalMenu.AuxIn, signalManagementState.getSelectedInputSignal());
		
		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.leftClick();});
		assertEquals(InputSignalMenu.USB, signalManagementState.getSelectedInputSignal());
		
		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.leftClick();});
		assertEquals(InputSignalMenu.FM, signalManagementState.getSelectedInputSignal());
		
		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.leftClick();});
		assertEquals(InputSignalMenu.DAB, signalManagementState.getSelectedInputSignal());

	}
	
	@Test
	public void alarmClickTest() {
		
		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.alarmClick();});
		assertTrue(alarmManager.getIsEnabled());
		
		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.alarmClick();});
		assertFalse(alarmManager.getIsEnabled());
		
		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.alarmClick();});
		assertTrue(alarmManager.getIsEnabled());
	}
	
	@Test
	public void auxOutClickTest() {
		AudioOutManager audioOutManager = new AudioOutManager(radio);
		radio.setAudiOutManager(audioOutManager);
		signalManagementState.setRadio(radio);
		
		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.auxOutClick();});
		assertTrue(audioOutManager.isEnabled());
		

		Assertions.assertThrows(NullPointerException.class, () -> {signalManagementState.auxOutClick();});
		assertFalse(audioOutManager.isEnabled());
	}
}
