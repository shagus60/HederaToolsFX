/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.controls;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import custom.baking.*;
import custom.image.*;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 *
 * @author shakir.gusaroff
 */
public class SubmitATopicTransaction extends VBox {
    
    /*
    A new topic created successfully
Tranaction time: 2.334 seconds
The new topic Id: 0.0.3381421

    */
    
  
    Task myWorker;

    Font fnt = new Font("Lucida Sans Unicode", 24);
    Text header = new Text("Submit a message to the topic");
    AccountInfo accInfo;
    Button submit = new Button("Submit");
    final HBox hb_taskProgress = new HBox();
    TextArea fileContentsTa = new TextArea();
    TextArea notification = new TextArea();
    final ProgressIndicator progressIndicator = new ProgressIndicator(0);
    
    TextField topicIdTf = new TextField();

    public SubmitATopicTransaction() {
        super(10);
        setMaxHeight(Double.MAX_VALUE);
       
        accInfo = new AccountInfo();
        
         Region spacer_over_header = new Region();
        spacer_over_header.setPrefHeight(15);
         getChildren().add(spacer_over_header);
        header.setFont(fnt);
        getChildren().add(header);
        getChildren().add(accInfo);
        
        
        TitledPane messageTp = new TitledPane();
        messageTp.setText("Message");
         messageTp.setContent(fileContentsTa);
          getChildren().add(messageTp);
         
         //For  file id . Label and text fileld inside HBox
         HBox hb = new HBox(15);
          Font fnt = Font.font("Verdana",  16);
         Label topicId = new Label("Topic ID: ");
         topicId.setFont(fnt);
         hb.getChildren().addAll(topicId, topicIdTf);
         getChildren().add(hb);
         
         
         
         
         
         
        
       
        
        getChildren().add(submit);
       // getChildren().add(new H());
        getChildren().add(hb_taskProgress);
        hb_taskProgress.setAlignment(Pos.CENTER);
        
        //The following spacer region is used to attach notification to the bottom
        Region spacer = new Region();
        spacer.setPrefHeight(40);
        VBox.setVgrow(spacer, Priority.ALWAYS);
        getChildren().add(spacer);
        
        
        TitledPane logTp = new TitledPane();
        logTp.setText("Log");
         logTp.setContent(notification);        
        getChildren().add(logTp);

        submit.setOnAction((ActionEvent event) -> {

            //System.out.println(accInfo.tfHederaAccount.getText());
            //System.out.println(accInfo.networkChoiceBox.getValue());
            //System.out.println(accInfo.tfPrivateKey.getText());

            try {
                notification.setText("");
                myWorker = createTask();

                progressIndicator.progressProperty().bind(myWorker.progressProperty());

                myWorker.stateProperty().addListener(new ChangeListener<Worker.State>() {
                    public void changed(ObservableValue<? extends Worker.State> observable, Worker.State oldValue, Worker.State newValue) {
                       ;
                        if (newValue == Worker.State.SUCCEEDED) {
                            hb_taskProgress.getChildren().remove(progressIndicator);
                            notification.setText(myWorker.getValue().toString());

                        } else if (newValue == Worker.State.RUNNING) {
                            // HBox.setHgrow(h, Priority.NEVER);
                            hb_taskProgress.getChildren().add(progressIndicator);
                        }
                    }
                });

                new Thread(myWorker).start();

            } catch (Exception ex) {
                
            }

        });
    }

    public Task<String> createTask() throws Exception {
        return new Task<String>() {

            @Override
            protected String call() throws Exception {
                String a = new SubmitATopic().submitATopic(accInfo.networkChoiceBox.getValue().toString(), accInfo.tfHederaAccount.getText(), accInfo.tfPrivateKey.getText(), fileContentsTa.getText(), topicIdTf.getText());

                return a;
            }

        };

    }
}
