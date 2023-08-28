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
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import static javafx.scene.layout.BorderPane.setMargin;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import custom.controls.nft.*;
import javafx.application.Platform;
import javafx.event.EventType;
import javafx.scene.control.TreeCell;
import javafx.util.Callback;
//import javafx.scene.text.TextLabel;

/**
 *
 * @author shakir.gusaroff
 */
public class TransactionsTree extends VBox {

    Font fntHeader = new Font("Lucida Sans Unicode", 24);

    Font fnt = Font.font("Arial", 22);
    //  Font fnt = new Font("Arial", FontWeight.BOLD, 18);
    Label header = new Label("Transactions");
    Label account = new Label("Account");
    Label accNew = new Label("Create a new account transaction");
    Label transfer = new Label("Create a transfer transaction");
    Label updateStaking = new Label("Update an account (Staking)");

    // Label sep = new Label("");
    Label file = new Label("File");
    Label createFile = new Label("Create a file transaction");
    Label updateFile = new Label("Update a file transaction");
    Label deleteFile = new Label("Delete a file transaction");

    Label topic = new Label("Topic");
    Label createTopic = new Label("Create a topic transaction");
    Label submitTopic = new Label("Submit a message to the topic");
    Label deleteTopic = new Label("Delete a topic transaction");

    Label hts = new Label("Non-Fungible Token");
    Label htsNew = new Label("Create NFT transaction");
    Label htsMint = new Label("Mint NFT transaction");
    Label htsAssoisiate = new Label("Assosiate user account");
    Label htsTransfer = new Label("Transfer NFT transaction");


    /*
    ObservableList<Label> items = FXCollections.observableArrayList(
            accNew, transfer, createFile, updateFile, deleteFile, createTopic,
            submitTopic, deleteTopic
    );
     */
    //ListView<Label>  transactions = new ListView<Label>();
    TreeItem rootItem = new TreeItem("Transactions");

    TreeItem<Label> accountItem = new TreeItem<Label>(account);
    TreeItem<Label> itemaccNew = new TreeItem<Label>(accNew);
    TreeItem<Label> itemTransfer = new TreeItem<Label>(transfer);
    TreeItem<Label> itemUpdateStaking = new TreeItem<Label>(updateStaking);

    TreeItem<Label> fileItem = new TreeItem<Label>(file);
    TreeItem<Label> createFileItem = new TreeItem<Label>(createFile);
    TreeItem<Label> updateFileItem = new TreeItem<Label>(updateFile);
    TreeItem<Label> deleteFileItem = new TreeItem<Label>(deleteFile);

    TreeItem<Label> topicItem = new TreeItem<Label>(topic);
    TreeItem<Label> createTopicItem = new TreeItem<Label>(createTopic);
    TreeItem<Label> submitTopicItem = new TreeItem<Label>(submitTopic);
    TreeItem<Label> deleteTopicItem = new TreeItem<Label>(deleteTopic);

    TreeItem<Label> htsItem = new TreeItem<Label>(hts);
    TreeItem<Label> itemhtsNew = new TreeItem<Label>(htsNew);
    TreeItem<Label> itemhtsMint = new TreeItem<Label>(htsMint);
    TreeItem<Label> itemhtsAssoisiatet = new TreeItem<Label>(htsAssoisiate);
    TreeItem<Label> itemhtsTransfer = new TreeItem<Label>(htsTransfer);
    
    

    TreeView transactions = new TreeView(rootItem);

