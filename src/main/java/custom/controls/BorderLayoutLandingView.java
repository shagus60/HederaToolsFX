/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.controls;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

/**
 *
 * @author shakir.gusaroff
 */
public class BorderLayoutLandingView extends BorderPane {

    Font fnt = new Font("Lucida Sans Unicode", 30);
    //TransactionsList tr_list = new TransactionsList();
      TransactionsTree tr_list = new TransactionsTree();
   // final TabPane tabPane = new TabPane();
    // final Tab transactionsTab = new Tab("Transactions");
    public BorderLayoutLandingView() {
       
     
        
        setId("BorderLayoutLandingView");
       setMargin(tr_list, new Insets(10, 10, 10, 10));
       
          setLeft(tr_list);
        tr_list.transactions.getSelectionModel().select(1);
        
       
         
        Label header = new Label("Hedera Transaction and Query Tool");

        header.setFont(fnt);
        setMargin(header, new Insets(12, 12, 6, 12));
        setTop(header);

        setRight(new Label(""));
        setBottom(new Label(""));
    }
}
