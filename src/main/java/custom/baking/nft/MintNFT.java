/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.baking.nft;

import com.hedera.hashgraph.sdk.AccountBalance;
import com.hedera.hashgraph.sdk.AccountBalanceQuery;
import com.hedera.hashgraph.sdk.TokenCreateTransaction;
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
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;

/**
 *
 * @author shakir.gusaroff
 */
//https://docs.hedera.com/hedera/sdks-and-apis/hedera-api/token-service/tokencreate
//https://docs.hedera.com/hedera/sdks-and-apis/sdks/tokens/mint-a-token
public class MintNFT {

    public String mintAToken(String network, String hederaAccount, String hederaPrivateKey,
            String tokenId,
            String metadata,
            String supplyKey
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

            // Mint a new NFT
            TokenMintTransaction mintTx = new TokenMintTransaction()
                    .setTokenId(TokenId.fromString(tokenId))
                    .addMetadata(metadata.getBytes())
                    .freezeWith(client);

            //Sign transaction with the supply key
            TokenMintTransaction mintTxSign = mintTx.sign(PrivateKey.fromString(hederaPrivateKey));
         
            //Submit the transaction to a Hedera network
           TransactionResponse mintTxSubmit = mintTxSign.execute(client);
            
           //Get the transaction receipt
           TransactionReceipt mintRx = mintTxSubmit.getReceipt(client);
           
           
           //Log the serial number
           System.out.println("Minted NFT " +tokenId + "with serial: " +mintRx.serials);
           
           
          
            long end = System.currentTimeMillis();
            //finding the time difference and converting it into seconds
            float sec = (end - start) / 1000F;

            sb.append("Minted NFT " + tokenId + " with serial number: " +mintRx.serials+ "\n");
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
