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

        Main.main.getMainWindow().setScene(Main.main.getCityScene());
    }

    @FXML private void backBtn2(){

        Main.main.getMainWindow().setScene(Main.main.getCityScene());
    }

    @FXML private void backBtn3(){

        Main.main.getMainWindow().setScene(Main.main.getCityScene());
    }

    @FXML private void speedDemon(){

        final short speed = 15000;
        final short price = 175;
        if (Main.gang.getMoney() >= speed){

            purchaseItem(speed, (short) 1, "Speed Demon");

            Main.gang.setCarSpriteURL("com/TheRedSpy15/trail/redcar.png");
            Main.gang.setVehicleID("Speed Demon");
            Main.gang.setCarSpeed(price);

            speedDemonMoneyLbl.setText("Money: $"+ Main.gang.getMoney());
        }else if (Main.gang.getMoney() < speed){

            amountOver = (short) (Main.gang.getMoney() - speed);

            Main.alert.alert("Amount over: " + amountOver);
        }
    }

    @FXML private void monsterTruck(){

        final short price = 5000;
        final short speed = 100;
        if (Main.gang.getMoney() >= price){

            purchaseItem(price, (short) 1, "Monster Truck");

            Main.gang.setCarSpriteURL("com/TheRedSpy15/trail/bluetruck.png");
            Main.gang.setVehicleID("Monster Truck");
            Main.gang.setCarSpeed(speed);

            monsterTruckMoneyLbl.setText("Money: $"+ Main.gang.getMoney());
        }else if (Main.gang.getMoney() < price){

            amountOver = (short) (Main.gang.getMoney() - price);

            Main.alert.alert("Amount over: " + amountOver);
        }
    }

    @FXML private void rallyCar(){

        final short price = 3500;
        final short speed = 120;
        if (Main.gang.getMoney() >= price){

            purchaseItem(price, (short) 1, "RALLY CAR");

            Main.gang.setCarSpriteURL("com/TheRedSpy15/trail/rallycar.png");
            Main.gang.setVehicleID("Rally Car");
            Main.gang.setCarSpeed(speed);

            rallyCarMoneyLbl.setText("Money: $"+ Main.gang.getMoney());
        }else if (Main.gang.getMoney() < price){

            amountOver = (short) (Main.gang.getMoney() - price);

            Main.alert.alert("Amount over: "+ amountOver);
        }
    }

    @FXML private void initialize(){

        monsterTruckMoneyLbl.setText("Your money: $"+(short) Main.gang.getMoney());
        rallyCarMoneyLbl.setText("Your money: $"+(short) Main.gang.getMoney());
        speedDemonMoneyLbl.setText("Your money: $"+(short) Main.gang.getMoney());
    }
}
