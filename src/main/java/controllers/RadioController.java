package controllers;

import java.text.DecimalFormat;

import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Configurator;
import models.RadioPlayer;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import models.enums.AlarmMenu;
import models.enums.DateAndTimeMenu;
import models.enums.DisplayType;
import models.enums.InputSignalMenu;
import models.enums.MainMenu;
import states.IdleState;


public class RadioController {

	///////////////////
	// Variables fxml//
	///////////////////
	
	@FXML
    private Button btnPower,btnEdit, btnFailureRadio, btnMenuRadio, btnAutotone, btnPresetUn, btnPresetDeux,btnPresetTrois, btnArrowUp, btnValidate;
	@FXML
    private Button btnArrowDown, btnArrowLeft, btnArrowRight, btnRadioAuxOutModifyStatus, btnRadioAlarmModifyStatus;
    @FXML
    private ImageView imgRadioFirstSpeaker, imgRadioSecondarySpeaker, imgRadioMainScreenMusicImage, imgRadioMainScreenStationLogo;
    @FXML
    private HBox hboxMenuAlarm, hboxRadioMainScreen, hboxRadioMainScreenVolume, hboxMenuDateAndTime, hboxMenuMain, hboxMenuInputSignal;
    @FXML
    private GridPane gridMenuAlarm, gridInputSignalDAB, gridInputSignalFM, gridInputSignalUSB, gridInputSignalAuxIN, gridRadioMainScreenStationInfo;
    @FXML
    private GridPane gridMenuDateAndTimeTimeEdit, gridMenuDateAndTimeDateEdit, gridMenuDateAndTime, gridMenuInputSignal;
    @FXML
    private Label lblAlarmHourEdit, lblAlarmMinutEdit,lblRadioMainScreenSignalType, lblRadioMainScreenDateAndTime, lblRadioMainScreenMessageEdit;
    @FXML
    private Label lblRadioMainScreenStationName, lblRadioMainScreenVolumeEdit, lblMenuDateAndTimeTime, lblDateAndTimeHourEdit;
    @FXML
    private Label lblDateAndTimeMinutEdit,lblMenuDateAndTimeDate, lblDateAndTimeDayEdit, lblDateAndTimeMonthEdit, lblDateAndTimeYearEdit;
	@FXML
	private Button rotator_dial;
	@FXML
	private Button rotator_handle;
	@FXML
	private AnchorPane paneRadio;
	
	private final int roundingFactor = 1; // no decimals rounding
	private final DoubleProperty rotation = new SimpleDoubleProperty();
	
	private RadioPlayer radio;
	private Configurator configurator;
	
	//////////////////////////////////////
	//			Constructeur            //
	//////////////////////////////////////
	
	public RadioController() {
	}
	
	@FXML
	private void initialize() {
		
		radio = new RadioPlayer(configurator);
		radio.setRadioController(this);
		rotator_handle.setRotate(210);
        
        rotator_dial.setOnAction((ActionEvent event) -> {
            event.consume();
        });
        rotator_handle.setOnAction((ActionEvent event) -> {
            event.consume();
        });
	}
	
	
	//////////////////////////////////////
	// Fonctions int�ractions graphique //
	//////////////////////////////////////
	
	@FXML
	public void openConfigurationScreen() {
		this.radio.openConfigurationScreen();
	}
	
    @FXML
    void rotatorMousePressed(MouseEvent e) {
        rotatorMouseDragged(e);
    }
    
    @FXML
    void rotatorMouseDragged(MouseEvent e) {
        final Parent parent = rotator_dial.getParent();
        final Bounds boundsLayout = rotator_dial.getLayoutBounds();
        final Double centerX = boundsLayout.getMinX() + (boundsLayout.getWidth() / 2);
        final Double centerY = boundsLayout.getMinY() + (boundsLayout.getHeight() / 2);
        final Point2D center = parent.localToParent(centerX, centerY);
        final Point2D mouse = parent.localToParent(e.getX(), e.getY());
        final Double deltaX = mouse.getX() - center.getX();
        final Double deltaY = mouse.getY() - center.getY();
        final Double radians = Math.atan2(deltaY, deltaX);
        rotate(Math.toDegrees(radians));
    }

