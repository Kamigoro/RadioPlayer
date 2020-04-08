package models;

import controllers.RadioController;
import models.behaviourManager.*;
import models.players.*;
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
import models.options.*;

public class RadioPlayer {

	
	private RadioController radioController;
	private Configurator configurator;
	
	private IRadioState currentState;
	private IPlayer player;
	private AlarmManager alarmManager;
	private AutotuneManager autotuneManager;
	private BreakingNewsManager breakingNewsManager;
	private DateAndHourManager dateAndHourManager;
	
	private AUXPlayer auxPlayer;
	private DABPlayer dabPlayer;
	private FMPlayer fmPlayer;
	private USBPlayer usbPlayer;
	
	private IOption[] optionsArray = new IOption[9];
	
	//////////////////////////////////////
	//			Constructeur            //
	//////////////////////////////////////
	
	public RadioPlayer(Configurator configurator) {
		System.out.println("MODELS : Création d'une nouvelle radio");
		instanciateOptions();
		this.configurator = configurator;
		player = new DABPlayer(this);
		setCurrentState(new IdleState(this));
		dateAndHourManager = new DateAndHourManager(this);
	}

	/////////////////////////////////////
	//    Fonctions normales           //
	/////////////////////////////////////
	
	/**
	 * Crée tous les objets correspondant à chaque options.
	 */
	public void instanciateOptions() {
		optionsArray[0] = new AlarmManagementOption(this);
		optionsArray[1] = new AudioOutOption(this);
		optionsArray[2] = new AutotuneOption(this);
		optionsArray[3] = new AUXInSupport(this);
		optionsArray[4] = new BreakingNewsOption(this);
		optionsArray[5] = new DateAndTimeAutoOption(this);
		optionsArray[6] = new FMSupport(this);
		optionsArray[7] = new SecondarySpeakerOption(this);
		optionsArray[8] = new USBSupport(this);
	}
	
	/**
	 * Active les options en fonctions du tableau de booléen renvoyé par le configurationController.
	 * @param optionsActivated
	 */
	public void manageOptions(boolean[] optionsActivated) {
		for(int i = 0; i<optionsActivated.length;i++) {
			if(optionsActivated[i]) {
				optionsArray[i].activate();
			}else {
				optionsArray[i].desactivate();
			}
		}
	}
	
	/////////////////////////////////////
	//    Délégation graphique         //
	/////////////////////////////////////
	
	/**
	 * Permet de demander au controller l'ouverture du menu
	 */
	public void openInitialScreen() {
		this.radioController.initialScreen();
	}
	
	public void openConfigurationScreen() {
		this.configurator.showConfiguratorScreen();
	}
	
	public void editRadioConfiguration() {
		this.configurator.showConfiguratorScreen();
	}
	

    /*-----------------------------------------------------------------------
     *---- 	  METHODES CONCERNANT LES MODIFICATIONS DU MENU   			 ----
     *-----------------------------------------------------------------------
     */
	
	/**
	 * Permet de demander au controller l'ouverture du menu du signal d'entrée
	 */
	public void openMenuScreen() {
		this.radioController.menuScreen();
	}
	
	/**
	 * Permet de demander au controller de changer la propriété séletionnée
	 * @param currentMenu
	 */
	public void changeSelectedMenu(MainMenu currentMenu) {
		this.radioController.changeFocusMainMenu(currentMenu);
	}
	
    /*-----------------------------------------------------------------------
     *---- 	  METHODES CONCERNANT LES MODIFICATIONS DU MENU              ----
     *-----------------------------------------------------------------------
     */
    
    /*-----------------------------------------------------------------------
     *---- 	  METHODES CONCERNANT LES MODIFICATIONS DU SIGNAL D'ENTREE   ----
     *-----------------------------------------------------------------------
     */
	
	/**
	 * Permet de demander au controller l'ouverture du menu du signal d'entrée
	 */
	public void openInputSignalMenu() {
		this.radioController.inputSignalMenu();
	}
	
	/**
	 * Permet de demander au controller de changer la propriété séletionnée
	 * @param currentInputSignal
	 */
	public void changeSelectedMenuInputSignal(InputSignalMenu currentInputSignal) {
		this.radioController.changeFocusInputSignalMenu(currentInputSignal);
	}
	
    /*------------------------------------------------------------------------
     *---- FIN METHODES CONCERNANT LES MODIFICATIONS DU SIGNAL D'ENTREE   ----
     *------------------------------------------------------------------------
     */
    
    /*-----------------------------------------------------------------------
     *---- 	  METHODES CONCERNANT LES MODIFICATIONS DE LA DATE ET HEURE  ----
     *-----------------------------------------------------------------------
     */
	
	/**
	 * Permet de demander au controller l'ouverture du menu de date et heure 
	 */
	public void openDateAndTimeMenu() {
		this.radioController.dateAndTimeMenu();
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
	
    /*-----------------------------------------------------------------------
     *--- FIN  METHODES CONCERNANT LES MODIFICATIONS DE LA DATE ET HEURE  ---
     *-----------------------------------------------------------------------
     */
       
    /*---------------------------------------------------------------
     *---- 	  METHODES CONCERNANT LES MODIFICATIONS DE L'ALARME  ----
     *---------------------------------------------------------------
     */
	
	/**
	 * Permet de demander au controller l'ouverture du menu alarme
	 */
	public void openAlarmMenu() {
		this.radioController.alarmMenu();
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
	
    /*---------------------------------------------------------------
     *--- FIN  METHODES CONCERNANT LES MODIFICATIONS DE L'ALARME  ---
     *---------------------------------------------------------------
     */
	
	public void editPlayerInformations(String PlayerName,String name, String imgSongPath, String imgMediaPath) {
		this.editPlayerInformations(PlayerName, name, imgSongPath, imgMediaPath);
	}
	
    
    /**
     * Méthode permettant de modifier les messages de l'écran principal de la radio
     * @param displayType énumération permettant de savoir quel label modifier dans l'écran principal
     * @param message string contenant le message à afficher
     */
	public void displayMessageOnMainScreen(DisplayType displayType, String message) {
		this.radioController.displayMessageOnMainScreen(displayType ,message);
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
		if(alarmManager == null) {
			System.out.println("BeaviourManager : La radio n'a plus de gestionnaire d'alarme");
		}
		this.alarmManager = alarmManager;
	}

	public AutotuneManager getAutotuneManager() {
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

	public DateAndHourManager getDateAndHourManager() {
		return dateAndHourManager;
	}
	
	public void setDateAndHourManager(DateAndHourManager dateAndHourManager) {
		this.dateAndHourManager = dateAndHourManager;
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

	
}
