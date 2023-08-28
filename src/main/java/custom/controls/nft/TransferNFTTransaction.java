/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.controls.nft;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author shakir.gusaroff
 */


/*
.addNftTransfer( new NftId(tokenId, 1), treasuryId, aliceAccountId)
        .freezeWith(client)
        .sign(treasuryKey);
        
        
Token Id
TreasuryId
Treasury Key
Alice account id

*/



import custom.controls.*;
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
import custom.baking.nft.*;
import custom.image.*;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.FontWeight;

/**
 *
 * @author shakir.gusaroff
 */
public class TransferNFTTransaction extends VBox {

   
    /*
    Created token  with token ID 0.0.3992444
Tranaction time: 3.208 seconds

    
    
    Tresary key and account id
   A new account created successfully
Tranaction time: 4.357 seconds
The new account ID is: 0.0.3992395
The private key is: 302e020100300506032b657004220420a101c245eff31d9bea028b0bc9f35eb8552073a4f11bd8421ff5b38378123cde
The new account balance is: 10 ℏ


Minted NFT 0.0.3992444 with serial number: [4]
Tranaction time: 1.345 seconds


     */
    Task myWorker;

    Font fnt = new Font("Lucida Sans Unicode", 24);
    Text header = new Text("  Transfer NFT transaction");
    AccountInfo accInfo;
    TextField tfTokenId = new TextField(); // .setTokenName("diploma")
    TextField tfTreasuryId = new TextField();
    TextField tfTreasuryKey = new TextField();
    TextField tfAliceAccountId = new TextField();
  
    Button submit = new Button("Transfer a token");
    final HBox hb_taskProgress = new HBox();
    //  TextArea fileContentsTa = new TextArea();
    TextArea notification = new TextArea();
    final ProgressIndicator progressIndicator = new ProgressIndicator(0);

    public TransferNFTTransaction() {
        super(10);
        setMaxHeight(Double.MAX_VALUE);
        accInfo = new AccountInfo();

        VBox mint = transfer();

        Region spacer_over_header = new Region();
        spacer_over_header.setPrefHeight(15);
        getChildren().add(spacer_over_header);

        header.setFont(fnt);
        getChildren().add(header);
        getChildren().add(accInfo);
        getChildren().add(mint);

        /*
        TitledPane fileContentsTp = new TitledPane();
        fileContentsTp.setText("File contents");
         fileContentsTp.setContent(fileContentsTa);
         
        
         getChildren().add(fileContentsTp);
         */
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

    public VBox transfer() {

        HBox h = new HBox();
        GridPane grid_1 = new GridPane();
        grid_1.setAlignment(Pos.CENTER_LEFT);
        grid_1.setHgap(10);
        grid_1.setVgap(10);
        grid_1.setPadding(new Insets(5, 5, 5, 5));

        
        Font fnt14 = Font.font("Verdana", 14);

        Label lbTokenId = new Label("Token Id:");
        lbTokenId.setFont(fnt14);
        grid_1.add(lbTokenId, 0, 1);
        grid_1.add(tfTokenId, 1, 1);

        Label treasuryId = new Label("Treasury Id:");
        treasuryId.setId("treasuryId");
        treasuryId.setFont(fnt14);
        grid_1.add(treasuryId, 0, 2);
        grid_1.add(tfTreasuryId, 1, 2);

       Label lbTreasuryKey = new Label("Treasury Key:");
        lbTreasuryKey.setId("treasuryKey");
        lbTreasuryKey.setFont(fnt14);
        grid_1.add(lbTreasuryKey, 0, 3);
        grid_1.add(tfTreasuryKey, 1, 3);
        

        Label lbAliceAccountId = new Label("Alice account id:");
        lbAliceAccountId.setId("aliceAccountId");
        lbAliceAccountId.setFont(fnt14);
        grid_1.add(lbAliceAccountId, 0, 4);
        grid_1.add(tfAliceAccountId, 1, 4);

       

       

        

        HBox hb = new HBox(0);
        hb.getChildren().addAll(grid_1);

        VBox vb = new VBox();

        vb.getChildren().addAll(hb);

        // vb.setPadding(new Insets(5));
        return vb;

    }

    public Task<String> createTask() throws Exception {
        return new Task<String>() {
            /*
             public String createToken(String network, String hederaAccount, String hederaPrivateKey,
            String tokenName,
            String tokenSymbol,
            String tokenType,
            String decimals,
            String initialSupply,
            String tresaryId,
            String supplyType,
            String maxSupply,
            String supplyKey,
            String treasuryKey 
             */

            @Override
            protected String call() throws Exception {
             /*
                String a = new MintNFT().mintAToken(accInfo.networkChoiceBox.getValue().toString(), accInfo.tfHederaAccount.getText(), accInfo.tfPrivateKey.getText(),
                        tfTokenId.getText(),
                        tfTreasuryId.getText(),
                        tfTreasuryKey.getText()
                        
                );

                   return a;
                
               
              */
             return "";
            }

        };

    }
}
