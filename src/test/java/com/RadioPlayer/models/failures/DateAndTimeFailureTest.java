package com.RadioPlayer.models.failures;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RadioPlayer.models.RadioPlayer;
import com.RadioPlayer.models.behaviourManager.DateAndHourManager;
import com.RadioPlayer.models.enums.MonthInYear;

public class DateAndTimeFailureTest {

	private DateAndTimeFailure dateAndTimeFailure;
	private RadioPlayer radio;
	private DateAndHourManager dhManager;
	
	@BeforeEach
	public void dateAndTimeFailureInit() {
		dhManager = new DateAndHourManager();
		radio = new RadioPlayer();
		dateAndTimeFailure = new DateAndTimeFailure(radio);
		radio.setDateAndHourManager(dhManager);
	}

	@Test
	public void activateDesactivateDateAndTimeFailureTest() {
		
		// lors de l'activation la date et l'heure se fixera au 1/1/1970 à 00:00
		dateAndTimeFailure.activate();

		assertEquals(0, dhManager.getCurrentMinute());
		assertEquals(0, dhManager.getCurrentHour());
		assertEquals(1, dhManager.getCurrentDay());
		// Le moins 1 est pour retomber dans un système où janvier est le premier mois de l'année et non le 0
		assertEquals(MonthInYear.JANUARY.ordinal(), dhManager.getCurrentMonth() - 1);
		assertEquals(1970, dhManager.getCurrentYear());

		// Lors de la désactivation de la panne on remet la radio à l'heure système
		dateAndTimeFailure.desactivate();
		
		Calendar rightNow = Calendar.getInstance();
		
		assertEquals(rightNow.get(Calendar.MINUTE), dhManager.getCurrentMinute());
		assertEquals(rightNow.get(Calendar.HOUR_OF_DAY), dhManager.getCurrentHour());
		assertEquals(rightNow.get(Calendar.DAY_OF_MONTH), dhManager.getCurrentDay());
		assertEquals(rightNow.get(Calendar.MONTH), dhManager.getCurrentMonth());
		assertEquals(rightNow.get(Calendar.YEAR), dhManager.getCurrentYear());
	}
}
