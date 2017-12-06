package com.TheRedSpy15.Trail;

/*

   Copyright [2017] [TheRedSpy15]

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

 */

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

        getAlertWindow().setTitle("Store");
        store.storeMethod();
        getAlertWindow().setScene(getStoreScene());
    }

    @FXML
    private void sell(){

        getAlertWindow().setTitle("Sell");
        store.storeMethod();
        getAlertWindow().setScene(new Scene(midSellStorePane));
    }

    @FXML
    private void keepGoing(){

        setDistance(getDistance() - 25);
        getAlertWindow().close();
    }

    @FXML
    private void claim(){

        if (getCapturedThieves() > 0){

            bountyMethod();
        }else{

            bountyLbl.setText("Sorry... you haven't caught anyone ( NO BOUNTY 4 U !! )");
        }
    }

    @FXML
    private void dealerShip(){

        getAlertWindow().setTitle("Dealer Ship");
        getAlertWindow().setScene(dealerScene);
    }

    @FXML
    private void setHireBtn(){

        getAlertWindow().setScene(hireScene);
    }

    @FXML
    private void initialize(){

        String cityNameLOCAL;

        if (!cityName.hasNext()) cityNameLOCAL = cityName.previous();
        else cityNameLOCAL = cityName.next();

        getAlertWindow().setTitle("City");
        townLbl.setText("You have come up to "+ cityNameLOCAL);
    }

    protected void bountyMethod(){

        int MoneyToClaim;

        MoneyToClaim = rand.nextInt(5000)+1000;
        MoneyToClaim *= getCapturedThieves();

        setScore(getScore() + 50000);

        bountyLbl.setText("You have Claimed: $"+MoneyToClaim);

        setMoney(getMoney() + MoneyToClaim);

        setCapturedThieves(0);
    }
}
