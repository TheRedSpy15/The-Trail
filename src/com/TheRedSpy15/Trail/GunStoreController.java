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

import static com.TheRedSpy15.trail.Gang.*;
import static com.TheRedSpy15.trail.Main.*;

public class GunStoreController extends Store {

    @FXML private Label moneyLbl;
    @FXML private Slider grenadeSlider;
    @FXML private Slider ammoSlider;

    @FXML private void ak47Btn(){ // ak47

        if (getMoney() >= 500){

            purchaseItem( (short) 500, (short) 1, "AK-47");

            setBaseAttackDamage(45);

            setGunID("AK-47");
            setGunSpriteURL("com/TheRedSpy15/trail/ak47.png");

            moneyLbl.setText("Money: $"+ getMoney());
        }else if (getMoney() < 500){

            amountOver = (short) (getMoney() - 500);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void setArmorBtn(){ // armor

        if (getMoney() >= 500){

            purchaseItem( (short) 500, (short) 1, "BULLET PROOF VEST");

            setBodyArmor((byte) (getBodyArmor() + 1));

            moneyLbl.setText("Money: $"+ getMoney());
        }else if (getMoney() < 500){

            amountOver = (short) (getMoney() - 500);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void grenadeBtn(){ // grenade

        if (getMoney() >= (1000 * grenadeSlider.getValue())){

            purchaseItem( (short) 1000, (short) grenadeSlider.getValue(), "GRENADE ("+(byte)grenadeSlider.getValue()+")");

            setGrenades(getGrenades() + (byte) grenadeSlider.getValue());

            moneyLbl.setText("Money: $"+ getMoney());
        }else if (getMoney() < (1000 * grenadeSlider.getValue())){

            amountOver = (short) (getMoney() - (1000 * grenadeSlider.getValue()));

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void ammoBtn(){ // ammo

        if (getMoney() >= (25 * ammoSlider.getValue())){

            purchaseItem( (short) 25, (short) ammoSlider.getValue(), "AMMO ("+(short)ammoSlider.getValue()+")");

            setAmmo(getAmmo() + (short) ammoSlider.getValue());

            playPurchaseSound();

            moneyLbl.setText("Money: $"+ getMoney());
        }else if (getMoney() < (25 * ammoSlider.getValue())){

            amountOver = (short) (getMoney() - (25 * ammoSlider.getValue()));

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void backBtn(){ // back to store

        store.updateStores();

        // determines if the scene is being used in alert window or main window and changes depending on that
        if (!(getAlertWindow().isShowing())){

            // setting window scene to travel pane
            getMainWindow().setScene(getStoreScene());
            checkFullScreen();
        }else{

            // Changing alert window scene to settlement scene
            getAlertWindow().setScene(getStoreScene());
        }
    }

    @FXML private void initialize(){

        moneyLbl.setText("Your money: $"+(short) getMoney());
    }
}
