package com.RadioPlayer.models;

import java.io.IOException;

import com.RadioPlayer.controllers.ConfigurationController;
import com.RadioPlayer.controllers.FailureController;
import com.RadioPlayer.controllers.RadioController;
import com.RadioPlayer.models.behaviourManager.AlarmManager;
import com.RadioPlayer.models.behaviourManager.AudioOutManager;
import com.RadioPlayer.models.behaviourManager.AutotuneManager;
import com.RadioPlayer.models.behaviourManager.BreakingNewsManager;
import com.RadioPlayer.models.behaviourManager.DateAndHourManager;
import com.RadioPlayer.models.behaviourManager.VolumeManager;
import com.RadioPlayer.models.constants.Constant;
import com.RadioPlayer.models.enums.AlarmMenu;
import com.RadioPlayer.models.enums.DateAndTimeMenu;
import com.RadioPlayer.models.enums.DisplayType;
import com.RadioPlayer.models.enums.InputSignalMenu;
import com.RadioPlayer.models.enums.MainMenu;
import com.RadioPlayer.models.options.AUXInSupport;
import com.RadioPlayer.models.options.AlarmManagementOption;
import com.RadioPlayer.models.options.AudioOutOption;
import com.RadioPlayer.models.options.AutotuneOption;
import com.RadioPlayer.models.options.BreakingNewsOption;
import com.RadioPlayer.models.options.DateAndTimeAutoOption;
import com.RadioPlayer.models.options.FMSupport;
import com.RadioPlayer.models.options.IOption;
import com.RadioPlayer.models.options.SecondarySpeakerOption;
import com.RadioPlayer.models.options.USBSupport;
import com.RadioPlayer.models.players.AUXPlayer;
import com.RadioPlayer.models.players.DABPlayer;
import com.RadioPlayer.models.players.FMPlayer;
import com.RadioPlayer.models.players.IPlayer;
import com.RadioPlayer.models.players.USBPlayer;
import com.RadioPlayer.states.IRadioState;
import com.RadioPlayer.states.IdleState;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class RadioPlayer {

	
	private RadioController radioController;
	private Configurator configurator;
	private FailureManager failureManager;
	private Stage failureStage;
	
	private IRadioState currentState;
	private IPlayer player;
	private AlarmManager alarmManager;
	private AutotuneManager autotuneManager;
	private BreakingNewsManager breakingNewsManager;
	private DateAndHourManager dateAndHourManager;
	private VolumeManager volumeManager;
	private AudioOutManager audioOutManager;
	
	private AUXPlayer auxPlayer;
	private DABPlayer dabPlayer;
	private FMPlayer fmPlayer;
	private USBPlayer usbPlayer;
	
	private IOption[] optionsArray = new IOption[9];
	private boolean isScreenWorking;
	
	//////////////////////////////////////
	//			Constructeur            //
	//////////////////////////////////////
	
	public RadioPlayer() {
	}
	
	public RadioPlayer(Configurator configurator) {
		isScreenWorking = true;
		instanciateOptions();
		this.configurator = configurator;
		player = new DABPlayer(this);
		dateAndHourManager = new DateAndHourManager(this);
		volumeManager = new VolumeManager(this);
	}

	/////////////////////////////////////
	//    Fonctions normales           //
	/////////////////////////////////////
	
	/**
	 * Crée tous les objets correspondant à chaque options.
	 */
	public void instanciateOptions() {
		optionsArray[0] = new AlarmManagementOption();
		optionsArray[1] = new AudioOutOption();
		optionsArray[2] = new AutotuneOption();
		optionsArray[3] = new AUXInSupport();
		optionsArray[4] = new BreakingNewsOption();
		optionsArray[5] = new DateAndTimeAutoOption();
		optionsArray[6] = new FMSupport();
		optionsArray[7] = new SecondarySpeakerOption();
		optionsArray[8] = new USBSupport();
	}
	
	/**
	 * Active les options en fonctions du tableau de booléen renvoyé par le configurationController.
	 * @param optionsActivated
	 */
	public void manageOptions(boolean[] optionsActivated) {
		for(int i = 0; i<optionsActivated.length;i++) {
			optionsArray[i].setRadioPlayer(this);
			if(optionsActivated[i]) {
				optionsArray[i].activate();
			}else {
				optionsArray[i].desactivate();
			}
		}
	}
	
	/**
	 * Génère l'écran des pannes pour la première fois
	 */
	public void generateFailureScreenForTheFirstTime() {
		try {
	    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/RadioPlayer/views/FailurePanel.fxml"));
	        Parent failureRoot;
			failureRoot = (Parent) loader.load();
	        FailureController failureController = loader.<FailureController>getController();
	        failureController.setRadio(this);
	        failureManager = failureController.getFailureManager();
	        failureStage = new Stage();
	        failureController.setStage(failureStage);
	        Platform.setImplicitExit(false);
	        Scene scene = new Scene(failureRoot);
	        failureStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent e) {
			    	Platform.exit();
			    	System.exit(0);
			    }
			  });
	        failureStage.setTitle("Ecran de gestion des pannes");
	        failureStage.setScene(scene);
	        failureStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	public void showFailureScreen() {
		failureStage.show();
	}
	
	public void saveCurrentMediaAsPreset1() {
		player.setPreset(Constant.indexOfFirstPreset);
	}
	
	public void saveCurrentMediaAsPreset2() {
		player.setPreset(Constant.indexOfSecondPreset);
	}
	
	public void saveCurrentMediaAsPreset3() {
		player.setPreset(Constant.indexOfThirdPreset);
	}
	
	public void loadPreset1() {
		if(player.getPreset(Constant.indexOfFirstPreset).getIndex()!=Constant.noMediaBehindPreset) {
			player.setCurrentMediaIndex(player.getPreset(Constant.indexOfFirstPreset).getIndex());
		}
	}
	
	public void loadPreset2() {
		if(player.getPreset(Constant.indexOfSecondPreset).getIndex()!=Constant.noMediaBehindPreset) {
			player.setCurrentMediaIndex(player.getPreset(Constant.indexOfSecondPreset).getIndex());
		}
	}
	
	public void loadPreset3() {
		if(player.getPreset(Constant.indexOfThirdPreset).getIndex()!=Constant.noMediaBehindPreset) {
			player.setCurrentMediaIndex(player.getPreset(Constant.indexOfThirdPreset).getIndex());
		}
	}
	
	/////////////////////////////////////
	//    Délégation graphique         //
	/////////////////////////////////////
	
	public void openInitialScreen() {
		if (isScreenWorking) {
			this.radioController.initialScreen();
		}
	}
	
	public void openConfigurationScreen() {
		this.configurator.showConfiguratorScreen();
	}
	
	public void editRadioConfiguration() {
		this.configurator.showConfiguratorScreen();
	}

	public void openMenuScreen() {
		if(isScreenWorking) {
			this.radioController.menuScreen();
		}
	}
	
	/**
	 * Permet de demander au controller de changer la propriété séletionnée
	 * @param currentMenu
	 */
	public void changeSelectedMenu(MainMenu currentMenu) {
		this.radioController.changeFocusMainMenu(currentMenu);
	}

	public void openInputSignalMenu() {
		if (isScreenWorking) {
			this.radioController.inputSignalMenu();
		}
	}
	
	/**
	 * Permet de demander au controller de changer la propriété séletionnée
	 * @param currentInputSignal
	 */
	public void changeSelectedMenuInputSignal(InputSignalMenu currentInputSignal) {
		this.radioController.changeFocusInputSignalMenu(currentInputSignal);
	}

	public void openDateAndTimeMenu() {
		if (isScreenWorking) {
			this.radioController.dateAndTimeMenu();
		}
	}
	
	/**
	 * Permet de demander au controller de changer la propriété séletionnée
	 * @param currentDateAndTime
	 */
	public void changeSelectedMenuDateAndTime(DateAndTimeMenu currentDateAndTime) {
		this.radioController.changeFocusDateAndTimeMenu(currentDateAndTime);
	}
	
	/**
	 * Permet de demander au controller de modifier la valeur des propriétés de la date et heure
	 * @param dateAndTimeMenu propriété sélectionnée
	 * @param currentDateAndTimeValue valeur de la propriété
	 */
	public void editDateAndTimeProperty(DateAndTimeMenu dateAndTimeMenu, int currentDateAndTimeValue) {
		this.radioController.editDateAndTimeLabel(dateAndTimeMenu, currentDateAndTimeValue);
	}
	
	/**
	 * Permet de modifier toutes les valeurs des propriétés de la date et l'heure en une fois
	 * @param dateAndTimeProperties
	 */
	public void editAllDateAndTimeProperties(int[] dateAndTimeProperties) {
		this.radioController.editAllDateAndTimeProperties(dateAndTimeProperties);
	}
	
	public void openAlarmMenu() {
		if (isScreenWorking) {
			this.radioController.alarmMenu();
		}
	}
	
	/**
	 * Permet de demander au controller de changer la propriété séletionnée
	 * @param currentAlarm
	 */
	public void changeSelectedMenuAlarm(AlarmMenu currentAlarm) {
		this.radioController.changeFocusAlarmMenu(currentAlarm);
	}
	
	/**
	 * Permet de demander au controller de modifier la valeur des propriétés de l'alarme
	 * @param currentAlarm propriété sélectionnée
	 * @param alarmValue valeur de la propriété
	 */
	public void editAlarmProperties(AlarmMenu currentAlarm, int alarmValue) {
		this.radioController.editAlarmLabel(currentAlarm, alarmValue);
	}
	
	/**
	 * Permet de demander au controller le changement du status de l'alarme
	 * @param status
	 */
	public void changeAlarmStatus(boolean status) {
		this.radioController.changeAlarmStatus(status);
	}
	
	public void editPlayerInformations(Media currentMedia) {
		radioController.editPlayerInformations(currentMedia.getArtistOrFrequency(),
				currentMedia.getName(),
				currentMedia.getSongImagePath(), 
				currentMedia.getMediaLogo());
	}
	
    /**
     * Méthode permettant de modifier les messages de l'écran principal de la radio
     * @param displayType énumération permettant de savoir quel label modifier dans l'écran principal
     * @param message string contenant le message à afficher
     */
	public void displayMessageOnMainScreen(DisplayType displayType, String message) {
		this.radioController.displayMessageOnMainScreen(displayType ,message);
	}
	
	public void changeVolume(String volume) {
		this.radioController.changeVolume(volume);
	}

	public void enableSecondarySpeaker() {
		this.radioController.showSecondarySpeaker();
	}
	
	public void disableSecondarySpeaker() {
		this.radioController.hideSecondarySpeaker();
	}
	
	public void changeAuxOutStatus(boolean isEnabled) {
		this.radioController.changeAuxOutStatus(isEnabled);
	}
	
	public void hideAuxOut() {
		this.radioController.hideAuxOut();
	}
	
	public void showAuxOut() {
		this.radioController.showAuxOut();
	}
	
	public void openOffScreen() {
		this.radioController.openOffScreen();
	}
	
	public void openBrokenScreen() {
		this.radioController.openBrokenScreen();
	}

	/////////////////////////////////////
	//			Getters et setters	   //
	/////////////////////////////////////
	
	public RadioController getRadioController() {
		return this.radioController;
	}
	
	public void setRadioController(RadioController radioController) {
		this.radioController = radioController;
	}
	
	public Configurator getConfigurator() {
		return this.configurator;
	}
	
	public void setConfigurator(Configurator configurator) {
		this.configurator = configurator;
	}
	
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
		this.alarmManager = alarmManager;
	}

	public AutotuneManager getAutotuneManager() {
		return autotuneManager;
	}

	public void setAutotuneManager(AutotuneManager autotuneManager) {
		this.autotuneManager = autotuneManager;
	}

	public BreakingNewsManager getBreakingNewsManager() {
		return breakingNewsManager;
	}

	public void setBreakingNewsManager(BreakingNewsManager breakingNewsManager) {
		this.breakingNewsManager = breakingNewsManager;
	}

	public DateAndHourManager getDateAndHourManager() {
		return dateAndHourManager;
	}
	
	public void setDateAndHourManager(DateAndHourManager dateAndHourManager) {
		this.dateAndHourManager = dateAndHourManager;
	}
	
	public void setVolumeManager(VolumeManager volumeManager) {
		this.volumeManager = volumeManager;
	}
	
	public IPlayer getPlayer() {
		return player;
	}

	public void setPlayer(IPlayer player) {
		this.player = player;
	}
	
	public AUXPlayer getAuxPlayer() {
		return auxPlayer;
	}

	public void setAuxPlayer(AUXPlayer auxPlayer) {
		this.auxPlayer = auxPlayer;
	}

	public DABPlayer getDabPlayer() {
		return dabPlayer;
	}

	public void setDabPlayer(DABPlayer dabPlayer) {
		this.dabPlayer = dabPlayer;
	}

	public FMPlayer getFmPlayer() {
		return fmPlayer;
	}

	public void setFmPlayer(FMPlayer fmPlayer) {
		this.fmPlayer = fmPlayer;
	}

	public USBPlayer getUsbPlayer() {
		return usbPlayer;
	}

	public void setUsbPlayer(USBPlayer usbPlayer) {
		this.usbPlayer = usbPlayer;
	}

	public VolumeManager getVolumeManager() {
		return this.volumeManager;
	}

	public AudioOutManager getAudioOutManager() {
		return this.audioOutManager;
	}
	
	public void setAudiOutManager(AudioOutManager audioOutManager) {
		this.audioOutManager = audioOutManager;
	}
	
	public FailureManager getFailureManager() {
		return this.failureManager;
	}

	public boolean isScreenWorking() {
		return isScreenWorking;
	}
	
	public void setIsScreenWorking(boolean isScreenWorking) {
		this.isScreenWorking = isScreenWorking;
	}
	
	public IOption[] getOptionArray() {
		return this.optionsArray;
	}

	

	
	
}
