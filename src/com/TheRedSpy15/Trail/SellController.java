package com.TheRedSpy15.trail;

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
import javafx.scene.control.Slider;

public class SellController extends AlertBox {

    @FXML private Label foodLbl;
    @FXML private Label waterLbl;
    @FXML private Label ammoLbl;
    @FXML private Slider waterSlider;
    @FXML private Slider foodSlider;
    @FXML private Slider ammoSlider;

    @FXML
    private void sell(){

        setFood(getFood() - (int) foodSlider.getValue());
        setWater(getWater() - (int) waterSlider.getValue());
        setAmmo(getAmmo() - (int) ammoSlider.getValue());

        store.playPurchaseSound();

        setMoney(getMoney() + (waterSlider.getValue() * 0.15) + (foodSlider.getValue() * 0.30) + (ammoSlider.getValue() * 20));

        getAlertWindow().setScene(cityScene);
    }

    @FXML
    public void initialize(){

        foodLbl.setText("Food: "+ getFood());
        waterLbl.setText("Water: "+ getWater());
        ammoLbl.setText("Ammo: "+ getAmmo());

        waterSlider.setMax(getWater());
        foodSlider.setMax(getFood());
        ammoSlider.setMax(getAmmo());

        waterSlider.setMin(0);
        ammoSlider.setMin(0);
        foodSlider.setMin(0);
    }
}
