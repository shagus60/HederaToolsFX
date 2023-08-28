/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.controls;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 *
 * @author shakir.gusaroff
 */
public class AccountInfo extends TitledPane {

  public  GridPane grid = new GridPane();
  public   final ChoiceBox networkChoiceBox = new ChoiceBox(FXCollections.observableArrayList("TESTNET", "MAINNET"));
  
  
 
  
  public  TextField tfHederaAccount = new TextField();
  public TextField tfPrivateKey = new TextField();
  
  
    public AccountInfo() {
        tfHederaAccount.setText("");
        tfPrivateKey.setText("");
         Font fnt = Font.font("Verdana",  16);
         Label network = new Label("Network: ");
         network.setFont(fnt);
         Label acc = new Label("Account number: ");
         acc.setFont(fnt);
         Label key = new Label("Private Key: ");
         key.setFont(fnt);

        networkChoiceBox.getSelectionModel().selectFirst();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        grid.add(network, 0, 0);
        grid.add(networkChoiceBox, 1, 0);
        grid.add(acc, 0, 1);
        grid.add(tfHederaAccount, 1, 1);
        grid.add(key, 0, 2);
        grid.add(tfPrivateKey, 1, 2);
        setText("Payer account info");
        setContent(grid);
      //  System.out.println(networkChoiceBox.getValue().toString());
    }

}
