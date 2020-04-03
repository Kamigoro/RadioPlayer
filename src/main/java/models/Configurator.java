package models;

import java.io.IOException;
import java.util.ArrayList;

import controllers.ConfigurationController;
import models.options.*;

public class Configurator {

	//Options possible d'activer
	private AlarmManagementOption alarmOption;
	private AutotuneOption autotuneOption;
	private DateAndTimeAutoOption dateAndTimeAutoOption;
	private AUXInOption auxInOption;
	private DABPlusOption dabPlusOption;
	private BreakingNewsOption breakingNewsOption;
	private FMOption fmOption;
	private USBOption usbOption;
	private ArrayList<IOption> optionsList;
	
	private RadioPlayer radio;
	private ConfigurationController controller;
	
	
	public Configurator(ConfigurationController controller) {
		this.controller = controller;
		radio = new RadioPlayer(this);
		instanciateAllOptions();
	}
	
	private void instanciateAllOptions() {
		
		//Création de toutes les options
		//TODO Passer une radio Ã  toutes les options pour que les options soit juste responsable de l'instanciation d'objet
		alarmOption = new AlarmManagementOption(radio);
		autotuneOption = new AutotuneOption(radio);
		dateAndTimeAutoOption = new DateAndTimeAutoOption(radio);
		auxInOption = new AUXInOption(radio);
		dabPlusOption = new DABPlusOption(radio);
		breakingNewsOption = new BreakingNewsOption(radio);
		fmOption = new FMOption(radio);
		usbOption = new USBOption(radio);
		
		//Les enregistrer dans la liste d'options
		optionsList = new ArrayList<IOption>();
		optionsList.add(alarmOption);
		optionsList.add(autotuneOption);
		optionsList.add(dateAndTimeAutoOption);
		optionsList.add(auxInOption);
		optionsList.add(dabPlusOption);
		optionsList.add(breakingNewsOption);
		optionsList.add(fmOption);
		optionsList.add(usbOption);
		
		//Par défaut elles sont toutes désactivées
		for (IOption option : optionsList) {
			option.desactivate();
		}
	}
	
	/**
	 * Fonction qui va être appelée lors de la génération d'une radio
	 * On va fermer l'écran de configuration et ouvrir l'écran de radio
	 * @throws IOException 
	 */
	public void generateRadioForTheFirstTime() throws IOException {
		hideConfiguratorScreen();
		radio.openRadioPlayerScreen();
	}
	
	/**
	 * Fonction qui va demander au controller de montrer la fenêtre de configuration
	 */
	public void showConfiguratorScreen() {
		this.controller.showScreen();
	}
	
	/**
	 * Fonction qui va demander au controller de fermer la fenêtre de configuration
	 */
	private void hideConfiguratorScreen() {
		this.controller.hideScreen();
	}
	
	///////////////////////////////////////////////////////////
	//	Fonctions d'activations ou désactivations d'options  //
	///////////////////////////////////////////////////////////
	
	public void manageAlarmOption(boolean activated) {
		if(activated) {
			alarmOption.activate();
		}else {
			alarmOption.desactivate();
		}
	}
	
	public void manageAutotuneOption(boolean activated) {
		if(activated) {
			autotuneOption.activate();
		}else {
			autotuneOption.desactivate();
		}
	}
	
	public void manageDateAndHourAutoOption(boolean activated) {
		if(activated) {
			dateAndTimeAutoOption.activate();
		}else {
			dateAndTimeAutoOption.desactivate();
		}
	}
	
	public void manageAuxInOption(boolean activated) {
		if(activated) {
			auxInOption.activate();
		}else {
			auxInOption.desactivate();
		}
	}
	
	public void manageDabPlusOption(boolean activated) {
		if(activated) {
			dabPlusOption.activate();
		}else {
			dabPlusOption.desactivate();
		}
	}
	
	public void manageBreakingNewsOption(boolean activated) {
		if(activated) {
			breakingNewsOption.activate();
		}else {
			breakingNewsOption.desactivate();
		}
	}
	
	public void manageFMOption(boolean activated) {
		if(activated) {
			fmOption.activate();
		}else {
			fmOption.desactivate();
		}
	}
	
	public void manageUSBOption(boolean activated) {
		if(activated) {
			usbOption.activate();
		}else {
			usbOption.desactivate();
		}
	}
	
}
