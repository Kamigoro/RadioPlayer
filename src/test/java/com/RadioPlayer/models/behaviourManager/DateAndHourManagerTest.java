package com.RadioPlayer.models.behaviourManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;

import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.RadioPlayer.models.*;
import com.RadioPlayer.models.enums.MonthInYear;

public class DateAndHourManagerTest {

	DateAndHourManager dhManager;

	@BeforeEach
	public void dateAndHourInit() {
		dhManager = new DateAndHourManager();
	}

	@Test
	public void getCurrentYearTest() {
		Calendar rightNow = Calendar.getInstance();
		dhManager.adjustToSystemTime();
		assertEquals(dhManager.getCurrentYear(), rightNow.get(Calendar.YEAR));
	}

	@Test
	public void getCurrentMonthTest() {
		Calendar rightNow = Calendar.getInstance();
		dhManager.adjustToSystemTime();
		assertEquals(dhManager.getCurrentMonth(), rightNow.get(Calendar.MONTH));
	}

	@Test
	public void getCurrentDayTest() {
		Calendar rightNow = Calendar.getInstance();
		dhManager.adjustToSystemTime();
		assertEquals(dhManager.getCurrentDay(), rightNow.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void getCurrentHourTest() {
		Calendar rightNow = Calendar.getInstance();
		dhManager.adjustToSystemTime();
		assertEquals(dhManager.getCurrentHour(), rightNow.get(Calendar.HOUR_OF_DAY));
	}

	@Test
	public void getCurrentMinuteTest() {
		Calendar rightNow = Calendar.getInstance();
		dhManager.adjustToSystemTime();
		assertEquals(dhManager.getCurrentMinute(), rightNow.get(Calendar.MINUTE));
	}

	@Test
	public void getCurrentSecondTest() {
		Calendar rightNow = Calendar.getInstance();
		dhManager.adjustToSystemTime();
		assertEquals(dhManager.getCurrentSecond(), rightNow.get(Calendar.SECOND));
	}

	@Test
	public void autoModeSwitchTest() {
		dhManager.setIsInAutoMode(true);
		assertTrue(dhManager.isInAutoMode());

		dhManager.setIsInAutoMode(false);
		assertFalse(dhManager.isInAutoMode());
	}

	@Test
	public void setAllPropertiesTest() {
		// Lors du set de toutes les propriétés le premier mois de l'année n'est pas le
		// 1 mais le zéro
		dhManager.setAllDateAndTimeProperties(0, 0, 1, 0, 1970);

		// Test de la réception de toutes les valeurs
		int[] dateAndTimePrperties = dhManager.getAllDateAndTimeProperties();

		assertEquals(0, dateAndTimePrperties[0]);
		assertEquals(0, dateAndTimePrperties[1]);
		assertEquals(1, dateAndTimePrperties[2]);
		// Le + 1 permet de récupérer les informations dans un format où Javnvier est le
		// mois 1 et non le zéro
		assertEquals(1, dateAndTimePrperties[3]);
		assertEquals(1970, dateAndTimePrperties[4]);
	}

	@Test
	public void timeSecondToMinuteIncrementationTest() {
		// Vérification de l'incrémentation des minutes et secondes
		dhManager.setCurrentSecond(0);
		dhManager.setCurrentMinute(0);

		// Incrémentation de 500 secondes le résultat devrait donc être 8 minutes et 20
		// secondes
		for (int i = 0; i < 500; i++) {
			dhManager.secondIncrement();
		}

		assertEquals(20, dhManager.getCurrentSecond());
		assertEquals(8, dhManager.getCurrentMinute());

	}

	@Test
	public void timeSecondToHourIncrementationTest() {
		// Vérification de l'incrémentation des heures minutes et secondes à partir de
		// 00:00:00
		dhManager.setCurrentSecond(0);
		dhManager.setCurrentMinute(0);
		dhManager.setCurrentHour(0);

		// Incrémentation de 3700 secondes le résultat devrait donc être 01:01:40
		for (int i = 0; i < 3700; i++) {
			dhManager.secondIncrement();
		}

		assertEquals(40, dhManager.getCurrentSecond());
		assertEquals(1, dhManager.getCurrentMinute());
		assertEquals(1, dhManager.getCurrentHour());

	}

	@Test
	public void setDatePropertiesTest() {
		dhManager.setCurrentDay(15);
		dhManager.setCurrentMonth(MonthInYear.MARCH.ordinal());
		dhManager.setCurrentYear(2020);
		
		assertEquals(15, dhManager.getCurrentDay());
		assertEquals(MonthInYear.MARCH.ordinal(), dhManager.getCurrentMonth());
		assertEquals(2020, dhManager.getCurrentYear());
		
	}

	@Test
	public void monthInAYearEnumTest() {
		for (int i = 0; i < 12; i++) {
			dhManager.setCurrentMonth(MonthInYear.JANUARY.ordinal() + i);
			// Le moins 1 est présent car une énumération commence à 0 mais les mois de l'année commencent avec janvier à 1 
			assertEquals( (MonthInYear.JANUARY.ordinal() + i),dhManager.getCurrentMonth());
		}
	}
	
	@Test
	public void howManyDaysInAMonthTest() {
		// Combien de jour retourne la méthode pour les mois de  Mai, Avril, Juillet, Octobre pour l'année 2020
		
		dhManager.setCurrentMonth(MonthInYear.MAY.ordinal());
		dhManager.setCurrentYear(2020);
		assertEquals(31, dhManager.howManyDaysInAMonth());
		dhManager.setCurrentMonth(MonthInYear.APRIL.ordinal());
		dhManager.setCurrentYear(2020);
		assertEquals(30, dhManager.howManyDaysInAMonth());
		dhManager.setCurrentMonth(MonthInYear.JULY.ordinal());
		dhManager.setCurrentYear(2020);
		assertEquals(31, dhManager.howManyDaysInAMonth());
		dhManager.setCurrentMonth(MonthInYear.OCTOBER.ordinal());
		dhManager.setCurrentYear(2020);
		assertEquals(31, dhManager.howManyDaysInAMonth());
	}
	
	@Test
	public void howManyDaysInALeapYearTest() {
		// Test de la méthode sur les années bissextiles
		
		dhManager.setCurrentMonth(MonthInYear.FEBRUARY.ordinal());
		dhManager.setCurrentYear(2020);
		assertEquals(29, dhManager.howManyDaysInAMonth());

		dhManager.setCurrentMonth(MonthInYear.FEBRUARY.ordinal());
		dhManager.setCurrentYear(2019);
		assertEquals(28, dhManager.howManyDaysInAMonth());
	}
	
}
