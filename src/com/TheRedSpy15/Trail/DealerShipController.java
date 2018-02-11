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

public class DealerShipController extends Store {

    @FXML private Label monsterTruckMoneyLbl, rallyCarMoneyLbl, speedDemonMoneyLbl;

    @FXML private void backBtn1(){

        Main.getAlertWindow().setScene(Main.main.getCityScene());
    }

    @FXML private void backBtn2(){

        Main.getAlertWindow().setScene(Main.main.getCityScene());
    }

    @FXML private void backBtn3(){

        Main.getAlertWindow().setScene(Main.main.getCityScene());
    }

    @FXML private void speedDemon(){

        final short speedDemonPrice = 15000;
        if (Main.gang.getMoney() >= speedDemonPrice){

            purchaseItem(speedDemonPrice, (short) 1, "Speed Demon");

            Main.gang.setCarSpriteURL("com/TheRedSpy15/trail/redcar.png");
            Main.gang.setVehicleID("Speed Demon");
            Main.gang.setCarSpeed((short) 175);

            speedDemonMoneyLbl.setText("Money: $"+ Main.gang.getMoney());
        }else if (Main.gang.getMoney() < speedDemonPrice){

            amountOver = (short) (Main.gang.getMoney() - speedDemonPrice);

            Main.alert.alert("Amount over: " + amountOver);
        }
    }

    @FXML private void monsterTruck(){

        final short monsterTruckPrice = 5000;
        if (Main.gang.getMoney() >= monsterTruckPrice){

            purchaseItem(monsterTruckPrice, (short) 1, "Monster Truck");

            Main.gang.setCarSpriteURL("com/TheRedSpy15/trail/bluetruck.png");
            Main.gang.setVehicleID("Monster Truck");
            Main.gang.setCarSpeed((short) 100);

            monsterTruckMoneyLbl.setText("Money: $"+ Main.gang.getMoney());
        }else if (Main.gang.getMoney() < monsterTruckPrice){

            amountOver = (short) (Main.gang.getMoney() - monsterTruckPrice);

            Main.alert.alert("Amount over: " + amountOver);
        }
    }

    @FXML private void rallyCar(){

        final short rallyCarPrice = 3500;
        if (Main.gang.getMoney() >= rallyCarPrice){

            purchaseItem(rallyCarPrice, (short) 1, "RALLY CAR");

            Main.gang.setCarSpriteURL("com/TheRedSpy15/trail/rallycar.png");
            Main.gang.setVehicleID("Rally Car");
            Main.gang.setCarSpeed((short) 200);

            rallyCarMoneyLbl.setText("Money: $"+ Main.gang.getMoney());
        }else if (Main.gang.getMoney() < rallyCarPrice){

            amountOver = (short) (Main.gang.getMoney() - rallyCarPrice);

            Main.alert.alert("Amount over: "+ amountOver);
        }
    }

    @FXML private void initialize(){

        monsterTruckMoneyLbl.setText("Your money: $"+(short) Main.gang.getMoney());
        rallyCarMoneyLbl.setText("Your money: $"+(short) Main.gang.getMoney());
        speedDemonMoneyLbl.setText("Your money: $"+(short) Main.gang.getMoney());
    }
}