    /**
     * M�thode permettant de r�aliser la rotation du bouton et permettre ainsi la modification du volume
     * @param value Valeur de l'angle de rotation
     */
    private void rotate(Double value) {
    	
    	Double Rotation = value;
    	// Permet d'obtenir une rotation comprise entre 0 et 360 à la place de -180 - 0 - 180
    	if (value < 0) Rotation = 360 + value;

    	// Fixer la rotation tolérée entre 210 et 150
    	if (Rotation > 210 || Rotation < 150) {
    		//int rounded = round(Rotation, roundingFactor);
    		rotation.set(Rotation);
    		rotator_handle.setRotate(Rotation);
    		// Ajout de 150 à la valeur pour passer dans un système 0 - 300 
    		float scaleDegrees = (float) (value + 150);
    		// Division par 3 pour obtenir un pourcentage
    		int percent = (int) (Math.round(scaleDegrees / 3));
    		System.out.println(percent);
    	}   

    }

    @FXML
	private void btnPowerClick(ActionEvent e) {
		radio.getCurrentState().onOffClick();
	}
	
	@FXML
	private void btnEditClick(ActionEvent e) {
		//TODO implémenter la réouverture du configurateur
		radio.editRadioConfiguration();
	}
	
	@FXML
	private void btnFailureClick(ActionEvent e) {
		//TODO implémenter l'ouverture de la gestion des pannes
	}
	
	@FXML
	private void btnMenuClick(ActionEvent e) {
		radio.getCurrentState().menuClick();
	}
	
	@FXML
	private void btnAutotuneClick(ActionEvent e) {
		radio.getCurrentState().autotuneClick();
	}
	
	@FXML
	private void btnPresetOneClick(ActionEvent e) {
		//Sauvegarde
		radio.getPlayer().setPreset1();
		//chargement
		if(radio.getPlayer().getPreset1()!=null) {
			radio.getPlayer().setCurrentMediaIndex(radio.getPlayer().getPreset1().getIndex());
		}
	}
	
	@FXML
	private void btnPresetTwoClick(ActionEvent e) {
		//Sauvegarde
		radio.getPlayer().setPreset2();
		//chargement
		radio.getPlayer().setCurrentMediaIndex(radio.getPlayer().getPreset2().getIndex());
	}
	
	@FXML
	private void btnPresetThreeClick(ActionEvent e) {
		//Sauvegarde
		radio.getPlayer().setPreset3();
		//chargement
		radio.getPlayer().setCurrentMediaIndex(radio.getPlayer().getPreset3().getIndex());
	}
	
	@FXML
	private void btnValidateClick(ActionEvent e) {
		radio.getCurrentState().okClick();
	}
	
	@FXML
	private void btnArrowUpClick(ActionEvent e) {
		radio.getCurrentState().upClick();
	}
	
	@FXML
	private void btnArrowDownClick(ActionEvent e) {
		radio.getCurrentState().downClick();
	}
	
	@FXML
	private void btnArrowLeftClick(ActionEvent e) {
		radio.getCurrentState().leftClick();
	}
	
	@FXML
	private void btnArrowRightClick(ActionEvent e) {
		radio.getCurrentState().rightClick();
	}
	
	@FXML
    public void btnRadioAlarmModifyStatusClick(ActionEvent e) {
    	radio.getCurrentState().alarmClick();
    }
	
	/**
	 * M�thode permettant de mettre au premier plan l'�cran initial de la radio 
	 */ 
	public void initialScreen() {
    	hboxRadioMainScreen.toFront();
    	//TODO Regarder pour bouger �a car c'est d�gueu
    	radio.setCurrentState(new IdleState(radio));
    }
    
    /*-----------------------------------------------------------------------
     *---- 	  METHODES CONCERNANT LES MODIFICATIONS DU MENU   			 ----
     *-----------------------------------------------------------------------
     */
	
