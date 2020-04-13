package com.RadioPlayer.models;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.RadioPlayer.models.failures.AudioOutFailure;
import com.RadioPlayer.models.failures.BreakingNewsFailure;
import com.RadioPlayer.models.failures.DateAndTimeFailure;
import com.RadioPlayer.models.failures.PlayerFailure;
import com.RadioPlayer.models.failures.ScreenFailure;
import com.RadioPlayer.models.failures.VolumeFailure;

public class FailureManagerTest {

	private FailureManager failureManager;
	private RadioPlayer radio;
	
	@BeforeEach
	public void failureManagerInit() {
		radio = new RadioPlayer();
		failureManager = new FailureManager();
		failureManager.setRadio(radio);
	}
	
	@Test
	public void setVolumeFailureTest() {
		failureManager.setVolumeFailure(new VolumeFailure(radio));
		assertNotNull(failureManager.getVolumeFailure());
	}
	
	@Test
	public void setAudioOutFailureTest() {
		failureManager.setAudioOutFailure(new AudioOutFailure(radio));
		assertNotNull(failureManager.getAudioOutFailure());
	}
	
	@Test
	public void setBreakingNewsFailureTest() {
		failureManager.setBreakingNewsFailure(new BreakingNewsFailure(radio));
		assertNotNull(failureManager.getBreakingNewsFailure());
	}
	
	@Test
	public void setDateAndTimeFailureTest() {
		failureManager.setDateAndTimeFailure(new DateAndTimeFailure(radio));
		assertNotNull(failureManager.getDateAndTimeFailure());
	}
	
	@Test
	public void setPlayerFailureTest() {
		failureManager.setPlayerFailure(new PlayerFailure(radio));
		assertNotNull(failureManager.getPlayerFailure());
	}
	
	@Test
	public void setScreenFailure() {
		failureManager.setScreenFailure(new ScreenFailure(radio));
		assertNotNull(failureManager.getScreenFailure());
		Assertions.assertThrows(NullPointerException.class, () -> { failureManager.getScreenFailure().activate();});
		assertFalse(radio.isScreenWorking());
	}
}
