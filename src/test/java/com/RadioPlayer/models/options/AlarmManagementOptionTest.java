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
import com.RadioPlayer.models.behaviourManager.AlarmManager;

public class AlarmManagementOptionTest {

	private AlarmManagementOption alarmManagementOption;
	private RadioPlayer radio;
	
	@BeforeEach
	public void alarmManagementOptionInit() {
		alarmManagementOption = new AlarmManagementOption();
		radio = new RadioPlayer();
		alarmManagementOption.setRadioPlayer(radio);
	}
	
	@Test
	public void alarmActivateTest() {
		alarmManagementOption.activate();
		assertNotNull(radio.getAlarmManager());
	}
	
	@Test
	public void alarmDesactivateTest() {
		alarmManagementOption.desactivate();
		assertNull(radio.getAlarmManager());
	}
	
	
	
}
