import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;

import java.util.ListIterator;

public class CityMenuController extends Main {

    private static ListIterator<String> cityName = cities.listIterator();

    @FXML private Label townLbl;
    @FXML private Label bountyLbl;

    @FXML
    private void store(){

        AlertWindow.setTitle("Store");
        store.storeMethod();
        AlertWindow.setScene(storeScene);
    }

    @FXML
    private void sell(){

        AlertWindow.setTitle("Sell");
        store.storeMethod();
        AlertWindow.setScene(new Scene(midSellStorePane));
    }

    @FXML
    private void keepGoing(){

        Distance -= 25;
        AlertWindow.close();
    }

    @FXML
    private void claim(){

        if (capturedThieves > 0){

            bountyMethod();
        }else{

            bountyLbl.setText("Sorry... you haven't caught anyone ( NO BOUNTY 4 U !! )");
        }
    }

    @FXML
    private void dealerShip(){

        AlertWindow.setTitle("Dealer Ship");
        AlertWindow.setScene(dealerScene);
    }

    @FXML
    private void setHireBtn(){

        AlertWindow.setScene(hireScene);
    }

    @FXML
    private void initialize(){

        String cityNameLOCAL;

        if (!cityName.hasNext()) cityNameLOCAL = cityName.previous();
        else cityNameLOCAL = cityName.next();

        AlertWindow.setTitle("City");
        townLbl.setText("You have come up to "+ cityNameLOCAL);
    }

    protected void bountyMethod(){

        int MoneyToClaim;

        MoneyToClaim = rand.nextInt(5000)+1000;
        MoneyToClaim *= capturedThieves;

        Score += 50000;

        bountyLbl.setText("You have Claimed: $"+MoneyToClaim);

        Money+=MoneyToClaim;

        capturedThieves = 0;
    }
}