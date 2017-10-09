import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

public class SettlementMenuController extends Main {

    @FXML private Label townLbl;
    @FXML private static Label bountyLbl;

    @FXML
    private void store(){

        store.storeMethod();
        AlertWindow.setScene(new Scene(midStorePane));
    }

    @FXML
    private void sell(){

        AlertWindow.setScene(new Scene(midSellStorePane));
    }

    @FXML
    private void keepGoing(){

        Distance -= 25;
        AlertWindow.close();
    }

    @FXML
    private void claim(){

        if (TurnInThief){

            bountyMethod();
        }else{

            bountyLbl.setText("Sorry... you haven't caught anyone ( NO BOUNTY 4 U !! )");
        }
    }

    @FXML
    private void initialize(){

        AlertWindow.setTitle("Settlement");
        townLbl.setText("You have come up to the town of "+TownList[TownSelector]);
    }

    protected static void bountyMethod(){

        int MoneyToClaim;

        MoneyToClaim = rand.nextInt(1000)+500;

        bountyLbl.setText("You have Claimed: $"+MoneyToClaim);

        Money+=MoneyToClaim;

        TurnInThief = false;
    }
}
