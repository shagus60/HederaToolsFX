/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.controls;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import custom.baking.*;

/**
 *
 * @author shakir.gusaroff
 */
public class CreateATransferTransaction extends VBox{
    Task myWorker;

    Font fnt = new Font("Lucida Sans Unicode", 24);
    Text header = new Text("Create a transfer transaction");
    AccountInfo accInfo;
    Button buttonCreateATransferTransaction = new Button("Create a transfer");
    final HBox hb_taskProgress = new HBox();
  
    TextArea notification = new TextArea();
    final ProgressIndicator progressIndicator = new ProgressIndicator(0);
    
    TextField amountTf = new TextField();
    
    
    TextField memoTf = new TextField();
TextField receiverAccountTf = new TextField();
    public CreateATransferTransaction () {
        super(10);
        setMaxHeight(Double.MAX_VALUE);
       
        accInfo = new AccountInfo();
        
         Region spacer_over_header = new Region();
        spacer_over_header.setPrefHeight(15);
         getChildren().add(spacer_over_header);
        header.setFont(fnt);
        getChildren().add(header);
        
        HBox hb = new HBox(10);
        hb.getChildren().add(accInfo);
        
        
        
        VBox memoAndAmount = new VBox(10) ;
        
        TitledPane tp = new TitledPane();
        tp.setText("Transfer amount");
        tp.setContent(amountTf);
         memoAndAmount.getChildren().add(tp);
         
         TitledPane tpMemo = new TitledPane();
        tpMemo.setText("Memo");
        tpMemo.setContent(memoTf);
         memoAndAmount.getChildren().add(tpMemo);
         hb.getChildren().add(memoAndAmount);
         
         
         TitledPane tpReceiverAccount  = new TitledPane();
        tpReceiverAccount.setText("Receiver account number");
        tpReceiverAccount.setContent(receiverAccountTf);
        hb.getChildren().add(tpReceiverAccount);
         
                 
        
        getChildren().add(hb);
        
        
        
        
        
        
        getChildren().add(buttonCreateATransferTransaction);
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
       

        buttonCreateATransferTransaction.setOnAction((ActionEvent event) -> {

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
              String a = "";
                 a = new CreateATransfer().createATransfer(accInfo.networkChoiceBox.getValue().toString(), accInfo.tfHederaAccount.getText(), accInfo.tfPrivateKey.getText(), receiverAccountTf.getText(), amountTf.getText(), memoTf.getText().trim());
                      
                return a;
            }

        };

    }
}
