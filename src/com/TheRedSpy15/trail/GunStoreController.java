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

public class GunStoreController extends Store {

    @FXML private Label moneyLbl;
    @FXML private Slider grenadeSlider;
    @FXML private Slider ammoSlider;

    @FXML private void ak47Btn(){ // ak47

        if (Gang.getMoney() >= 500){

            purchaseItem( (short) 500, (short) 1, "AK-47");

            Gang.setBaseAttackDamage(45);

            Gang.setGunID("AK-47");
            Gang.setGunSpriteURL("com/TheRedSpy15/trail/ak47.png");

            moneyLbl.setText("Money: $"+ Gang.getMoney());
        }else if (Gang.getMoney() < 500){

            amountOver = (short) (Gang.getMoney() - 500);

            Main.alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void setArmorBtn(){ // armor

        if (Gang.getMoney() >= 500){

            purchaseItem( (short) 500, (short) 1, "BULLET PROOF VEST");

            Gang.setBodyArmor((byte) (Gang.getBodyArmor() + 1));

            moneyLbl.setText("Money: $"+ Gang.getMoney());
        }else if (Gang.getMoney() < 500){

            amountOver = (short) (Gang.getMoney() - 500);

            Main.alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void grenadeBtn(){ // grenade

        if (Gang.getMoney() >= (1000 * grenadeSlider.getValue())){

            purchaseItem( (short) 1000, (short) grenadeSlider.getValue(), "GRENADE ("+(byte)grenadeSlider.getValue()+")");

            Gang.setGrenades(Gang.getGrenades() + (byte) grenadeSlider.getValue());

            moneyLbl.setText("Money: $"+ Gang.getMoney());
        }else if (Gang.getMoney() < (1000 * grenadeSlider.getValue())){

            amountOver = (short) (Gang.getMoney() - (1000 * grenadeSlider.getValue()));

            Main.alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void ammoBtn(){ // ammo

        if (Gang.getMoney() >= (25 * ammoSlider.getValue())){

            purchaseItem( (short) 25, (short) ammoSlider.getValue(), "AMMO ("+(short)ammoSlider.getValue()+")");

            Gang.setAmmo(Gang.getAmmo() + (short) ammoSlider.getValue());

            playPurchaseSound();

            moneyLbl.setText("Money: $"+ Gang.getMoney());
        }else if (Gang.getMoney() < (25 * ammoSlider.getValue())){

            amountOver = (short) (Gang.getMoney() - (25 * ammoSlider.getValue()));

            Main.alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void backBtn(){ // back to store

        Main.store.updateStores();

        // determines if the scene is being used in alert window or main window and changes depending on that
        if (!(Main.getAlertWindow().isShowing())){

            // setting window scene to travel pane
            Main.getMainWindow().setScene(Main.getStoreScene());
            Main.checkFullScreen();
        }else{

            // Changing alert window scene to settlement scene
            Main.getAlertWindow().setScene(Main.getStoreScene());
        }
    }

    @FXML private void initialize(){

        moneyLbl.setText("Your money: $"+(short) Gang.getMoney());
    }
}
