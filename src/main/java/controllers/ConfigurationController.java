package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.*;

public class ConfigurationController implements Initializable {

	//Composants présents dans le FXML
	@FXML
    private ImageView imgAboutIt,imgOptions,imgInputSignal,imgMenuView,iconImgUSBSupport,
	iconImgAlarm,iconImgAudioIN,iconImgAutotune,iconImgAudioOUT,iconImgBreakNews,iconImgSecondSpeaker,iconImgAutoDateTime,iconImgFMSupport;
    @FXML
    private Button btnInputSginal,btnOptions,btnGenerate;
    @FXML
    private Pane panMenuView,panAboutIt,panInputSignal;
    @FXML
    private Label lblMenuView;
    @FXML
    private GridPane panOptions;
    @FXML
    private CheckBox chbxAlarm,chbxAuxINSupport,chbxAutotune,chbxAudioOUT,chbxBreakNews,chbxSecondarySpeaker,chbxAutoDateTime,
    chbxDAB,chbxFMSupport,chbxUSBSupport;
    @FXML
    private TextArea txtDescriptionSignal;
    
    //////////////////////
    //Variables normales//
    //////////////////////
    
	private Configurator configurator;
	private boolean[] optionsArray = new boolean[9];
	private Stage stage;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configurator = new Configurator(this);
		lblMenuView.setText("A propos de l'application");
		imgMenuView.setImage(imgAboutIt.getImage());
		panMenuView.setBackground(new Background(new BackgroundFill(Color.rgb(122, 134, 151), CornerRadii.EMPTY , Insets.EMPTY)));
		panAboutIt.toFront();
	}
	
	/**
	 * Fonction qui va être appelé quand on va vouloir appuyer sur le bouton générer/sauver.
	 * Si aucune radio n'a jamais été générée on va en générer une, sinon on va juste mettre à jour ses options.
	 * @param event
	 * @throws IOException
	 */
	@FXML
	private void generateRadioClick(ActionEvent event) throws IOException {
		if(configurator.getRadio()==null) {
			configurator.generateRadioForTheFirstTime();
		} else {
			configurator.updateRadioOptions();
		}
	}
	
	/**
	 * Rend visible l'écran de configuration
	 */
	public void showScreen() {
		stage.show();
		btnGenerate.setText("Sauver");
	}
	
	/**
	 * Rend invisible l'écran de configuration
	 */
	public void hideScreen() {
		stage.hide();
	}
	
	@FXML
	private void menuOptionsClick(ActionEvent event)
	{
		lblMenuView.setText(btnOptions.getText());
		imgMenuView.setImage(imgOptions.getImage());
		panMenuView.setBackground(new Background(new BackgroundFill(Color.rgb(97, 64, 13), CornerRadii.EMPTY , Insets.EMPTY)));
		panOptions.toFront();
	}
	
	@FXML
	private void menuInputSignalClick(ActionEvent event)
	{
		lblMenuView.setText(btnInputSginal.getText());
		imgMenuView.setImage(imgInputSignal.getImage());
		panMenuView.setBackground(new Background(new BackgroundFill(Color.rgb(82, 116, 85), CornerRadii.EMPTY , Insets.EMPTY)));
		panInputSignal.toFront();
	}
	
	@FXML
	private void aboutItClick()
	{
		lblMenuView.setText("A propos de l'application");
		imgMenuView.setImage(imgAboutIt.getImage());
		panMenuView.setBackground(new Background(new BackgroundFill(Color.rgb(122, 134, 151), CornerRadii.EMPTY , Insets.EMPTY)));
		panAboutIt.toFront();
	}
	
	@FXML
	private void closeConfigClick(ActionEvent event)
	{
		System.exit(0);
	}
	
	//////////////////////////
	// Click sur les options//
	//////////////////////////
	@FXML
	private void alarmOptionClick() {
		optionsArray[0] = chbxAlarm.isSelected();
		iconImgAlarm.setVisible(chbxAlarm.isSelected());
	}
	
	@FXML
	private void audioOutExtClick() {
		optionsArray[1] = chbxAudioOUT.isSelected();
		iconImgAudioOUT.setVisible(chbxAudioOUT.isSelected());
	}
	
	@FXML
	private void autotuneOptionClick() {
		optionsArray[2] = chbxAutotune.isSelected();
		iconImgAutotune.setVisible(chbxAutotune.isSelected());
	}
	
	@FXML
	private void auxInSupportClick() {
		optionsArray[3] = chbxAuxINSupport.isSelected();
		iconImgAudioIN.setVisible(chbxAuxINSupport.isSelected());
	}
	
	@FXML
	private void breakingNewsOptionClick() {
		optionsArray[4] = chbxBreakNews.isSelected();
		iconImgBreakNews.setVisible(chbxBreakNews.isSelected());
	}
	
	@FXML
	private void dateAndHourAutoOptionClick() {
		optionsArray[5] = chbxAutoDateTime.isSelected();
		iconImgAutoDateTime.setVisible(chbxAutoDateTime.isSelected());
	}
	
	@FXML
	private void fmSupportClick() {
		optionsArray[6] = chbxFMSupport.isSelected();
		iconImgFMSupport.setVisible(chbxFMSupport.isSelected());
	}
	
	@FXML
	private void secondarySpeakerOptionClick() {
		optionsArray[7] = chbxSecondarySpeaker.isSelected();
		iconImgSecondSpeaker.setVisible(chbxSecondarySpeaker.isSelected());
	}
	
	@FXML
	private void usbSupportClick() {
		optionsArray[8] = chbxUSBSupport.isSelected();
		iconImgUSBSupport.setVisible(chbxUSBSupport.isSelected());
	}
		
	//////////////////////////
	// Getters et setters   //
	//////////////////////////
	
	public Configurator getConfigurator() {
		return configurator;
	}
	//////////////////////////
	// Getters et setters   //
	//////////////////////////
	
	public void setConfigurator(Configurator configurator) {
		this.configurator = configurator;
	}
	
	public boolean[] getOptionsArray() {
		return this.optionsArray;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
}
