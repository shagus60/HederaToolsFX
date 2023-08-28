/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.baking.nft;

import com.hedera.hashgraph.sdk.AccountBalance;
import com.hedera.hashgraph.sdk.AccountBalanceQuery;
import com.hedera.hashgraph.sdk.TokenCreateTransaction;
import com.hedera.hashgraph.sdk.TokenAssociateTransaction;
import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.Hbar;
import com.hedera.hashgraph.sdk.Key;
import com.hedera.hashgraph.sdk.Status;

import com.hedera.hashgraph.sdk.TokenMintTransaction;

import com.hedera.hashgraph.sdk.TransactionReceipt;
import com.hedera.hashgraph.sdk.TokenId;
import com.hedera.hashgraph.sdk.TokenType;
import com.hedera.hashgraph.sdk.TokenSupplyType;
import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.hedera.hashgraph.sdk.PublicKey;
import com.hedera.hashgraph.sdk.ReceiptStatusException;
import com.hedera.hashgraph.sdk.TransactionResponse;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author shakir.gusaroff
 */
public class AssociateUserAccountsWithTheNFT {

    public String associateUserAccountsWithTheNFT(String network, String hederaAccount, String hederaPrivateKey,
            String tokenId,
            String aliceAccountId,
            String aliceKey
    ) {
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

            //Create the associate transaction and sign with Alice's key 
            // Associate a token to an account 
            TokenAssociateTransaction transaction = new TokenAssociateTransaction()
                    .setAccountId(AccountId.fromString(aliceAccountId))
                    .setTokenIds(Collections.singletonList(TokenId.fromString(tokenId)));

            //Freeze the unsigned transaction, sign with the private key of the account that is being associated to a token, submit the transaction to a Hedera network
            TransactionResponse txResponse = transaction.freezeWith(client).sign(PrivateKey.fromPem(aliceKey)).execute(client);
            //Request the receipt of the transaction
            TransactionReceipt receipt = txResponse.getReceipt(client);
            //Get the transaction consensus status
            
            //Get the transaction consensus status
              Status transactionStatus = receipt.status;
            
            System.out.println("The transaction consensus status " +transactionStatus);
            long end = System.currentTimeMillis();
            //finding the time difference and converting it into seconds
            float sec = (end - start) / 1000F;

            sb.append("The transaction consensus status " +transactionStatus + "\n");
            sb.append("Tranaction time: " + sec + " seconds" + "\n");

            sb.append("\n");
            sb.append("\n");
            sb.append("\n");
            return sb.toString();

        } /*catch (TimeoutException ex) {
            sb.append(ex.getMessage());
            return sb.toString();
            //  Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PrecheckStatusException ex) {
            //  Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            sb.append(ex.getMessage());
            return sb.toString();

        } 

        catch (ReceiptStatusException ex) {
            //  Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            sb.append(ex.getMessage());
            return sb.toString();
        } 
        
         */ catch (Exception ex) {
            // Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            sb.append(ex.getMessage());
            return sb.toString();
            //textAreaResult.setText(sb.toString());

        }

    }
}
