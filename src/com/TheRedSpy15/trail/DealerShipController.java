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

public class DealerShipController extends Store {

    @FXML private Label moneyLbl2;
    @FXML private Label moneyLbl1;

    public void blueTruck(){

        if (Gang.getMoney() >= 5000){

            purchaseItem( (short) 5000, (short) 1, "BLUE TRUCK");

            Gang.setCarSpriteURL("com/TheRedSpy15/trail/bluetruck.png");

            moneyLbl1.setText("Money: $"+ Gang.getMoney());
        }else if (Gang.getMoney() < 5000){

            amountOver = (short) (Gang.getMoney() - 5000);

            Main.alert.alert("Amount over: ");
        }
    }

    @FXML private void setBackBtn1(){

        Main.getAlertWindow().setScene(Main.getCityScene());
    }

    @FXML private void setBackBtn2(){

        Main.getAlertWindow().setScene(Main.getCityScene());
    }

    public void rallyCar(){

        if (Gang.getMoney() >= 3500){

            purchaseItem( (short) 3500, (short) 1, "RALLY CAR");

            Gang.setCarSpriteURL("com/TheRedSpy15/trail/rallycar.png");

            moneyLbl1.setText("Money: $"+ Gang.getMoney());
        }else if (Gang.getMoney() < 3500){

            amountOver = (short) (Gang.getMoney() - 3500);

            Main.alert.alert("Amount over: "+amountOver);
        }
    }

    @FXML private void initialize(){

        moneyLbl1.setText("Your money: $"+(short) Gang.getMoney());
        moneyLbl2.setText("Your money: $"+(short) Gang.getMoney());
    }
}
