import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.FXMLLoader;
 
public class GUIStarter extends Application {
    
	private double xOffset = 0;
	private double yOffset = 0;
	
	public static void main(String[] args) {
        launch(args);
    }
	
    @Override
    public void start(Stage stage) throws IOException {
    	
    	FXMLLoader loader = new FXMLLoader(this.getClass().getResource("views/ConfigurationScreen.fxml"));
        Parent root = (Parent) loader.load();
        
        Scene scene = new Scene(root);
        stage.setTitle("FXML Welcome");
        stage.setScene(scene);
        stage.show();
    }
    
}