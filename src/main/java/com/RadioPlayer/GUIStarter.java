package com.RadioPlayer;
import java.io.IOException;

import com.RadioPlayer.controllers.ConfigurationController;
import com.RadioPlayer.controllers.RadioController;
import com.RadioPlayer.models.Configurator;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.fxml.FXMLLoader;
 
public class GUIStarter extends Application {
    
	private double xOffset = 0;
	private double yOffset = 0;
	
	public static void main(String[] args) {
        launch(args);
    }
	
    @Override
    public void start(Stage stage) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/com/RadioPlayer/views/ConfigurationScreen.fxml"));
        Parent root = (Parent) loader.load();
        ConfigurationController configurationController = loader.<ConfigurationController>getController();
        configurationController.setStage(stage);
        Platform.setImplicitExit(false);
        Scene scene = new Scene(root);
        stage.setTitle("Configurateur");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent e) {
		    	Platform.exit();
		    	System.exit(0);
		    }
		  });
        stage.setScene(scene);
        stage.show();

    }
    
}