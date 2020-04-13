package com.RadioPlayer.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RadioPlayer.models.behaviourManager.DateAndHourManager;
import com.RadioPlayer.models.constants.Constant;
import com.RadioPlayer.models.players.AUXPlayer;
import com.RadioPlayer.models.players.DABPlayer;
import com.RadioPlayer.models.players.FMPlayer;
import com.RadioPlayer.models.players.USBPlayer;

public class RadioPlayerTest {
	
	private RadioPlayer radio;
	DateAndHourManager dhManager;
	
	@BeforeEach
	public void radioInit() {
		radio = new RadioPlayer();
		radio.instanciateOptions();
		dhManager = new DateAndHourManager();
		radio.setDateAndHourManager(dhManager);
	}
	
	@Test
	public void optionIntanciateTest() {
		for (int i = 0; i < 9; i++) {
			assertNotNull(radio.getOptionArray()[i]);
		}
	}
	
	@Test
	public void manageOptionsTest() {
		
		boolean[] arrayActivateOptions = {true,false,false,false,true,false,false,false,false};
		
		// L'exeption peut se produire lorsque certaines options ont un impact sur l'interface graphique
		Assertions.assertThrows(NullPointerException.class, () -> { radio.manageOptions(arrayActivateOptions); });
		
		assertNotNull(radio.getAlarmManager());
		assertNotNull(radio.getBreakingNewsManager());
		
	}
	
	@Test
	public void dabPlayerTest() throws IOException, FileNotFoundException, NullPointerException {
		radio.setPlayer(new DABPlayer(radio));
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().launchPlayer(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().rightClick(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().rightClick(); });
		radio.saveCurrentMediaAsPreset1();
		assertEquals(radio.getPlayer().getPreset(Constant.indexOfFirstPreset).getName(), radio.getPlayer().getCurrentMedia().getName());
		
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().leftClick(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.loadPreset1(); });
		assertEquals(radio.getPlayer().getPreset(Constant.indexOfFirstPreset).getName(), radio.getPlayer().getCurrentMedia().getName());
		
	}
	
	@Test
	public void auxPlayerTest() throws IOException, FileNotFoundException, NullPointerException {
		radio.setPlayer(new AUXPlayer(radio));
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().launchPlayer(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().rightClick(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().rightClick(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().rightClick(); });
		radio.saveCurrentMediaAsPreset2();
		assertEquals(radio.getPlayer().getPreset(Constant.indexOfSecondPreset).getName(), radio.getPlayer().getCurrentMedia().getName());
		
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().leftClick(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.loadPreset2(); });
		assertEquals(radio.getPlayer().getPreset(Constant.indexOfSecondPreset).getName(), radio.getPlayer().getCurrentMedia().getName());
	}

	@Test
	public void usbPlayerTest() throws IOException, FileNotFoundException, NullPointerException {
		radio.setPlayer(new USBPlayer(radio));
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().launchPlayer(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().rightClick(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().rightClick(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().rightClick(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().rightClick(); });
		radio.saveCurrentMediaAsPreset3();
		assertEquals(radio.getPlayer().getPreset(Constant.indexOfThirdPreset).getName(), radio.getPlayer().getCurrentMedia().getName());
		
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().leftClick(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.getPlayer().leftClick(); });
		Assertions.assertThrows(NullPointerException.class, () -> { radio.loadPreset3(); });
		assertEquals(radio.getPlayer().getPreset(Constant.indexOfThirdPreset).getName(), radio.getPlayer().getCurrentMedia().getName());
	}
}