	/**
	 * M�thode permettant de mettre au premier plan l'interface de modification du signal d'entr�e
	 */
    public void menuScreen() {
    	hboxMenuMain.toFront();
    	
    }
     /**
      * M�thode permettant de montrer sur l'interface quelle propri�t� sera s�lectionn�e
      * @param currentMenu �num�ration permettant de savoir quelle propri�t� modifier
      */
    public void changeFocusMainMenu(MainMenu currentMenu) {
    	
    	switch(currentMenu) {
	    	case DateAndHour :
	    		changeFocusOfGridPane(gridMenuDateAndTime, true);
	    		changeFocusOfGridPane(gridMenuInputSignal, false);
	    		changeFocusOfGridPane(gridMenuAlarm, false);
	    		break;
	    	
	    	case InputSignal : 
	      		changeFocusOfGridPane(gridMenuDateAndTime, false);
	    		changeFocusOfGridPane(gridMenuInputSignal, true);
	    		changeFocusOfGridPane(gridMenuAlarm, false);
	    		break;
	    	
	    	case Alarm :
	      		changeFocusOfGridPane(gridMenuDateAndTime, false);
	    		changeFocusOfGridPane(gridMenuInputSignal, false);
	    		changeFocusOfGridPane(gridMenuAlarm, true);
	    		break;
    	}
    
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
     * M�thode permettant de mettre au premier plan l'interface de modification du signal d'entr�e
     */
    public void inputSignalMenu() {
    	hboxMenuInputSignal.toFront();
    }
    
    /**
     * M�thode permettant de montrer sur l'interface quelle propri�t� sera s�lectionn�e
     * @param currentInputSignal �num�ration permettant de savoir quelle propri�t� modifier
     */
    public void changeFocusInputSignalMenu(InputSignalMenu currentInputSignal) {
    	
    	switch(currentInputSignal) {
	    	case DAB :
	    		changeFocusOfGridPane(gridInputSignalDAB, true);
	    		changeFocusOfGridPane(gridInputSignalFM, false);
	    		changeFocusOfGridPane(gridInputSignalUSB, false);
	    		changeFocusOfGridPane(gridInputSignalAuxIN, false);
	    		break;
	    	
	    	case FM : 
	    		changeFocusOfGridPane(gridInputSignalDAB, false);
	    		changeFocusOfGridPane(gridInputSignalFM, true);
	    		changeFocusOfGridPane(gridInputSignalUSB, false);
	    		changeFocusOfGridPane(gridInputSignalAuxIN, false);
	    		break;
	    	
	    	case USB :
	    		changeFocusOfGridPane(gridInputSignalDAB, false);
	    		changeFocusOfGridPane(gridInputSignalFM, false);
	    		changeFocusOfGridPane(gridInputSignalUSB, true);
	    		changeFocusOfGridPane(gridInputSignalAuxIN, false);
	    		break;
	    		
	    	case AuxIn :
	    		changeFocusOfGridPane(gridInputSignalDAB, false);
	    		changeFocusOfGridPane(gridInputSignalFM, false);
	    		changeFocusOfGridPane(gridInputSignalUSB, false);
	    		changeFocusOfGridPane(gridInputSignalAuxIN, true);
	    		break;
    	}
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
     * M�thode permettant de mettre au premier plan l'interface de modification de la date et heure
     */
    public void dateAndTimeMenu() {
    	hboxMenuDateAndTime.toFront();
    }
    
    
    /**
     * M�thode permettant de montrer sur l'interface quelle propri�t� sera s�lectionn�e
     * @param currentDateAndTime �num�ration permettant de savoir quelle propri�t� modifier
     */    
    public void changeFocusDateAndTimeMenu(DateAndTimeMenu currentDateAndTime) {
    	
    	switch(currentDateAndTime) {
	    	case Hour :
	    		changeFocusOfLabel(lblDateAndTimeHourEdit, true);
	    		changeFocusOfLabel(lblDateAndTimeMinutEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeDayEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeMonthEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeYearEdit, false);
	    		break;
	    	case Minute :
	    		changeFocusOfLabel(lblDateAndTimeHourEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeMinutEdit, true);
	    		changeFocusOfLabel(lblDateAndTimeDayEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeMonthEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeYearEdit, false);
	    		break;
	    	case Day :
	    		changeFocusOfLabel(lblDateAndTimeHourEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeMinutEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeDayEdit, true);
	    		changeFocusOfLabel(lblDateAndTimeMonthEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeYearEdit, false);
	    		break;
	    	case Month :
	    		changeFocusOfLabel(lblDateAndTimeHourEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeMinutEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeDayEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeMonthEdit, true);
	    		changeFocusOfLabel(lblDateAndTimeYearEdit, false);
	    		break;
	    	case Year :
	    		changeFocusOfLabel(lblDateAndTimeHourEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeMinutEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeDayEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeMonthEdit, false);
	    		changeFocusOfLabel(lblDateAndTimeYearEdit, true);
	    		break;
    	}
    }
    
    /**
     * M�thode permettant de modifier la date et l'heure en fonction de la propri�t� s�lectionn�e
     * @param dateAndTimeMenu �num�ration permettant de savoir quelle propri�t� modifier
     * @param currentDateAndTimeValue
     */
    public void editDateAndTimeLabel(DateAndTimeMenu dateAndTimeMenu, int currentDateAndTimeValue) {
    	
    	switch(dateAndTimeMenu) {
    	case Minute :
    		changeValueOfLabel(lblDateAndTimeMinutEdit, currentDateAndTimeValue);
    		break;
    	case Hour :
    		changeValueOfLabel(lblDateAndTimeHourEdit, currentDateAndTimeValue);
    		break;
    	case Day :
    		changeValueOfLabel(lblDateAndTimeDayEdit, currentDateAndTimeValue);
    		break;
    	case Month :
    		changeValueOfLabel(lblDateAndTimeMonthEdit, currentDateAndTimeValue);
    		break;
    	case Year :
    		changeValueOfLabel(lblDateAndTimeYearEdit, currentDateAndTimeValue);
    		break;
    	}
    	
    }
    
    /**
     * M�thode permettant de modifier toutes les propri�t�s de date et heure en une fois
     * @param dateAndTimeProperties Vecteur d'entier contant toutes les informations de date et heure
     */
    public void editAllDateAndTimeProperties(int [] dateAndTimeProperties) {
    	
    	changeValueOfLabel(lblDateAndTimeMinutEdit, dateAndTimeProperties[0]);
    	changeValueOfLabel(lblDateAndTimeHourEdit, dateAndTimeProperties[1]);
    	changeValueOfLabel(lblDateAndTimeDayEdit, dateAndTimeProperties[2]);
    	changeValueOfLabel(lblDateAndTimeMonthEdit, dateAndTimeProperties[3]);
    	changeValueOfLabel(lblDateAndTimeYearEdit, dateAndTimeProperties[4]);
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
     * M�thode permettant de mettre au premier plan l'interface de modification de l'alarme
     */
    public void alarmMenu() {
    	hboxMenuAlarm.toFront();
    }
    
    /**
     * M�thode permettant de montrer sur l'interface quelle propri�t� est s�lectionn�e
     * @param currentAlarm �num�ration permettant de savoir quelle propri�t� modifier
     */
    public void changeFocusAlarmMenu(AlarmMenu currentAlarm) {
    	
    	switch(currentAlarm) {
	    	case Hour :
	    		changeFocusOfLabel(lblAlarmHourEdit, true);
	    		changeFocusOfLabel(lblAlarmMinutEdit, false);
	    		break;
	    	case Minut :
	    		changeFocusOfLabel(lblAlarmHourEdit, false);
	    		changeFocusOfLabel(lblAlarmMinutEdit, true);
	    		break;
    	}
    }
    
    /**
     * M�thode permettant de modifier l'heure et la minute en fonction de la propri�t� s�lectionn�e
     * @param currentAlarm �num�ration permettant de savoir quelle propri�t� modifier
     * @param alarmValue
     */
    public void editAlarmLabel(AlarmMenu currentAlarm, int alarmValue) {
    	
    	switch (currentAlarm) {
			case Hour:
				changeValueOfLabel(lblAlarmHourEdit,alarmValue);
				break;
	
			case Minut :
				changeValueOfLabel(lblAlarmMinutEdit, alarmValue);
				break;
		}
    }
    
    /**
     * M�thode permettant de modifier le visuel du button de l'alarme afin de savoir si cette derni�re est active ou non. 
     * @param isEnabled
     */
    public void changeAlarmStatus(boolean isEnabled) {
    	if(isEnabled) {
    		btnRadioAlarmModifyStatus.setStyle("-fx-border-color: #42B504; -fx-border-width: 3;");
    	} else {
    		btnRadioAlarmModifyStatus.setStyle("-fx-border-color: #C00000; -fx-border-width: 3;");
    	}
    }
    
    /*---------------------------------------------------------------
     *--- FIN  METHODES CONCERNANT LES MODIFICATIONS DE L'ALARME  ---
     *---------------------------------------------------------------
     */
    
    public void editPlayerInformations(String PlayerName, String name, String imgSongPath, String imgMediaPath) {
    	lblRadioMainScreenStationName.setText(name);
    	lblRadioMainScreenSignalType.setText(PlayerName);
    	//imgRadioMainScreenMusicImage.setImage(new Image(imgSongPath));
    	//imgRadioMainScreenStationLogo.setImage(new Image(imgMediaPath));
    }
    
    /**
     * M�thode permettant de modifier les messages de l'�cran principal de la radio
     * @param displayType �num�ration permettant de savoir quel label modifier dans l'�cran principal
     * @param message string contenant le message � afficher
     */
    public void displayMessageOnMainScreen(DisplayType displayType, String message) {
    	
    	Platform.runLater(() -> {
    			 
	    	switch (displayType) {
		    	case Alarm :
		    		lblRadioMainScreenMessageEdit.setTextFill(Color.web("#4A932E"));
					lblRadioMainScreenMessageEdit.setText(message);
		        	break;
		    	case BreakingNews :
		    		lblRadioMainScreenMessageEdit.setTextFill(Color.web("#C00000"));
					lblRadioMainScreenMessageEdit.setText(message);
		    		break;
		    	case DateAndTime :
		    		lblRadioMainScreenDateAndTime.setText(message);
		    		break;
	    	}   	
    	});
    }
    
    
    /**
     * M�thode permettant la modification de la valeur d'un label avec un passage de la variable du format 1 digit � deux ex : 1 -> 01
     * @param label
     * @param value
     */
    public void changeValueOfLabel(Label label, int value) {
    	label.setText(new DecimalFormat("00").format(value));
    }
    
    /**
     * M�thode permettant de mettre le focus sur un label ou de le remettre � la normale
     * @param label
     * @param isFocused
     */
    public void changeFocusOfLabel(Label label, boolean isFocused) {
    	if(isFocused) {
    		// Griser le fond du label
    		label.setBackground(new Background(new BackgroundFill(Color.rgb(185, 185, 185), null, null)));
    	} else {
    		// Remettre la couleur de fond d'origine du label
    		label.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    	}
    }
    
    
    /**
     * M�thode permettant de mettre le focus sur un grid ou de le remettre � la normale
     * @param grid
     * @param isFocused
     */
    public void changeFocusOfGridPane(GridPane grid, boolean isFocused) {
    	if(isFocused) {
    		// Griser le grid si s�lectionn�
    		grid.setBackground(new Background(new BackgroundFill(Color.rgb(185, 185, 185), new CornerRadii(20), null)));
    		
    	} else {
    		// Remettre la couleur d'origine si d�s�lectionn�
    		grid.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
    	}
    }

	//////////////////////////
	// Getters et setters   //
	//////////////////////////
    
    public void setRadioPlayer (RadioPlayer radio) {
    	this.radio = radio;
    }
    
    public RadioPlayer getRadioPlayer() {
    	return this.radio;
    }
    
    public void setConfigurator(Configurator configurator) {
    	this.configurator = configurator;
    	this.radio.setConfigurator(configurator);
    }
    
    public Configurator getConfigurator() {
    	return this.configurator;
    }
	
}
