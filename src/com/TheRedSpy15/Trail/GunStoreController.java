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

    private static short amountOver = 0;
    @FXML private Label moneyLbl;
    @FXML private Slider grenadeSlider;
    @FXML private Slider ammoSlider;

    @FXML private void ak47Btn(){

        if (getMoney() >= 500){

            setMoney(getMoney() - 500);

            setScore(getScore() + 5000);

            setBaseAttackDamage(45);

            playPurchaseSound();

            alert.specialPurchase("AK-47");
            setGunID("AK-47");
            setGunSpriteURL("com/TheRedSpy15/trail/ff1fbae3c3282a772246605d08225293.png");

            moneyLbl.setText("Money: $"+ getMoney());
        }else if (getMoney() < 500){

            amountOver = (short) (getMoney() - 500);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void grenadeBtn(){

        if (getMoney() >= (1000 * grenadeSlider.getValue())){

            setMoney(getMoney() - (1000 * grenadeSlider.getValue()));

            setScore(getScore() + (int) (10000 * grenadeSlider.getValue()));

            setGrenades(getGrenades() + (int) grenadeSlider.getValue());

            playPurchaseSound();

            alert.specialPurchase("GRENADE ("+(int)grenadeSlider.getValue()+")");

            moneyLbl.setText("Money: $"+ getMoney());
        }else if (getMoney() < (1000 * grenadeSlider.getValue())){

            amountOver = (short) (getMoney() - (1000 * grenadeSlider.getValue()));

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void ammoBtn(){

        if (getMoney() >= (25 * ammoSlider.getValue())){

            setMoney(getMoney() - (25 * ammoSlider.getValue()));

            setScore(getScore() + (int) (10000 * ammoSlider.getValue()));

            setAmmo(getAmmo() + (int) ammoSlider.getValue());

            playPurchaseSound();

            alert.specialPurchase("AMMO ("+(int)ammoSlider.getValue()+")");

            moneyLbl.setText("Money: $"+ getMoney());
        }else if (getMoney() < (25 * ammoSlider.getValue())){

            amountOver = (short) (getMoney() - (25 * ammoSlider.getValue()));

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void backBtn(){

        store.updateStores();

        // determines if the scene is being used in alert window or main window and changes depending on that
        if (!(getAlertWindow().isShowing())){

            // setting window scene to travel pane
            getMainWindow().setScene(getStoreScene());
        }else{

            // Changing alert window scene to settlement scene
            getAlertWindow().setScene(getStoreScene());
        }
    }

    @FXML private void initialize(){

        moneyLbl.setText("Your money: $"+(int) getMoney());
    }
}
