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
import com.RadioPlayer.models.behaviourManager.DateAndHourManager;
import com.RadioPlayer.models.constants.Constant;
import com.RadioPlayer.models.enums.AlarmMenu;
import com.RadioPlayer.models.enums.DateAndTimeMenu;

public class DateAndHourManagementStateTest {

	private DateAndHourManagementState dateAndHourManagementState;
	private RadioPlayer radio;
	private DateAndHourManager dateAndHourManager;
	private AlarmManager alarmManager;
	
	@BeforeEach
	public void dateAndHourManagementStateInit() {
		radio = new RadioPlayer();
		dateAndHourManager = new DateAndHourManager();
		radio.setDateAndHourManager(dateAndHourManager);
		alarmManager = new AlarmManager(radio);
		radio.setAlarmManager(alarmManager);
		dateAndHourManagementState = new DateAndHourManagementState();
		dateAndHourManagementState.setRadio(radio);		
	}
	
	@Test
	public void dateAndHourManagementStateSwitchTest() {
		dateAndHourManagementState.setSelectedDateAndTimeProperty(DateAndTimeMenu.Hour);
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.rightClick();});
		assertEquals(DateAndTimeMenu.Minute, dateAndHourManagementState.getSelectedDateAndTimeProperty());
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.rightClick();});
		assertEquals(DateAndTimeMenu.Day, dateAndHourManagementState.getSelectedDateAndTimeProperty());
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.rightClick();});
		assertEquals(DateAndTimeMenu.Month, dateAndHourManagementState.getSelectedDateAndTimeProperty());
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.rightClick();});
		assertEquals(DateAndTimeMenu.Year, dateAndHourManagementState.getSelectedDateAndTimeProperty());
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.leftClick();});
		assertEquals(DateAndTimeMenu.Month, dateAndHourManagementState.getSelectedDateAndTimeProperty());
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.leftClick();});
		assertEquals(DateAndTimeMenu.Day, dateAndHourManagementState.getSelectedDateAndTimeProperty());
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.leftClick();});
		assertEquals(DateAndTimeMenu.Minute, dateAndHourManagementState.getSelectedDateAndTimeProperty());
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.leftClick();});
		assertEquals(DateAndTimeMenu.Hour, dateAndHourManagementState.getSelectedDateAndTimeProperty());
	}
	
	@Test
	public void minutToHourIncrementTest() {
		
		dateAndHourManagementState.setSelectedDateAndTimeProperty(DateAndTimeMenu.Minute);
		dateAndHourManagementState.setDateAndTimeMinute(Constant.minMinuteInAnHour);
		dateAndHourManagementState.setDateAndTimeHour(Constant.minHourInADay);
		
		// Incrementation des minutes + 74 min cela donnera 1h14 avec report dans les heures
		for (int i = 0; i < 74; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.upClick();});
		}	
		assertEquals(14, dateAndHourManagementState.getDateAndTimeMinute());
		assertEquals(1, dateAndHourManagementState.getDateAndTimeHour());
		
		
		// Décrémentation des minutes - 50 cela donne 00h24
		for (int i = 0; i < 50; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.downClick();});
		}	
		assertEquals(24, dateAndHourManagementState.getDateAndTimeMinute());
		assertEquals(0, dateAndHourManagementState.getDateAndTimeHour());
	}
	
	@Test
	public void hourToDayIncrementTest() {
		
		dateAndHourManagementState.setSelectedDateAndTimeProperty(DateAndTimeMenu.Hour);
		dateAndHourManagementState.setDateAndTimeHour(Constant.minHourInADay);
		dateAndHourManagementState.setDateAndTimeDay(Constant.minDayInAMonth);
		
		// Incrementation des minutes + 74 heures cela donnera le quatrième jour à 2h
		for (int i = 0; i < 74; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.upClick();});
		}	
		assertEquals(2, dateAndHourManagementState.getDateAndTimeHour());
		assertEquals(4, dateAndHourManagementState.getDateAndTimeDay());
		
		
		// Décrémentation des minutes - 24 heures cela donne Le 3 ème jour à 02H
		for (int i = 0; i < 24; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.downClick();});
		}	
		assertEquals(2, dateAndHourManagementState.getDateAndTimeHour());
		assertEquals(3, dateAndHourManagementState.getDateAndTimeDay());
	}
	
	@Test
	public void dayToMonthIncrementTest() {
		
		dateAndHourManagementState.setSelectedDateAndTimeProperty(DateAndTimeMenu.Day);
		dateAndHourManagementState.setDateAndTimeDay(Constant.minDayInAMonth);
		dateAndHourManagementState.setDateAndTimeMonth(1);
		
		// Incrementation des minutes + 36 jours à partir du 1 er janvier cela donnera le 5 février
		for (int i = 0; i < 36; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.upClick();});
		}	
		assertEquals(6, dateAndHourManagementState.getDateAndTimeDay());
		assertEquals(2, dateAndHourManagementState.getDateAndTimeMonth());
		
		
		// Décrémentation des minutes - 24 jours cela donne Le 13 janvier
		for (int i = 0; i < 24; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.downClick();});
		}	
		assertEquals(13, dateAndHourManagementState.getDateAndTimeDay());
		assertEquals(1, dateAndHourManagementState.getDateAndTimeMonth());
	}
	
	@Test
	public void MonthToYearIncrementTest() {
		
		dateAndHourManagementState.setSelectedDateAndTimeProperty(DateAndTimeMenu.Month);
		dateAndHourManagementState.setDateAndTimeMonth(Constant.minMonthInAYear);
		dateAndHourManagementState.setDateAndTimeYear(2000);
		
		// Incrementation des minutes + 25 mois à l'année 2020 cela donnera janvier 2003
		for (int i = 0; i < 25; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.upClick();});
		}	
		assertEquals(2, dateAndHourManagementState.getDateAndTimeMonth());
		assertEquals(2002, dateAndHourManagementState.getDateAndTimeYear());
		
		
		// Décrémentation des minutes - 15 mois cela donnere 
		for (int i = 0; i < 15; i++) {
			Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.downClick();});
		}	
		assertEquals(11, dateAndHourManagementState.getDateAndTimeMonth());
		assertEquals(2000, dateAndHourManagementState.getDateAndTimeYear());
	}
	
	
	@Test
	public void alarmClickTest() {
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.alarmClick();});
		assertTrue(alarmManager.getIsEnabled());
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.alarmClick();});
		assertFalse(alarmManager.getIsEnabled());
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.alarmClick();});
		assertTrue(alarmManager.getIsEnabled());
	}
	
	@Test
	public void auxOutClickTest() {
		AudioOutManager audioOutManager = new AudioOutManager(radio);
		radio.setAudiOutManager(audioOutManager);
		dateAndHourManagementState.setRadio(radio);
		
		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.auxOutClick();});
		assertTrue(audioOutManager.isEnabled());
		

		Assertions.assertThrows(NullPointerException.class, () -> {dateAndHourManagementState.auxOutClick();});
		assertFalse(audioOutManager.isEnabled());
	}
}
