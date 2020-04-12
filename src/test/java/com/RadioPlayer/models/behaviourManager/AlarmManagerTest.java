package com.RadioPlayer.models.behaviourManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.RadioPlayer.models.*;

public class AlarmManagerTest {

	AlarmManager alarmManager;
	
	@BeforeEach
	public void alarmManagerInit() {
		alarmManager = new AlarmManager();
	}
	
	@Test
	public void isEnabledSwitchTest() {
		alarmManager.setIsEnabled(true);
		assertTrue(alarmManager.getIsEnabled());
		alarmManager.setIsEnabled(false);
		assertFalse(alarmManager.getIsEnabled());
	}
	
	@Test
	public void setAlarmHourAndMinute() {
		alarmManager.setTriggerHour(14);
		alarmManager.setTriggerMinute(25);
		
		assertEquals(14, alarmManager.getTriggerHour());
		assertEquals(25, alarmManager.getTriggerMinute());
	}
	
	@Test
	public void setIllegalAlarmHourAndMinuteValue() {
	 
	  Assertions.assertThrows(IllegalArgumentException.class, () -> { alarmManager.setTriggerHour(25); });
	 
	  Assertions.assertThrows(IllegalArgumentException.class, () -> { alarmManager.setTriggerMinute(62); });
	}
	
	@Test
	public void defaultValueForAlarmProperties() {
		AlarmManager alarmManager = new AlarmManager(null);
		
		assertEquals(25, alarmManager.getTriggerHour());
		assertEquals(60, alarmManager.getTriggerMinute());
		assertFalse(alarmManager.getIsEnabled());
	}
	
	
	
}
