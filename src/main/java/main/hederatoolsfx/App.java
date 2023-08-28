package main.hederatoolsfx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import custom.controls.*;
import custom.image.*;
import javafx.stage.Screen;
/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
       
     
    BorderLayoutLandingView borderPane = new BorderLayoutLandingView();
    
   
       Scene scene = new Scene(borderPane , Screen.getPrimary().getBounds().getWidth()*0.8 , Screen.getPrimary().getBounds().getHeight()*0.8 , Color.BEIGE);
     //  System.out.println("Hello " + getClass().getResource("/style.css").toExternalForm());
      scene.getStylesheets().addAll("style.css");

      
      
      stage.setTitle("Hedera Transaction Tool");
      stage.setScene(scene);
      stage.show();
      
     
    }

    public static void main(String[] args) {
        launch();
    }

}