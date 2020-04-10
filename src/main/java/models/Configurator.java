package models;

import java.io.IOException;
import java.util.ArrayList;

import controllers.ConfigurationController;
import controllers.RadioController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import models.options.*;

public class Configurator {

	private RadioPlayer radio;
	private ConfigurationController configurationController;
	private RadioController radioController;
	private boolean[] optionsArray = new boolean[9];
	

	public Configurator(ConfigurationController controller) {
		this.configurationController = controller;
	}
	
	////////////////////////////////////////////////////////////
	//			Fonctions générales			                  //
	////////////////////////////////////////////////////////////
	
	
	/**
	 * Fonction qui va être appelée lors de la génération d'une radio
	 * On va fermer l'écran de configuration et ouvrir l'écran de radio
	 * @throws IOException 
	 */
	public void generateRadioForTheFirstTime() throws IOException {
		openRadioPlayerScreen();
		this.radio = radioController.getRadioPlayer();
		this.radio.manageOptions(this.configurationController.getOptionsArray());
		hideConfiguratorScreen();
	}

	/**
	 * Fonction qui va être appelé lorsque nous avons déjà configuré une radio et que nous voulons mettre à jour ses options.
	 * Nous renvoyons un tableau d'options à la radio. Ce tableau correspond au checkbox qui sont cochés dans le ConfigurationController.
	 */
	public void updateRadioOptions() {
		this.radio.manageOptions(this.configurationController.getOptionsArray());
		configurationController.hideScreen();
	}
	
	////////////////////////////////////////////////////////////
	//			Afficher ou cacher des écrans                 //
	////////////////////////////////////////////////////////////
	
	public void openRadioPlayerScreen() {
		try{
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/RadioPanel.fxml"));
			Parent radioParent = (Parent) fxmlLoader.load();
			radioController = fxmlLoader.<RadioController>getController();
			radioController.setConfigurator(this);
			radioController.initialScreen();
			Stage radioStage = new Stage();
			radioStage.setTitle("Simulateur de radio");
			radioStage.setScene(new Scene(radioParent));
			radioStage.show();
			
			//Permet de complètement fermer l'application//
			//C'est à dire stopper tous les threads      //
			radioStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent e) {
			    	Platform.exit();
			    	System.exit(0);
			    }
			  });
		
		} catch (IOException e) {
			System.out.println("Impossible de charger la page RadioPanel.fxml");
			System.out.println(e);
		}
	}
	
	/**
	 * Fonction qui va demander au controller de montrer la fenêtre de configuration
	 */
	public void showConfiguratorScreen() {
		this.configurationController.showScreen();
	}
	
	/**
	 * Fonction qui va demander au controller de fermer la fenêtre de configuration
	 */
	private void hideConfiguratorScreen() {
		this.configurationController.hideScreen();
	}

	
	//////////////////////////
	// Getters et setters   //
	//////////////////////////
	
	public RadioPlayer getRadio() {
		return radio;
	}

	public void setRadio(RadioPlayer radio) {
		this.radio = radio;
	}

	public ConfigurationController getConfigurationController() {
		return configurationController;
	}

	public void setConfigurationController(ConfigurationController configurationController) {
		this.configurationController = configurationController;
	}

	public RadioController getRadioController() {
		return radioController;
	}

	public void setRadioController(RadioController radioController) {
		this.radioController = radioController;
	}
	
	public boolean[] getOptionsArray() {
		return optionsArray;
	}

	public void setOptionsArray(boolean[] optionsArray) {
		this.optionsArray = optionsArray;
	}
}
