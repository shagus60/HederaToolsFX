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
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/**
 *
 * @author shakir.gusaroff
 */
public class UpdateAnAccountStakingTransaction extends VBox {

    //   A new account created successfully
//Tranaction time: 2.922 seconds
//The new account ID is:  0.0.4750
//The private key is: 302e020100300506032b6570042204202da5d389d5dcdbb093d47e43ced7d46932f12b9637b3af7a5ee6ed6906445213
//The new account balance is: 10 ℏ
    Task myWorker;

    Font fnt = new Font("Lucida Sans Unicode", 24);
    Text header = new Text("Update an account (Staking)");
    AccountInfo accInfo;
    Button submit = new Button("Update an account");
    final HBox hb_taskProgress = new HBox();

    TextArea notification = new TextArea();
    final ProgressIndicator progressIndicator = new ProgressIndicator(0);

    public final ChoiceBox nodesChoiceBox = new ChoiceBox(FXCollections.observableArrayList(
            "0 - Hosted by LG",
            "1 - Hosted by Swirlds",
            "2 - Hosted by FIS",
            "3 - Hosted by Wipro",
            "4 - Hosted by Nomuro",
            "5 - Hosted by Google",
            "6 - Hosted by Zain",
            "7 - Hosted by Magalu",
            "8 - Hosted by Boeing",
            "9 - Hosted by DLA Piper",
            "10 - Hosted by Tata communication",
            "11 - Hosted by IBM",
            "12 - Hosted by Deutche Telecom",
            "13 - Hosted by UCL",
            "14 - Hosted by Avery Denison",
            "15 - Hosted by Dentons",
            "16 - Hosted by Standard Bank"
    ));

    public UpdateAnAccountStakingTransaction() {
        super(10);
        setMaxHeight(Double.MAX_VALUE);

        accInfo = new AccountInfo();
        header.setFont(fnt);
         
        Region spacer_over_header = new Region();
        spacer_over_header.setPrefHeight(15);
        getChildren().add(spacer_over_header);
        getChildren().add(header);
        getChildren().add(accInfo);
        
        HBox hboxForNode = new HBox(10);
        Label lblNode = new Label("Choose a node to stake to:");
        lblNode.setFont(Font.font("Verdana",  16));
        hboxForNode.getChildren().addAll(lblNode,nodesChoiceBox);
       // nodesChoiceBox.getSelectionModel().getSelectedIndex()
        getChildren().add(hboxForNode);
        
        
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
                String a = new UpdateAnAccountStaking().updateAccountStaking(accInfo.networkChoiceBox.getValue().toString(), accInfo.tfHederaAccount.getText(), accInfo.tfPrivateKey.getText(), nodesChoiceBox.getSelectionModel().getSelectedIndex());

                return a;
            }

        };

    }
}
