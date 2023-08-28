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
public class CreateNFT {

    //  public static String createAccount(String network, String hederaAccount, String hederaPrivateKey) throws TimeoutException, ReceiptStatusException, PrecheckStatusException 
    public String createAToken(String network, String hederaAccount, String hederaPrivateKey,
            String tokenName,
            String tokenSymbol,
            String tokenType,
            String decimals,
            String initialSupply,
            String tresaryId,
            String supplyType,
            String maxSupply,
            String supplyKey,
            String treasuryKey 
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

            TokenCreateTransaction nftCreate = new TokenCreateTransaction()
                    .setTokenName(tokenName)
                    .setTokenSymbol(tokenSymbol)
                    .setTokenType(TokenType.valueOf(tokenType))
                    .setDecimals(Integer.parseInt(decimals))
                    .setInitialSupply(Integer.parseInt(initialSupply))
                    .setTreasuryAccountId(AccountId.fromString(tresaryId))
                    .setSupplyType(TokenSupplyType.valueOf(supplyType))
                    .setMaxSupply(Long.parseLong(maxSupply))
                      .setSupplyKey(PrivateKey.fromString(supplyKey))
                    .freezeWith(client);
            // Get the new account ID
           
            // System.out.println("The new account balance is: " );
            //Sign the transaction with the treasury key
            //  TokenCreateTransaction nftCreateTxSign = nftCreate.sign(treasuryKey);    //myPrivateKey
            TokenCreateTransaction nftCreateTxSign = nftCreate.sign(PrivateKey.fromString(treasuryKey));    //myPrivateKey
            //Submit the transaction to a Hedera network
            TransactionResponse nftCreateSubmit = nftCreateTxSign.execute(client);

            //Get the transaction receipt
            TransactionReceipt nftCreateRx = nftCreateSubmit.getReceipt(client);

            //Get the token ID
            TokenId tokenId = nftCreateRx.tokenId;

            //Log the token ID
            long end = System.currentTimeMillis();
            //finding the time difference and converting it into seconds
            float sec = (end - start) / 1000F;

            sb.append("Created token  with token ID " + tokenId + "\n");
            sb.append("Tranaction time: " + sec + " seconds" + "\n");
            //// sb.append("The new account ID is: " + newAccountId + "\n");
            //sb.append("The private key is: " + newAccountPrivateKey + "\n");
            //sb.append("The new account balance is: " + accountBalance.hbars + "\n");

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
