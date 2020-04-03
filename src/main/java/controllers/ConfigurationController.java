package controllers;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    private ImageView imgAboutIt,imgOptions,imgInputSignal,imgMenuView,
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
    private CheckBox chbxAlarm,chbxAudioIN,chbxAutotune,chbxAudioOUT,chbxBreakNews,chbxSecondarySpeaker,chbxAutoDateTime,chbxFMSupport,
    chbxDAB,chbxFM,chbxUSB,chbxAuxIN;
    @FXML
    private TextArea txtDescriptionSignal;
	
    //Variables normales
	private Configurator configurator;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		configurator = new Configurator(this);
		lblMenuView.setText("A propos de l'application");
		imgMenuView.setImage(imgAboutIt.getImage());
		panMenuView.setBackground(new Background(new BackgroundFill(Color.rgb(122, 134, 151), CornerRadii.EMPTY , Insets.EMPTY)));
		panAboutIt.toFront();
	}
	
	@FXML
	private void generateRadioClick(ActionEvent event) throws IOException {
		configurator.generateRadioForTheFirstTime();
	}
	
	public void showScreen() {
		//TODO Implémenter le fait de rendre visible l'écran de configuration
	}
	
	public void hideScreen() {
		Stage.getWindows().get(0).hide();
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
		configurator.manageAlarmOption(chbxAlarm.isSelected());
		iconImgAlarm.setVisible(chbxAlarm.isSelected());
	}
	
	@FXML
	private void auxInOptionClick() {
		configurator.manageAuxInOption(chbxAudioIN.isSelected());
		iconImgAudioIN.setVisible(chbxAudioIN.isSelected());
	}
	
	@FXML
	private void autotuneOptionClick() {
		configurator.manageAutotuneOption(chbxAutotune.isSelected());
		iconImgAutotune.setVisible(chbxAutotune.isSelected());
	}
	
	@FXML
	private void audioOutExtClick() {
		iconImgAudioOUT.setVisible(chbxAudioOUT.isSelected());
	}
	
	@FXML
	private void breakingNewsOptionClick() {
		configurator.manageBreakingNewsOption(chbxBreakNews.isSelected());
		iconImgBreakNews.setVisible(chbxBreakNews.isSelected());
	}
	
	@FXML
	private void secondarySpeakerOptionClick() {
		iconImgSecondSpeaker.setVisible(chbxSecondarySpeaker.isSelected());
	}
	
	@FXML
	private void dateAndHourAutoOptionClick() {
		configurator.manageDateAndHourAutoOption(chbxAutoDateTime.isSelected());
		iconImgAutoDateTime.setVisible(chbxAutoDateTime.isSelected());
	}
	
	@FXML
	private void fmOptionClick() {
		configurator.manageFMOption(chbxFMSupport.isSelected());
		iconImgFMSupport.setVisible(chbxFMSupport.isSelected());
	}
		
	
	
}
