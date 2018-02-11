package com.TheRedSpy15.trail;

/*

   Copyright 2018 TheRedSpy15

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
import javafx.scene.control.Label;

import java.util.ListIterator;

public class CityMenuController {

    private static ListIterator<String> cityName = Main.cities.listIterator();

    @FXML private Label townLbl, bountyLbl;

    @FXML
    private void store(){

        Main.getAlertWindow().setTitle("Store");
        Main.store.updateStores();
        Main.getAlertWindow().setScene(Main.main.getStoreScene());
    }

    @FXML
    private void sell(){

        Main.getAlertWindow().setTitle("Sell");
        Main.store.updateStores();
        Main.getAlertWindow().setScene(Main.main.getSellScene());
    }

    @FXML
    private void keepGoing(){

        Main.gang.setDistance(Main.gang.getDistance() - 25);
        Main.getAlertWindow().close();
    }

    @FXML
    private void claim(){

        if (Main.gang.getCapturedThieves() > 0){

            bountyMethod();
        }else{

            bountyLbl.setText("Sorry... you haven't caught anyone ( NO BOUNTY 4 U !! )");
        }
    }

    @FXML
    private void dealerShip(){

        Main.getAlertWindow().setTitle("Dealer Ship");
        Main.store.updateStores();
        Main.getAlertWindow().setScene(Main.main.getDealerScene());
    }

    @FXML
    private void setHireBtn(){

        Main.getAlertWindow().setScene(Main.main.getHireScene());
    }

    @FXML
    private void initialize(){

        String cityNameLOCAL;

        // Some what circular linked list
        if (!cityName.hasNext()) {
            while (cityName.hasPrevious()) cityName.previous();

            cityNameLOCAL = cityName.next();
        } else cityNameLOCAL = cityName.next();

        Main.getAlertWindow().setTitle("City");
        townLbl.setText("You have come up to "+ cityNameLOCAL);
    }

    protected void bountyMethod(){

        int MoneyToClaim;

        MoneyToClaim = (int) (Math.random() * 5000) + 1000;
        MoneyToClaim *= Main.gang.getCapturedThieves();

        Main.gang.setScore(Main.gang.getScore() + 50000);

        bountyLbl.setText("You have Claimed: $"+MoneyToClaim);

        Main.gang.setMoney(Main.gang.getMoney() + MoneyToClaim);

        Main.gang.setCapturedThieves(0);
    }
}
