/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.controls;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import static javafx.scene.layout.BorderPane.setMargin;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
//import javafx.scene.text.TextLabel;

/**
 *
 * @author shakir.gusaroff
 */
public class TransactionsList extends VBox {
    // Font fntHeader = Font.font("Arial", 24);
     
     Font fntHeader = new Font("Lucida Sans Unicode", 24);
     
   
    Font fnt = Font.font("Arial", 22);
    //  Font fnt = new Font("Arial", FontWeight.BOLD, 18);
    Label accNew = new Label("Create a new account transaction");

    Label transfer = new Label("Create a transfer transaction");
    
   // Label sep = new Label("");
      Label header = new Label("Transactions");
    Label createFile = new Label("Create a file transaction");
    
    Label updateFile = new Label("Update a file transaction");

    Label deleteFile = new Label("Delete a file transaction");
    
     Label createTopic = new Label("Create a topic transaction");
     Label submitTopic = new Label("Submit a message to the topic");
      Label deleteTopic = new Label("Delete a topic transaction");
     
    ObservableList<Label> items = FXCollections.observableArrayList(
            accNew, transfer,   createFile, updateFile, deleteFile, createTopic,
            submitTopic, deleteTopic
    );
    
    ListView<Label>  transactions = new ListView<Label>();

    public TransactionsList() {
       super(3);
         header.setFont(fntHeader);
        accNew.setFont(fnt);
        //  accUpdate.setFont(fnt);
        transfer.setFont(fnt);
        createFile.setFont(fnt);
        
       // createFile.setTextFill(Color.web("#0076a3"));
        updateFile.setFont(fnt);
        deleteFile.setFont(fnt);
         createTopic.setFont(fnt); 
         submitTopic.setFont(fnt);
            deleteTopic.setFont(fnt);
         
        //
        transactions.setItems(items);
        setMinWidth(365);

        transactions.setCursor(Cursor.HAND);
       // Label header = new Label("Transactions");
        getChildren().add(header);
        getChildren().add( transactions);
        transactions.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Label>() {
            public void changed(ObservableValue<? extends Label> ov,
                    Label old_val, Label new_val) {
    
                BorderLayoutLandingView b = (BorderLayoutLandingView) getParent();
                
              //  setMargin(tr_list, new Insets(12, 12, 12, 12));
               // Tab tabPane = (Tab)getParent();
               //// TabPane tabPane = (TabPane)getParent();
               // BorderLayoutLandingView b = (BorderLayoutLandingView) tabPane.getParent(); 
                
                
                if (new_val.getText().equals("Create a transfer transaction")) {
                    CreateATransferTransaction c = new CreateATransferTransaction();
                  // b.setMargin(this, new Insets(10, 10, 10, 10));
                   // c.setMargin( new Insets(12, 12, 12, 12));
                    b.setCenter(c);

                } else if (new_val.getText().equals("Create a new account transaction")) {
                   // b.setCenter(new CreateANewAccountTransaction());
                     CreateANewAccountTransaction c = new CreateANewAccountTransaction();
                     // c.setMargin( new Insets(12, 12, 12, 12));
                    b.setCenter(c);

                } else if (new_val.getText().equals("Create a file transaction")) {
                   // b.setCenter(new CreateAFileTransaction());
                  //  b.setMargin(this, new Insets(10, 10, 10, 10));
                     CreateAFileTransaction c = new CreateAFileTransaction();
                     // c.setMargin( new Insets(12, 12, 12, 12));
                    b.setCenter(c);

                } else if (new_val.getText().equals("Update a file transaction")) {
                  //  b.setCenter(new UpdateAFileTransaction());
                   //b.setMargin(this, new Insets(10, 10, 10, 10));
                     UpdateAFileTransaction c = new UpdateAFileTransaction();
                    //  c.setMargin( new Insets(12, 12, 12, 12));
                    b.setCenter(c);

                }
                
                else if (new_val.getText().equals("Delete a file transaction")) {
                    b.setCenter(new DeleteAFileTransaction());

                }
                
                else if (new_val.getText().equals("Create a topic transaction")) {
                    b.setCenter(new CreateATopicTransaction());

                }
                
                else if (new_val.getText().equals("Submit a message to the topic")) {
                    b.setCenter(new SubmitATopicTransaction());
                    
                    

                }
                
                
                 else if (new_val.getText().equals("Delete a topic transaction")) {
                    b.setCenter(new DeleteATopicTransaction());

                }
                /* else if (new_val.getLabel().equals("Create an account update transaction")) {
                    b.setCenter(new CreateAnAccountUpdateTransaction());

                }*/
            }
        });

    }

    /*
  static class ColorRectCell extends ListCell<String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Rectangle rect = new Rectangle(100, 20);
            if (item != null) {
                rect.setFill(Color.web(item));
                setGraphic(rect);
            }
        }
    }
     */
}
