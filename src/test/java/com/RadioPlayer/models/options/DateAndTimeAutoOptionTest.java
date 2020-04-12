package com.RadioPlayer.models.options;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.RadioPlayer.models.*;
import com.RadioPlayer.models.behaviourManager.DateAndHourManager;
import com.RadioPlayer.models.enums.MonthInYear;

public class DateAndTimeAutoOptionTest {

	DateAndTimeAutoOption dtAutoOption;
	RadioPlayer radio;
	DateAndHourManager dhManager;
	
	@BeforeEach
	public void dateAndTimeAutoOptionInit() {
		dtAutoOption = new DateAndTimeAutoOption();
		dhManager = new DateAndHourManager();
		radio = new RadioPlayer();
		dtAutoOption.setRadioPlayer(radio);
		radio.setDateAndHourManager(dhManager);
	}
	
	@Test
	public void activateDesactivateDateAndTimeAuto() {
		
		dtAutoOption.activate();
		
		assertTrue(dhManager.isInAutoMode());
		
		// Si on essaye de forcer la date et l'heure cela ne fonctionnera pas 
		dhManager.setAllDateAndTimeProperties(0, 0, 1, MonthInYear.JANUARY.ordinal(), 1970);
	
		Calendar rightNow = Calendar.getInstance();
		
		assertEquals(rightNow.get(Calendar.MINUTE), dhManager.getCurrentMinute());
		assertEquals(rightNow.get(Calendar.HOUR_OF_DAY), dhManager.getCurrentHour());
		assertEquals(rightNow.get(Calendar.DAY_OF_MONTH), dhManager.getCurrentDay());
		assertEquals(rightNow.get(Calendar.MONTH), dhManager.getCurrentMonth());
		assertEquals(rightNow.get(Calendar.YEAR), dhManager.getCurrentYear());
		
		dtAutoOption.desactivate();
		
		assertFalse(dhManager.isInAutoMode());
	}
}
