package models;

import controllers.RadioController;
import models.behaviourManager.*;
import models.players.IPlayer;
import states.IRadioState;
import states.IdleState;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.enums.AlarmMenu;
import models.enums.DateAndTimeMenu;
import models.enums.DisplayType;
import models.enums.InputSignalMenu;
import models.enums.MainMenu;

public class RadioPlayer {

	private IRadioState currentState;
	private RadioController radioController;
	private Configurator configurator;
	private IPlayer player;
	
	//C'est ici que vont se trouver les plugin que peut avoir notre radio
	private AlarmManager alarmManager;
	private AutotuneManager autotuneManager;
	private BreakingNewsManager breakingNewsManager;
	private DateAndHourManager dateAndHourManager;
	
	//Constructeur
	public RadioPlayer(Configurator configurator) {
		System.out.println("MODELS : Création d'une nouvelle radio");
		
		this.configurator = configurator;
		setCurrentState(new IdleState(this));
		
		//Création d'un gestionnaire des heures associés à la radio
		dateAndHourManager = new DateAndHourManager(this);
	}

	
	public void openConfigurationScreen() {
		this.configurator.showConfiguratorScreen();
	}
	
	public void openRadioPlayerScreen() {
		try{
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/RadioPanel.fxml"));
			Parent radioParent = (Parent) fxmlLoader.load();
			radioController = fxmlLoader.<RadioController>getController();
			radioController.setRadioPlayer(this);
			radioController.initialScreen();
			Stage radioStage = new Stage();
			radioStage.setTitle("Simulateur de radio");
			radioStage.setScene(new Scene(radioParent));
			radioStage.show();
		
		} catch (IOException e) {
			System.out.println("Impossible de charger la page RadioPanel.fxml");
			System.out.println(e);
		}
	}
	
	/////////////////////////////////////
	//    Délégation graphique         //
	/////////////////////////////////////
	
	public void openInitialScreen() {
		this.radioController.initialScreen();
	}
	
	public void openMenuScreen() {
		this.radioController.menuScreen();
	}
	
	public void changeSelectedMenu(MainMenu currentMenu) {
		this.radioController.changeFocusMainMenu(currentMenu);
	}
	
	public void openInputSignalMenu() {
		this.radioController.inputSignalMenu();
	}
	
	public void changeSelectedMenuInputSignal(InputSignalMenu currentInputSignal) {
		this.radioController.changeFocusInputSignalMenu(currentInputSignal);
	}
	
	public void openDateAndTimeMenu() {
		this.radioController.dateAndTimeMenu();
	}
	
	public void changeSelectedMenuDateAndTime(DateAndTimeMenu currentDateAndTime) {
		this.radioController.changeFocusDateAndTimeMenu(currentDateAndTime);
	}
	
	public void openAlarmMenu() {
		this.radioController.alarmMenu();
	}
	
	public void changeSelectedMenuAlarm(AlarmMenu currentAlarm) {
		this.radioController.changeFocusAlarmMenu(currentAlarm);
	}
	public void editAlarmProperties(AlarmMenu currentAlarm, int alarmValue) {
		this.radioController.editAlarmLabel(currentAlarm, alarmValue);
	}
	
	public void changeAlarmStatus(boolean status) {
		this.radioController.changeAlarmStatus(status);
	}
	
	public void displayMessageOnMainScreen(DisplayType displayType, String message) {
		this.radioController.displayMessageOnMainScreen(displayType ,message);
	}
	
	/////////////////////////////////////
	//			Getters et setters	   //
	/////////////////////////////////////
	
	public IRadioState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(IRadioState currentState) {
		this.currentState = currentState;
	}
	
	public AlarmManager getAlarmManager() {
		return alarmManager;
	}

	public void setAlarmManager(AlarmManager alarmManager) {
		if(alarmManager == null) {
			System.out.println("BeaviourManager : La radio n'a plus de gestionnaire d'alarme");
		}
		this.alarmManager = alarmManager;
	}

	
	
	
	
	
	public AutotuneManager getAutotune() {
		return autotuneManager;
	}

	public void setAutotuneManager(AutotuneManager autotuneManager) {
		if(autotuneManager == null) {
			System.out.println("BeaviourManager : La radio n'a plus de gestionnaire d'autotune");
		}
		this.autotuneManager = autotuneManager;
	}
	
	
	
	
	
	
	public BreakingNewsManager getBreakingNewsManager() {
		return breakingNewsManager;
	}

	public void setBreakingNewsManager(BreakingNewsManager breakingNewsManager) {
		if(breakingNewsManager == null) {
			System.out.println("BeaviourManager : La radio n'a plus de gestionnaire de Breaking News");
		}
		this.breakingNewsManager = breakingNewsManager;
	}


	
	
	
	
	
	public IPlayer getPlayer() {
		return player;
	}


	public void setPlayer(IPlayer player) {
		this.player = player;
	}
	
}
