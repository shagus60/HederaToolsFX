/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.controls.nft;

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
public class CreateNFTTransaction extends VBox {

   //For  NFT, you must set the initial supply to 0.
   //for nft decimals set 0 
    /*
    Minting fungible token allows you to increase the total supply of the token.
    Minting a non-fungible token creates an NFT with its unique metadata for the class of NFTs defined by the token ID. 
    
    The Supply Key must sign the transaction.
    */
    /*
   
    
    
    Tresary key and account id
 A new account created successfully
Tranaction time: 5.144 seconds
The new account ID is: 0.0.4038374
The private key is: 302e020100300506032b6570042204208057bd22b336398f4fcd61a1f4d9c7365815a58089e8368d30411a48f9cdc8b0
The new account balance is: 10 ℏ

    
    Created NFT  with token ID 0.0.4038479
Tranaction time: 1.557 seconds




     */
    Task myWorker;

    Font fnt = new Font("Lucida Sans Unicode", 24);
    Text header = new Text("  Create NFT transaction");
    AccountInfo accInfo;
    TextField tfTokenName = new TextField(); // .setTokenName("diploma")
    TextField tfTokenSymbol = new TextField();
    //final ChoiceBox cbTokenType = new ChoiceBox(FXCollections.observableArrayList("NON_FUNGIBLE_UNIQUE", "FUNGIBLE"));
    final ChoiceBox cbTokenType = new ChoiceBox(FXCollections.observableArrayList("NON_FUNGIBLE_UNIQUE"));
    TextField tfDecimal = new TextField();  //.setDecimals(0)
    TextField tfInitialSupply = new TextField();  //setInitialSupply(0)
    TextField tfTreasuryAccountId = new TextField();  // .setTreasuryAccountId(treasuryId)
    final ChoiceBox cbSupplyType = new ChoiceBox(FXCollections.observableArrayList("FINITE"));    //  .setSupplyType(TokenSupplyType.FINITE)
    TextField tfMaxSupply = new TextField();  //.setMaxSupply(250)
    TextField tfSupplyKey = new TextField();  //.setSupplyKey(supplyKey)
    TextField tfTreasuryKey = new TextField();  //.treasuryKey)
    Button buttonCreateAFile = new Button("Create a token");
    final HBox hb_taskProgress = new HBox();
    //  TextArea fileContentsTa = new TextArea();
    TextArea notification = new TextArea();
    final ProgressIndicator progressIndicator = new ProgressIndicator(0);

    public CreateNFTTransaction() {
        super(10);
        setMaxHeight(Double.MAX_VALUE);
        accInfo = new AccountInfo();

        VBox nftInfo = nftInfo();

        Region spacer_over_header = new Region();
        spacer_over_header.setPrefHeight(15);
        getChildren().add(spacer_over_header);

        header.setFont(fnt);
        getChildren().add(header);
        getChildren().add(accInfo);
        getChildren().add(nftInfo);

        /*
        TitledPane fileContentsTp = new TitledPane();
        fileContentsTp.setText("File contents");
         fileContentsTp.setContent(fileContentsTa);
         
        
         getChildren().add(fileContentsTp);
         */
        getChildren().add(buttonCreateAFile);
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

        buttonCreateAFile.setOnAction((ActionEvent event) -> {

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

    public VBox nftInfo() {

        HBox h = new HBox();
        GridPane grid_1 = new GridPane();
        grid_1.setAlignment(Pos.CENTER_LEFT);
        grid_1.setHgap(10);
        grid_1.setVgap(10);
        grid_1.setPadding(new Insets(5, 5, 5, 5));

        GridPane grid_2 = new GridPane();
        grid_2.setAlignment(Pos.CENTER_LEFT);
        grid_2.setHgap(10);
        grid_2.setVgap(10);
        grid_2.setPadding(new Insets(5, 5, 5, 5));

        Font fnt14 = Font.font("Verdana", 14);

        Label lbTokenName = new Label("Token name:");
        lbTokenName.setFont(fnt14);
        grid_1.add(lbTokenName, 0, 1);
        grid_1.add(tfTokenName, 1, 1);

        Label lbTokenSymbol = new Label("Token symbol:");
        lbTokenSymbol.setId("tokenSymbol");
        lbTokenSymbol.setFont(fnt14);
        grid_1.add(lbTokenSymbol, 0, 2);
        grid_1.add(tfTokenSymbol, 1, 2);

        Label lbTokenType = new Label("Token type:");
        lbTokenType.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid_1.add(lbTokenType, 0, 3);
        grid_1.add(cbTokenType, 1, 3);
        cbTokenType.getSelectionModel().selectFirst();

        Label lbDecimals = new Label("Decimals:");
        lbDecimals.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid_1.add(lbDecimals, 0, 4);
        grid_1.add(tfDecimal, 1, 4);
        tfDecimal.setText("0");

        Label lbInitialSupply = new Label("Initial Supply:");
        lbInitialSupply.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid_1.add(lbInitialSupply, 0, 5);
        grid_1.add(tfInitialSupply, 1, 5);
        tfInitialSupply.setText("0");

        Label lbTreasuryAccountId = new Label("Treasury Account Id:");
        lbTreasuryAccountId.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid_2.add(lbTreasuryAccountId, 0, 1);
        grid_2.add(tfTreasuryAccountId, 1, 1);

        Label lbSupplyType = new Label("Supply Type:");
        lbSupplyType.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid_2.add(lbSupplyType, 0, 2);
        grid_2.add(cbSupplyType, 1, 2);

        Label lbMaxSupply = new Label("Max Supply:");
        lbMaxSupply.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid_2.add(lbMaxSupply, 0, 3);
        grid_2.add(tfMaxSupply, 1, 3);

        Label lbSupplyKey = new Label("Supply Key:");
        lbSupplyKey.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid_2.add(lbSupplyKey, 0, 4);
        grid_2.add(tfSupplyKey, 1, 4);

        Label lbtreasuryKey = new Label("Treasury Key:");
        lbtreasuryKey.setFont(javafx.scene.text.Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid_2.add(lbtreasuryKey, 0, 5);
        grid_2.add(tfTreasuryKey, 1, 5);

        HBox hb = new HBox(0);
        hb.getChildren().addAll(grid_1, grid_2);

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
                String a = new CreateNFT().createAToken(accInfo.networkChoiceBox.getValue().toString(), accInfo.tfHederaAccount.getText(), accInfo.tfPrivateKey.getText(),
                        tfTokenName.getText(),
                        tfTokenSymbol.getText(),
                        cbTokenType.getValue().toString(),
                        tfDecimal.getText(),
                        tfInitialSupply.getText(),
                        tfTreasuryAccountId.getText(),
                        cbSupplyType.getValue().toString(),
                        tfMaxSupply.getText(),
                        tfSupplyKey.getText(),
                        tfTreasuryKey.getText()
                );

                   return a;
                
            }

        };

    }
}
