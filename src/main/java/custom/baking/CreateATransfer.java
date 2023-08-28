/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom.baking;

import com.hedera.hashgraph.sdk.AccountBalance;
import com.hedera.hashgraph.sdk.AccountBalanceQuery;
import com.hedera.hashgraph.sdk.AccountCreateTransaction;
import com.hedera.hashgraph.sdk.AccountId;
import com.hedera.hashgraph.sdk.Client;
import com.hedera.hashgraph.sdk.Hbar;


import com.hedera.hashgraph.sdk.PrecheckStatusException;
import com.hedera.hashgraph.sdk.PrivateKey;
import com.hedera.hashgraph.sdk.PublicKey;
import com.hedera.hashgraph.sdk.ReceiptStatusException;
import com.hedera.hashgraph.sdk.TransactionResponse;
import com.hedera.hashgraph.sdk.TransferTransaction;
import java.util.concurrent.TimeoutException;

/**
 *
 * @author shakir.gusaroff
 */
public class CreateATransfer {

    public String createATransfer(String network, String hederaAccount, String hederaPrivateKey, String receiverAccount, String transferAmonmtStr, String memo) {
        //Grab your Hedera testnet account ID and private key
        StringBuilder sb = new StringBuilder();
        try {

            long start = System.currentTimeMillis();
            AccountId myAccountId = AccountId.fromString(hederaAccount);
            AccountId receiverAccountId = AccountId.fromString(receiverAccount);

            PrivateKey myPrivateKey = PrivateKey.fromString(hederaPrivateKey);
            //Create your Hedera testnet client
            Client client;
            if (network.equals("TESTNET")) {
                client = Client.forTestnet();
            } else {
                client = Client.forMainnet();

            }
            client.setOperator(myAccountId, myPrivateKey);

            long transferAmonmt = Long.parseLong(transferAmonmtStr);
           
            
            TransactionResponse sendHbar = new TransferTransaction()
                    .addHbarTransfer(myAccountId, Hbar.from(0 - transferAmonmt)) //Sending account
                    .addHbarTransfer(receiverAccountId, Hbar.from(transferAmonmt)) //Receiving account
                    .setTransactionMemo(memo)
                    .execute(client);
            
            
            /*
             TransactionResponse sendHbar = new TransferTransaction()
                    .addHbarTransfer(myAccountId, Hbar.fromTinybars(0 - transferAmonmt)) //Sending account
                    .addHbarTransfer(receiverAccountId, Hbar.fromTinybars(transferAmonmt)) //Receiving account
                    .execute(client);
*/
             sb.append("The transfer transaction was: " + sendHbar.getReceipt(client).status + "\n");
            long end = System.currentTimeMillis();
            //finding the time difference and converting it into seconds
            float sec = (end - start) / 1000;
          

            sb.append("Tranaction time: " + sec + " seconds" + "\n");

            return sb.toString();

            
        } catch (NumberFormatException ex) {
            
             sb.append(ex.toString());
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
