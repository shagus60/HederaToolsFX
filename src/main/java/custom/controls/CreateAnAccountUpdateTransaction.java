/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.controls;

import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author shakir.gusaroff
 */
public class CreateAnAccountUpdateTransaction  extends VBox{
     Font fnt = new Font("Lucida Sans Unicode", 24);
    Text header = new Text("Create an account update transaction");
    AccountInfo  accInfo = new AccountInfo();
    
    public CreateAnAccountUpdateTransaction(){
        super(10);
        
         Region spacer_over_header = new Region();
        spacer_over_header.setPrefHeight(15);
         getChildren().add(spacer_over_header);
        header.setFont(fnt);
        getChildren().add(header);
        getChildren().add(accInfo);
    }
    
    
}