    public TransactionsTree() {
        super(3);

        /*
        transactions.setCellFactory(new Callback<TreeView<Label>, TreeCell<Label>>() {
        @Override 
        public TreeCell<Label> call(TreeView<Label> param) {
            return new TreeCell<Label>() {
                @Override 
                protected void updateItem(Label item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item != null && "File".equals(item.getText())) {
                          setText(item.getText());
                        setDisable(true);
                    } else {
                         //setText(item.getText());
                        setDisable(false);
                    }
                  
                }

            };
        }
    });
         */
        //transactions.setset..set.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        //transactions.setMinHeight(Double.MAX_VALUE);
        //  setBackground(Color.BEIGE);
        VBox.setVgrow(transactions, Priority.ALWAYS);
        header.setFont(fntHeader);
        account.setFont(fntHeader);
        file.setFont(fntHeader);
        topic.setFont(fntHeader);
        hts.setFont(fntHeader);

        accNew.setFont(fnt);
        //  accUpdate.setFont(fnt);
        transfer.setFont(fnt);
        updateStaking.setFont(fnt);

        createFile.setFont(fnt);
        updateFile.setFont(fnt);
        deleteFile.setFont(fnt);

        createTopic.setFont(fnt);
        submitTopic.setFont(fnt);
        deleteTopic.setFont(fnt);

        htsNew.setFont(fnt);
        htsMint.setFont(fnt);
        htsAssoisiate.setFont(fnt);
          htsTransfer.setFont(fnt);

        //
        rootItem.getChildren().add(accountItem);

        accountItem.getChildren().addAll(itemaccNew, itemTransfer, itemUpdateStaking);
        accountItem.setExpanded(true);

        rootItem.getChildren().add(fileItem);
        fileItem.getChildren().addAll(createFileItem, updateFileItem, deleteFileItem);
        fileItem.setExpanded(true);

        rootItem.getChildren().add(topicItem);
        topicItem.getChildren().addAll(createTopicItem, submitTopicItem, deleteTopicItem);
        topicItem.setExpanded(true);

        rootItem.getChildren().add(htsItem);
        htsItem.getChildren().addAll(itemhtsNew, itemhtsMint,itemhtsAssoisiatet, itemhtsTransfer);
        htsItem.setExpanded(true);

        setMinWidth(380);

        transactions.setCursor(Cursor.HAND);
        transactions.setShowRoot(false);
        // Label header = new Label("Transactions");
        getChildren().add(header);

        getChildren().add(transactions);

        transactions.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<TreeItem>() {
            public void changed(ObservableValue<? extends TreeItem> ov,
                    TreeItem old_val, TreeItem new_val) {
                if (new_val != null) {
                    BorderLayoutLandingView b = (BorderLayoutLandingView) getParent();
                    System.out.println("The selected index is " + transactions.getSelectionModel().getSelectedIndex());
                    System.out.println("The selected index is " + transactions.getSelectionModel().getSelectedItem());
                    //   Label selected = ((Label)new_val.getValue()).getText();
                    System.out.println(new_val.getValue());
                    String itemText = ((Label) new_val.getValue()).getText();
                    // System.out.println("Selected index" + );
                    //  setMargin(tr_list, new Insets(12, 12, 12, 12));
                    // Tab tabPane = (Tab)getParent();
                    //// TabPane tabPane = (TabPane)getParent();
                    // BorderLayoutLandingView b = (BorderLayoutLandingView) tabPane.getParent(); 

                    if (itemText.equals("Create a transfer transaction")) {
                        CreateATransferTransaction c = new CreateATransferTransaction();
                        //       System.out.println("The parrent is " + b);
                        // System.out.println(new_val);
                        // b.setMargin(this, new Insets(10, 10, 10, 10));
                        // c.setMargin( new Insets(12, 12, 12, 12));
                        b.setCenter(c);

                    } else if (itemText.equals("Update an account (Staking)")) {
                        // b.setCenter(new CreateANewAccountTransaction());
                        UpdateAnAccountStakingTransaction c = new UpdateAnAccountStakingTransaction();
                        // c.setMargin( new Insets(12, 12, 12, 12));
                        b.setCenter(c);

                    } else if (itemText.equals("Create a new account transaction")) {
                        // b.setCenter(new CreateANewAccountTransaction());
                        CreateANewAccountTransaction c = new CreateANewAccountTransaction();
                        // c.setMargin( new Insets(12, 12, 12, 12));
                        b.setCenter(c);

                    } else if (itemText.equals("Create a file transaction")) {
                        // b.setCenter(new CreateAFileTransaction());
                        //  b.setMargin(this, new Insets(10, 10, 10, 10));
                        CreateAFileTransaction c = new CreateAFileTransaction();
                        // c.setMargin( new Insets(12, 12, 12, 12));
                        b.setCenter(c);

                    } else if (itemText.equals("Update a file transaction")) {
                        //  b.setCenter(new UpdateAFileTransaction());
                        //b.setMargin(this, new Insets(10, 10, 10, 10));
                        UpdateAFileTransaction c = new UpdateAFileTransaction();
                        //  c.setMargin( new Insets(12, 12, 12, 12));
                        b.setCenter(c);

                    } else if (itemText.equals("Delete a file transaction")) {
                        b.setCenter(new DeleteAFileTransaction());

                    } else if (itemText.equals("Create a topic transaction")) {
                        b.setCenter(new CreateATopicTransaction());

                    } else if (itemText.equals("Submit a message to the topic")) {
                        b.setCenter(new SubmitATopicTransaction());

                    } else if (itemText.equals("Delete a topic transaction")) {
                        b.setCenter(new DeleteATopicTransaction());

                    } else if (itemText.equals("Create NFT transaction")) {
                        b.setCenter(new CreateNFTTransaction());

                    } else if (itemText.equals("Mint NFT transaction")) {
                        b.setCenter(new MintNFTTransaction());
                    } // Associate User Accounts with the NFT
                    else if (itemText.equals("Assosiate user account")) {
                        b.setCenter(new AssociateUserAccountsWithTheNFTTransaction());

                    }
                    
                     else if (itemText.equals("Transfer NFT transaction")) {
                        b.setCenter(new TransferNFTTransaction());

                    } 
                   
                    
                    
                    else //Select only leaf nodes
                        
                        
                        
                        
                    {
                        Platform.runLater(() -> transactions.getSelectionModel().select(-1));
                    }
                }
                //transactions.getSelectionModel().clearSelection();
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
