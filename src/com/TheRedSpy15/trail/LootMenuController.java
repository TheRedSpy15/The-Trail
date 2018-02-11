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

public class LootMenuController extends Main {

    @FXML private Label moneyLbl, foodLbl, waterLbl, ammoLbl;

    private short moneyLOOT, foodLOOT, waterLOOT, ammoLOOT;

    @FXML
    private void initialize(){

        final short maxMoneyLOOT = 1500;
        final short maxFoodLOOT = 500;
        final short maxWaterLOOT = 500;
        final short maxAmmoLOOT = 100;

        moneyLOOT = (short) ((Math.random() * maxMoneyLOOT) + 0);
        foodLOOT = (short) ((Math.random() * maxFoodLOOT) + 0);
        waterLOOT = (short) ((Math.random() * maxWaterLOOT) + 0);
        ammoLOOT = (short) ((Math.random() * maxAmmoLOOT) + 0);

        moneyLbl.setText("Money: $"+ moneyLOOT);
        foodLbl.setText("Food: "+foodLOOT);
        waterLbl.setText("Water: "+waterLOOT);
        ammoLbl.setText("Ammo: "+ammoLOOT);
    }

    @FXML
    private void setTakeBtn(){

        Main.gang.setMoney(Main.gang.getMoney() + moneyLOOT);
        Main.gang.setFood(Main.gang.getFood() + foodLOOT);
        Main.gang.setWater(Main.gang.getWater() + waterLOOT);
        Main.gang.setAmmo(Main.gang.getAmmo() + ammoLOOT);

        getAlertWindow().close();
    }
}
