/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.baking;

import com.hedera.hashgraph.sdk.AccountInfo;
import com.hedera.hashgraph.sdk.AccountBalanceQuery;
import com.hedera.hashgraph.sdk.AccountUpdateTransaction;
import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.AccountInfoQuery;

import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.Hbar;
import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.hedera.hashgraph.sdk.PublicKey;
import com.hedera.hashgraph.sdk.ReceiptStatusException;
import com.hedera.hashgraph.sdk.TransactionResponse;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author shakir.gusaroff
 */
public class UpdateAnAccountStaking {
/*
    03/20/2023
    A new account created successfully
Tranaction time: 3.584 seconds
The new account ID is: 0.0.3880459
The private key is: 302e020100300506032b657004220420a701acea6bebfbb0b6a5a3f280d4980b6cd87e58c3f3604dc0b66d1d0ae109b4
The new account balance is: 10 ℏ


    */
  
    //  public static String createAccount(String network, String hederaAccount, String hederaPrivateKey) throws TimeoutException, ReceiptStatusException, PrecheckStatusException 

    public  String updateAccountStaking(String network, String hederaAccount, String hederaPrivateKey, long nodeId) {
        //Grab your Hedera testnet account ID and private key
        StringBuilder sb = new StringBuilder();
        try {
             if (nodeId < 0) {
                throw new Exception("Invalid node Id ");
             
             
             }
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
            
            TransactionResponse response = new AccountUpdateTransaction()
                    .setAccountId(myAccountId)
                   // .setStakedNodeId(Long.parseLong(nodeId))
                       .setStakedNodeId(nodeId)
                    .freezeWith(client)
                    .sign(myPrivateKey)                    
                    .execute(client);
            // Get the new account ID
           
            AccountInfo info = new AccountInfoQuery()
                    .setAccountId(myAccountId)
                    .execute(client);
           

            long end = System.currentTimeMillis();
            //finding the time difference and converting it into seconds
            float sec = (end - start) / 1000F;

            sb.append("Account updated  successfully" + "\n");
            sb.append("Tranaction time: " + sec + " seconds" + "\n");
            sb.append("Staking info: Staked node Id is " +  nodeId + "\n");
             sb.append("Balance info: " + info.balance + "\n");
           

            sb.append("\n");
            sb.append("\n");
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
/*
        } catch (ReceiptStatusException ex) {
            //  Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            sb.append(ex.getMessage());
            return sb.toString();
            */
        } catch (Exception ex) {
            // Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            sb.append(ex.getMessage());
            return sb.toString();
            //textAreaResult.setText(sb.toString());

        }

    }
}
