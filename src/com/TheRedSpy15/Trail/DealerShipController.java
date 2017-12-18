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

import static com.TheRedSpy15.trail.Gang.*;
import static com.TheRedSpy15.trail.Main.*;

public class DealerShipController extends Store {

    @FXML private Label moneyLbl2;
    @FXML private Label moneyLbl1;

    public void blueTruck(){

        if (getMoney() >= 5000){

            setMoney(getMoney() - 5000);

            setScore(getScore() + 50000);

            setCarSpriteURL("com/TheRedSpy15/trail/sprite6_0.png");

            alert.specialPurchase("BLUE TRUCK");

            playPurchaseSound();

            moneyLbl1.setText("Money: $"+ getMoney());
        }else if (getMoney() < 5000){

            amountOver = (short) (getMoney() - 5000);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void setBackBtn1(){

        getAlertWindow().setScene(getCityScene());
    }

    @FXML private void setBackBtn2(){

        getAlertWindow().setScene(getCityScene());
    }

    public void rallyCar(){

        if (getMoney() >= 3500){

            setMoney(getMoney() - 3500);

            setScore(getScore() + 35000);

            setCarSpriteURL("com/TheRedSpy15/trail/spr_rally_0.png");

            alert.specialPurchase("RALLY CAR");

            playPurchaseSound();

            moneyLbl1.setText("Money: $"+ getMoney());
        }else if (getMoney() < 3500){

            amountOver = (short) (getMoney() - 3500);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML private void initialize(){

        moneyLbl1.setText("Your money: $"+(int) getMoney());
        moneyLbl2.setText("Your money: $"+(int) getMoney());
    }
}
