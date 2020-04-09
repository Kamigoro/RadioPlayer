package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.FailureManager;
import models.RadioPlayer;

public class FailureController {
	
	@FXML
	private Button btnSaveFailure;
	
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
		/*if(cbxBreakingNewsFailure.isSelected()&& radio.getBreakingNewsManager()!=null) {
			failureManager.getBreakingNewsFailure().activate();
		}*/
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
	
	public void setStage(Stage stage) {
		failureStage = stage;
	}
	
	public FailureManager getFailureManager() {
		return failureManager;
	}

}
