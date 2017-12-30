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

public class SellController extends Gang {

    @FXML private Label moneyLbl;
    @FXML private Label grenadeLbl;
    @FXML private Slider grenadeSlider;
    @FXML private Label foodLbl;
    @FXML private Label waterLbl;
    @FXML private Label ammoLbl;
    @FXML private Slider waterSlider;
    @FXML private Slider foodSlider;
    @FXML private Slider ammoSlider;

    @FXML
    private void sell(){

        setFood(getFood() - (short) foodSlider.getValue());
        setWater(getWater() - (short) waterSlider.getValue());
        setAmmo(getAmmo() - (short) ammoSlider.getValue());
        setGrenades(getGrenades() - (byte) grenadeSlider.getValue());

        Main.store.playPurchaseSound();

        setMoney(getMoney() +
                (waterSlider.getValue() * 0.15) +
                (foodSlider.getValue() * 0.30) +
                (ammoSlider.getValue() * 20) +
                (grenadeSlider.getValue() * 50)
        );

        Main.getAlertWindow().setScene(Main.getCityScene());
    }

    @FXML
    private void setSellCarBtn(){ // sell car

        switch (getCarSpriteURL()) {
            case "com/TheRedSpy15/trail/bluetruck.png":  // Blue truck

                Main.alert.sold("Blue truck", (short) 1, (short) 2500);

                setCarSpriteURL(getDefaultCarURL());

                moneyLbl.setText("Money: $" + getMoney());
                break;
            case "com/TheRedSpy15/trail/rallycar.png":  // Rally car

                Main.alert.sold("Rally car", (short) 1, (short) 1500);

                setCarSpriteURL(getDefaultCarURL());

                moneyLbl.setText("Money: $" + getMoney());
                break;
            default:

                Main.alert.cannotSell("car");
                break;
        }
    }

    @FXML
    private void setSellGunBtn(){ // sell gun

        if (getGunSpriteURL().equals("com/TheRedSpy15/trail/ak47.png")){ // AK-47

            Main.store.playPurchaseSound();

            setMoney(getMoney() + 350);
            setBaseAttackDamage(getDefaultAttackDMG());
            setGunID(getDefaultGunID());
            setGunSpriteURL(getDefaultGunSpriteURL());

            Main.alert.sold("AK-47", (short) 1, (short) 350);

            moneyLbl.setText("Money: $"+getMoney());
        }else{

            Main.alert.cannotSell("gun");
        }
    }

    @FXML
    public void initialize(){

        foodLbl.setText("Food: "+ getFood());
        waterLbl.setText("Water: "+ getWater());
        ammoLbl.setText("Ammo: "+ getAmmo());
        grenadeLbl.setText("Ammo: "+ getGrenades());
        moneyLbl.setText("Money: $"+getMoney());

        waterSlider.setMax(getWater());
        foodSlider.setMax(getFood());
        ammoSlider.setMax(getAmmo());
        grenadeSlider.setMax(getGrenades());

        waterSlider.setMin(0);
        ammoSlider.setMin(0);
        foodSlider.setMin(0);
        grenadeSlider.setMin(0);
    }
}
