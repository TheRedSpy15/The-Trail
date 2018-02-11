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

public class GunStoreController extends Store {

    @FXML private Label moneyLbl, ammoLbl, grenadeLbl, vestLbl;
    @FXML private JFXSlider grenadeSlider, ammoSlider;

    @FXML private void ak47Btn(){ // ak47

        final short price = 500;
        final short damage = 45;
        if (Main.gang.getMoney() >= price){

            purchaseItem( price, (short) 1, "AK-47");

            Main.gang.setBaseAttackDamage(damage);

            Main.gang.setGunID("AK-47");
            Main.gang.setGunSpriteURL("com/TheRedSpy15/trail/ak47.png");

            moneyLbl.setText("Money: $"+ Main.gang.getMoney());
        }else if (Main.gang.getMoney() < price){ // Not enough money

            amountOver = (short) (Main.gang.getMoney() - price);

            Main.alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void doubleBarrelBtn(){

        final short price = 1500;
        final short damage = 85;
        if (Main.gang.getMoney() >= price){

            purchaseItem( price, (short) 1, "Double-Barrel");

            Main.gang.setBaseAttackDamage(damage);

            Main.gang.setGunID("Double-Barrel");
            Main.gang.setGunSpriteURL("com/TheRedSpy15/trail/DoubleBarrel.png");

            moneyLbl.setText("Money: $"+ Main.gang.getMoney());
        }else if (Main.gang.getMoney() < price){ // Not enough money

            amountOver = (short) (Main.gang.getMoney() - price);

            Main.alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void uziBtn(){

        final short price = 350;
        final short damage = 25;
        if (Main.gang.getMoney() >= price){

            purchaseItem( price, (short) 1, "Uzi");

            Main.gang.setBaseAttackDamage(damage);

            Main.gang.setGunID("Uzi");
            Main.gang.setGunSpriteURL("com/TheRedSpy15/trail/uzi.png");

            moneyLbl.setText("Money: $"+ Main.gang.getMoney());
        }else if (Main.gang.getMoney() < price){ // Not enough money

            amountOver = (short) (Main.gang.getMoney() - price);

            Main.alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void armorBtn(){ // armor

        final short price = 500;
        if (Main.gang.getMoney() >= price){

            purchaseItem( price, (short) 1, "BULLET PROOF VEST");

            Main.gang.setBodyArmor((byte) (Main.gang.getBodyArmor() + 1));

            moneyLbl.setText("Money: $"+ (short) Main.gang.getMoney());
        }else if (Main.gang.getMoney() < price){ // Not enough money

            amountOver = (short) (Main.gang.getMoney() - price);

            Main.alert.notEnoughMoney(amountOver);
        }

        initialize();
    }

    @FXML private void grenadeBtn(){ // grenade

        final short price = 1000;
        if (Main.gang.getMoney() >= (price * grenadeSlider.getValue())){

            purchaseItem( price, (short) grenadeSlider.getValue(), "GRENADE ("+(byte)grenadeSlider.getValue()+")");

            Main.gang.setGrenades(Main.gang.getGrenades() + (byte) grenadeSlider.getValue());

            moneyLbl.setText("Money: $"+ (short) Main.gang.getMoney());
        }else if (Main.gang.getMoney() < (price * grenadeSlider.getValue())){ // Not enough money

            amountOver = (short) (Main.gang.getMoney() - (price * grenadeSlider.getValue()));

            Main.alert.notEnoughMoney(amountOver);
        }

        initialize();
    }

    @FXML private void ammoBtn(){ // ammo

        final byte price = 25;
        if (Main.gang.getMoney() >= (price * ammoSlider.getValue())){

            purchaseItem( (short) price, (short) ammoSlider.getValue(), "AMMO ("+(short)ammoSlider.getValue()+")");

            Main.gang.setAmmo(Main.gang.getAmmo() + (short) ammoSlider.getValue());

            moneyLbl.setText("Money: $"+ (short) Main.gang.getMoney());
        }else if (Main.gang.getMoney() < (price * ammoSlider.getValue())){ // Not enough money

            amountOver = (short) (Main.gang.getMoney() - (price * ammoSlider.getValue()));

            Main.alert.notEnoughMoney(amountOver);
        }

        initialize();
    }

    @FXML private void backBtn(){ // back to store

        Main.store.updateStores();

        // determines if the scene is being used in alert window or main window and changes depending on that
        if (!(Main.getAlertWindow().isShowing())){

            // setting window scene to travel pane
            Main.main.getMainWindow().setScene(Main.main.getStoreScene());
        }else{

            // Changing alert window scene to settlement scene
            Main.getAlertWindow().setScene(Main.main.getStoreScene());
        }
    }

    @FXML private void initialize(){

        vestLbl.setText("Own: "+Main.gang.getBodyArmor());
        ammoLbl.setText("Own: "+Main.gang.getAmmo());
        grenadeLbl.setText("Own: "+Main.gang.getGrenades());
        moneyLbl.setText("Your money: $"+(short) Main.gang.getMoney());
    }
}
