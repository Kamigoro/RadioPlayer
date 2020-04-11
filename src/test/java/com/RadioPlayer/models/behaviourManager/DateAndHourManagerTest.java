package com.RadioPlayer.models.behaviourManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.*;
import org.junit.jupiter.api.Test;
import com.RadioPlayer.models.*;


public class DateAndHourManagerTest {

	
	
	@Test
	public void getCurrentDayTest() {
		DateAndHourManager dhManager = new DateAndHourManager();
		Calendar rightNow = Calendar.getInstance();
		dhManager.adjustToSystemTime();
		assertEquals(dhManager.getCurrentDay(), rightNow.get(Calendar.DAY_OF_MONTH));
	}
	
	@Test
	public void getCurrentHourTest() {
		DateAndHourManager dhManager = new DateAndHourManager();
		Calendar rightNow = Calendar.getInstance();
		dhManager.adjustToSystemTime();
		assertEquals(dhManager.getCurrentHour(), rightNow.get(Calendar.HOUR_OF_DAY));
	}
	
	@Test
	public void getCurrentMinuteTest() {
		DateAndHourManager dhManager = new DateAndHourManager();
		Calendar rightNow = Calendar.getInstance();
		dhManager.adjustToSystemTime();
		assertEquals(dhManager.getCurrentMinute(), rightNow.get(Calendar.MINUTE));
	}
	
	
	@Test
	public void getCurrentSecondTest() {
		DateAndHourManager dhManager = new DateAndHourManager();
		Calendar rightNow = Calendar.getInstance();
		dhManager.adjustToSystemTime();
		assertEquals(dhManager.getCurrentSecond(), rightNow.get(Calendar.SECOND));
	}
	
}
