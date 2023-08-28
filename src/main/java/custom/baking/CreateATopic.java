/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.baking;

import com.hedera.hashgraph.sdk.FileCreateTransaction;
import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.Hbar;
import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.hedera.hashgraph.sdk.PublicKey;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.hedera.hashgraph.sdk.FileId;
import com.hedera.hashgraph.sdk.ReceiptStatusException;
import com.hedera.hashgraph.sdk.TransactionResponse;

import com.hedera.hashgraph.sdk.TopicMessageQuery;

import java.util.concurrent.TimeoutException;

import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.FileCreateTransaction;
import com.hedera.hashgraph.sdk.TopicId;
import com.hedera.hashgraph.sdk.Hbar;
import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.hedera.hashgraph.sdk.ReceiptStatusException;
import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.hedera.hashgraph.sdk.TransactionResponse;
import com.hedera.hashgraph.sdk.TopicCreateTransaction;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;



/**
 *
 * @author shakir.gusaroff
 */
public class CreateATopic {
    /*
    A new file created successfully
Tranaction time: 2.244 seconds
The new file Id: 0.0.49198581
content "abc_gus"
    */
    

    public String createATopic(String network, String hederaAccount, String hederaPrivateKey) {
        //Grab your Hedera testnet account ID and private key
        StringBuilder sb = new StringBuilder();
        try {

            long start = System.currentTimeMillis();
            AccountId myAccountId = AccountId.fromString(hederaAccount);

            PrivateKey myPrivateKey = PrivateKey.fromString(hederaPrivateKey);
            //Create your Hedera testnet client
            Client client;
            if (network.equals("TESTNET")) {
                client = Client.forTestnet();
            } else {
                client = Client.forMainnet();

            }
            client.setOperator(myAccountId, myPrivateKey);
            // Generate a new key pair

            TransactionResponse transactionResponse = new TopicCreateTransaction()
                    // Use the same key as the operator to "own" this file
                    //.setKeys(myPrivateKey.getPublicKey())
                   .freezeWith(client)   
                    .execute(client);

            TransactionReceipt receipt = transactionResponse.getReceipt(client);
            TopicId newTopicId = receipt.topicId;

            System.out.println("file: " + newTopicId);

            long end = System.currentTimeMillis();
            //finding the time difference and converting it into seconds
            float sec = (end - start) / 1000F;

            sb.append("A new topic created successfully" + "\n");
            sb.append("Tranaction time: " + sec + " seconds" + "\n");
            sb.append("The new topic Id: " +  newTopicId + "\n");
            

            
            sb.append("\n");
            
            
            Thread.sleep(1000);
            
            
            //Subscribe to the topic
new TopicMessageQuery()
    .setTopicId(newTopicId)
    .subscribe(client, resp -> {
            String messageAsString = new String(resp.contents, StandardCharsets.UTF_8);
            System.out.println(resp.consensusTimestamp + " received topic message: " + messageAsString);
    });
            
            
            
            
            return sb.toString();

        } catch (TimeoutException ex) {
            sb.append(ex.getMessage());
            return sb.toString();
            //  Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrecheckStatusException ex) {
            //  Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            sb.append(ex.getMessage());
            return sb.toString();

        } catch (ReceiptStatusException ex) {
            //  Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            sb.append(ex.getMessage());
            return sb.toString();
        } catch (Exception ex) {
            // Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            sb.append(ex.getMessage());
            return sb.toString();
            //textAreaResult.setText(sb.toString());

        }

    }

}
