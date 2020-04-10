package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;
import models.FailureManager;
import models.RadioPlayer;

public class FailureController {
	
	@FXML
	private Button btnSaveFailure;
	
	@FXML
	private CheckBox chbxAudioOutFailure, chbxPlayerFailure, chbxBreakingNewsFailure, chbxScreenFailure, chbxDateAndTimeFailure, chbxVolumeFailure;
	
	private RadioPlayer radio;
	private FailureManager failureManager;
	private Stage failureStage;
	
	public FailureController() {
		
	}
	
	public void setRadio(RadioPlayer radio) {
		this.radio = radio;
		failureManager = new FailureManager(radio);
	}
	
	@FXML
	private void btnSaveFailureClick() {
		failureStage.hide();
	}
	
	@FXML
	private void showScreen() {
		failureStage.show();
	}
	
	@FXML
	private void hideScreen() {
		failureStage.hide();
	}
	
	@FXML 
	public void chbxAudioOutFailureCheck(ActionEvent event) {
		if(chbxAudioOutFailure.isSelected()) {
			if(radio.getAudioOutManager() != null) {
				chbxAudioOutFailure.setSelected(true);
				failureManager.getAudioOutFailure().activate();
			} else {
				chbxAudioOutFailure.setSelected(false);
				failureManager.getAudioOutFailure().desactivate();
			}
		}
	}
	
	@FXML
	public void chbxPlayerFailureCheck(ActionEvent event) {
		if(chbxPlayerFailure.isSelected()) {
				chbxPlayerFailure.setSelected(true);
				failureManager.getPlayerFailure().activate();
			} else {
				chbxPlayerFailure.setSelected(false);
				failureManager.getPlayerFailure().desactivate();
			}
	} 
	
	@FXML
	public void chbxScreenFailureCheck(ActionEvent event) {
		if(chbxScreenFailure.isSelected()) {
				chbxScreenFailure.setSelected(true);
				failureManager.getScreenFailure().activate();
			} else {
				chbxScreenFailure.setSelected(false);
				failureManager.getScreenFailure().desactivate();
			}
	} 
	
	@FXML
	public void chbxBreakingNewsFailureCheck(ActionEvent event) {
		if(chbxBreakingNewsFailure.isSelected()) {
			if(radio.getBreakingNewsManager() != null) {
				chbxBreakingNewsFailure.setSelected(true);
				failureManager.getBreakingNewsFailure().activate();
			} else {
				chbxBreakingNewsFailure.setSelected(false);
				failureManager.getBreakingNewsFailure().desactivate();
			}
		}
	} 
	
	@FXML
	public void chbxDateAndTimeFailureCheck(ActionEvent event) {
		if(chbxDateAndTimeFailure.isSelected()) {
				chbxDateAndTimeFailure.setSelected(true);
				failureManager.getDateAndTimeFailure().activate();
			} else {
				chbxDateAndTimeFailure.setSelected(false);
				failureManager.getDateAndTimeFailure().desactivate();
			}
	}  
	
	@FXML
	public void chbxVolumeFailureCheck(ActionEvent event) {
		if(chbxVolumeFailure.isSelected()) {
				chbxVolumeFailure.setSelected(true);
				failureManager.getVolumeFailure().activate();
			} else {
				chbxVolumeFailure.setSelected(false);
				failureManager.getVolumeFailure().desactivate();
			}
	} 
	
	public void setStage(Stage stage) {
		failureStage = stage;
	}
	
	public FailureManager getFailureManager() {
		return failureManager;
	}

}
