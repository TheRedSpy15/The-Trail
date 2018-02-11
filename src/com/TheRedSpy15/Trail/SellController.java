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

import com.jfoenix.controls.JFXSlider;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SellController {

    @FXML private Label moneyLbl, grenadeLbl, foodLbl, waterLbl, ammoLbl;
    @FXML private JFXSlider grenadeSlider, waterSlider, foodSlider, ammoSlider;

    @FXML
    private void sell(){

        Main.gang.setFood(Main.gang.getFood() - (short) foodSlider.getValue());
        Main.gang.setWater(Main.gang.getWater() - (short) waterSlider.getValue());
        Main.gang.setAmmo(Main.gang.getAmmo() - (short) ammoSlider.getValue());
        Main.gang.setGrenades(Main.gang.getGrenades() - (byte) grenadeSlider.getValue());

        Main.store.playPurchaseSound();

        final double waterValue = 0.15;
        final double foodValue = 0.30;
        final byte grenadeValue = 50;
        final byte ammoValue = 20;

        Main.gang.setMoney(Main.gang.getMoney() +
                (waterSlider.getValue() * waterValue) +
                (foodSlider.getValue() * foodValue) +
                (ammoSlider.getValue() * ammoValue) +
                (grenadeSlider.getValue() * grenadeValue)
        );

        Main.getAlertWindow().setScene(Main.main.getCityScene());
    }

    @FXML
    private void setSellCarBtn(){ // sell car

        final short rallyCarValue = 1500;
        final short monsterTruckValue = 2500;
        final short speedDemonValue = 5000;

        switch (Main.gang.getCarSpriteURL()) {
            case "com/TheRedSpy15/trail/bluetruck.png":  // Blue truck

                Main.alert.sold("Monster Truck", (short) 1, monsterTruckValue);

                Main.gang.setCarSpriteURL(Main.gang.getDefaultCarURL());
                Main.gang.setVehicleID("Starter Car");

                moneyLbl.setText("Money: $" + Main.gang.getMoney());
                break;
            case "com/TheRedSpy15/trail/rallycar.png":  // Rally car

                Main.alert.sold("Rally car", (short) 1, rallyCarValue);

                Main.gang.setCarSpriteURL(Main.gang.getDefaultCarURL());
                Main.gang.setVehicleID("Starter Car");

                moneyLbl.setText("Money: $" + Main.gang.getMoney());
                break;
            case "com/TheRedSpy15/trail/redcar.png":

                Main.alert.sold("Speed Demon", (short) 1, speedDemonValue);

                Main.gang.setCarSpriteURL(Main.gang.getDefaultCarURL());
                Main.gang.setVehicleID("Starter Car");

                moneyLbl.setText("Money: $" + Main.gang.getMoney());
                break;
            default:

                Main.alert.cannotSell("car");
                break;
        }
    }

    @FXML
    private void setSellGunBtn(){ // sell gun

        final short ak47Value = 350;
        final short doubleBarrelValue = 850;
        final short uziValue = 100;

        switch (Main.gang.getGunSpriteURL()) {
            case "com/TheRedSpy15/trail/ak47.png":  // AK-47

                Main.alert.sold("AK-47", (short) 1, ak47Value);

                Main.gang.setBaseAttackDamage(Main.gang.getDefaultAttackDMG());
                Main.gang.setGunID(Main.gang.getDefaultGunID());
                Main.gang.setGunSpriteURL(Main.gang.getDefaultGunSpriteURL());

                moneyLbl.setText("Money: $" + Main.gang.getMoney());
                break;
            case "com/TheRedSpy15/trail/DoubleBarrel.png":

                Main.alert.sold("Double-Barrel", (short) 1, doubleBarrelValue);

                Main.gang.setBaseAttackDamage(Main.gang.getDefaultAttackDMG());
                Main.gang.setGunID(Main.gang.getDefaultGunID());
                Main.gang.setGunSpriteURL(Main.gang.getDefaultGunSpriteURL());

                moneyLbl.setText("Money: $" + Main.gang.getMoney());
                break;
            case "com/TheRedSpy15/trail/uzi.png":

                Main.alert.sold("Uzi", (short) 1, uziValue);

                Main.gang.setBaseAttackDamage(Main.gang.getDefaultAttackDMG());
                Main.gang.setGunID(Main.gang.getDefaultGunID());
                Main.gang.setGunSpriteURL(Main.gang.getDefaultGunSpriteURL());

                moneyLbl.setText("Money: $" + Main.gang.getMoney());
                break;
            default:
                Main.alert.cannotSell("gun");
                break;
        }
    }

    @FXML
    private void initialize(){

        foodLbl.setText("Food: "+ Main.gang.getFood());
        waterLbl.setText("Water: "+ Main.gang.getWater());
        ammoLbl.setText("Ammo: "+ Main.gang.getAmmo());
        grenadeLbl.setText("Ammo: "+ Main.gang.getGrenades());
        moneyLbl.setText("Money: $"+ Main.gang.getMoney());

        waterSlider.setMax(Main.gang.getWater());
        foodSlider.setMax(Main.gang.getFood());
        ammoSlider.setMax(Main.gang.getAmmo());
        grenadeSlider.setMax(Main.gang.getGrenades());

        waterSlider.setMin(0);
        ammoSlider.setMin(0);
        foodSlider.setMin(0);
        grenadeSlider.setMin(0);
    }
}
