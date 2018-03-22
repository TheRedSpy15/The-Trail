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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class InventoryController extends MidGameMenu {

    @FXML private Label
            armorLbl, foodLbl, waterLbl,
            ammoLbl, moneyLbl, scoreLbl,
            gunModelLbl, damageLbl, grenadeLbl,
            speedLbl, vehicleIDLabel, rankLbl;

    @FXML private ImageView carImage, gunImage;

    @FXML private void initialize(){

        vehicleIDLabel.setText("Model: " + Main.gang.getVehicleID());
        foodLbl.setText("Food: "+ Main.gang.getFood());
        armorLbl.setText("Armored Vests: "+ Main.gang.getBodyArmor());
        waterLbl.setText("Water: "+ Main.gang.getWater());
        ammoLbl.setText("Ammo: "+ Main.gang.getAmmo());
        moneyLbl.setText("Money: "+ (int) Main.gang.getMoney());
        carImage.setImage(new Image(Main.gang.getCarSpriteURL()));
        scoreLbl.setText("Score: "+ Main.gang.getScore());
        gunModelLbl.setText("Model: "+ Main.gang.getGunID());
        damageLbl.setText("Damage: "+ Main.gang.getBaseAttackDamage());
        gunImage.setImage(new Image(Main.gang.getGunSpriteURL()));
        grenadeLbl.setText("Grenades: "+ Main.gang.getGrenades());
        speedLbl.setText("TOP SPEED: " + Main.gang.getCarSpeed() + " MPH");
        rankLbl.setText("Rank: "+ Gang.determineRank());
    }

    @FXML private void backBtn(){

        back();
    }
}
