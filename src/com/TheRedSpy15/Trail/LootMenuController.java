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
import javafx.scene.control.Label;

public class LootMenuController extends Main {

    @FXML private Label moneyLbl;
    @FXML private Label foodLbl;
    @FXML private Label waterLbl;
    @FXML private Label ammoLbl;

    private int moneyLOOT;
    private int foodLOOT;
    private int waterLOOT;
    private int ammoLOOT;

    @FXML
    private void initialize(){

        moneyLOOT = (int) (Math.random() * 1500) + 50;
        foodLOOT = (int) (Math.random() * 500) + 50;
        waterLOOT = (int) (Math.random() * 500) + 50;
        ammoLOOT = (int) (Math.random() * 100) + 5;

        moneyLbl.setText("Money: $"+ moneyLOOT);
        foodLbl.setText("Food: "+foodLOOT);
        waterLbl.setText("Water: "+waterLOOT);
        ammoLbl.setText("Ammo: "+ammoLOOT);
    }

    @FXML
    private void setTakeBtn(){

        setMoney(getMoney() + moneyLOOT);
        setFood(getFood() + foodLOOT);
        setWater(getWater() + waterLOOT);
        setAmmo(getAmmo() + ammoLOOT);

        getAlertWindow().close();
    }
}
