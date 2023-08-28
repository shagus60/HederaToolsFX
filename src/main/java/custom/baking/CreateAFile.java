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
import java.util.concurrent.TimeoutException;



/**
 *
 * @author shakir.gusaroff
 */
public class CreateAFile {
    /*
    A new file created successfully
Tranaction time: 2.244 seconds
The new file Id: 0.0.49198581
content "abc_gus"
    */
    

    public String createAFile(String network, String hederaAccount, String hederaPrivateKey, String fileContents) {
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

            TransactionResponse transactionResponse = new FileCreateTransaction()
                    // Use the same key as the operator to "own" this file
                    .setKeys(myPrivateKey.getPublicKey())
                    .setContents(fileContents)
                    // The default max fee of 1 HBAR is not enough to make a file ( starts around 1.1 HBAR )
                    .setMaxTransactionFee(new Hbar(2)) // 2 HBAR
                    .execute(client);

            TransactionReceipt receipt = transactionResponse.getReceipt(client);
            FileId newFileId = receipt.fileId;

            System.out.println("file: " + newFileId);

            long end = System.currentTimeMillis();
            //finding the time difference and converting it into seconds
            float sec = (end - start) / 1000F;

            sb.append("A new file created successfully" + "\n");
            sb.append("Tranaction time: " + sec + " seconds" + "\n");
            sb.append("The new file Id: " +  newFileId + "\n");
            

            
            sb.append("\n");
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
