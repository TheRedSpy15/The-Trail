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

public class ThiefMenuController {

    @FXML private Label loseLbl;
    private short amount = 0;

    @FXML private void initialize(){

        amount = (short) (Math.random() * 500);

        if (amount > Gang.getMoney()) amount = (short) Gang.getMoney();

        loseLbl.setText("Would lose: $"+amount);
    }

    @FXML public void setIgnoreBtn(){

        Gang.setMoney(Gang.getMoney() - amount);

        Main.getAlertWindow().close();
    }

    @FXML public void setCaptureBtn(){

        int chance = (int) (Math.random() * 100);

        if (chance >= 50 && Gang.getCapturedThieves() == 0) {

            Main.alert.alert("You have capture the thief, turn them in for a reward");

            Gang.setCapturedThieves(Gang.getCapturedThieves() + 1);
        }else {

            Main.alert.alert("You failed catch them and they got away");

            Gang.setMoney(Gang.getMoney() - amount);
        }
    }

    @FXML public void setAttackBtn(){

        Main.getAlertWindow().setScene(Main.getShootOutScene());
    }
}
