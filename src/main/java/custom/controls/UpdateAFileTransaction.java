/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.controls;

/**
 *
 * @author shakir.gusaroff
 */
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
public class UpdateAFileTransaction extends VBox {
    
   /* A new file created successfully
Tranaction time: 6.559 seconds
The new file Id: 0.0.49236799
    
    My file content is hello
    
   */ 
    
    

    //public static String hederaAccount = "0.0.16447089";
    //public static String hederaPrivateKey = "302e020100300506032b6570042204200ea60b801334a524e373ef964ad0663ca9f3e8511a9d4a29afdfb990b1f0cbbc";
    /*
    12/23/2022
    Smart contract deployed to the Hedera network successfully
Deployment time: 2.587 seconds
The new account ID is: 0.0.49153502
The private key is: 302e020100300506032b65700422042087fe3a4ea8424126043e37cd57faf7150df8786fcec23cb012461552ab1f4251
The new account balance is: 10 ℏ
    
    
    Smart contract deployed to the Hedera network successfully
Deployment time: 1.316 seconds
The new account ID is: 0.0.49153538
The private key is: 302e020100300506032b657004220420d37f59bceb31c4c8e65a92249f3fbf4cf20831e1821d3101c58ee21af88ecce0
The new account balance is: 10 ℏ

Smart contract deployed to the Hedera network successfully
Deployment time: 1.317 seconds
The new account ID is: 0.0.49165334
The private key is: 302e020100300506032b6570042204202d3c8aad7f243025fc2ce7900be6092057e67be22eda7f76ce6c3f9d0eb9bf7d
The new account balance is: 10 ℏ
    
    A new account created successfully
Tranaction time: 2.257 seconds
The new account ID is: 0.0.49165337
The private key is: 302e020100300506032b6570042204206abe813fdfebb703c7fcb398088d96fc47bd4435d160d9cc54b5a38e57558f0a
The new account balance is: 10 ℏ



     */
    Task myWorker;

    Font fnt = new Font("Lucida Sans Unicode", 24);
    Text header = new Text("Update a file transaction");
    AccountInfo accInfo;
    Button buttonuPDATEAFile = new Button("UPDATE a file");
    final HBox hb_taskProgress = new HBox();
    TextArea fileContentsTa = new TextArea();
    TextArea notification = new TextArea();
    final ProgressIndicator progressIndicator = new ProgressIndicator(0);
    
    TextField fileIdTf = new TextField();

    public UpdateAFileTransaction() {
        super(10);
        setMaxHeight(Double.MAX_VALUE);
       
        accInfo = new AccountInfo();
        
         Region spacer_over_header = new Region();
        spacer_over_header.setPrefHeight(15);
         getChildren().add(spacer_over_header);
        header.setFont(fnt);
        getChildren().add(header);
        getChildren().add(accInfo);
        
        
        TitledPane fileContentsTp = new TitledPane();
        fileContentsTp.setText("File contents");
         fileContentsTp.setContent(fileContentsTa);
          getChildren().add(fileContentsTp);
         
         //For  file id . Label and text fileld inside HBox
         HBox hb = new HBox(15);
          Font fnt = Font.font("Verdana",  16);
         Label fileId = new Label("File ID: ");
         fileId.setFont(fnt);
         hb.getChildren().addAll(fileId, fileIdTf);
         getChildren().add(hb);
         
         
         
         
         
         
        
       
        
        getChildren().add(buttonuPDATEAFile);
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

        buttonuPDATEAFile.setOnAction((ActionEvent event) -> {

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
                String a = new UpdateAFile().updateAFile(accInfo.networkChoiceBox.getValue().toString(), accInfo.tfHederaAccount.getText(), accInfo.tfPrivateKey.getText(), fileContentsTa.getText(), fileIdTf.getText());

                return a;
            }

        };

    }
}
