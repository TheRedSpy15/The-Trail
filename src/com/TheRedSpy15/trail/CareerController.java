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
import javafx.scene.Scene;

public class CareerController extends Career{

    @FXML
    private void hitman() {

        final short hitmanStartingMoney = 3_500;
        final short hitmanWage = 1_000;

        Main.gang.setMoney(Main.gang.getMoney() + hitmanStartingMoney);
        Main.gang.setWage(hitmanWage);
        Main.gang.gangSetupMethod();
        Main.main.getMainWindow().setScene(new Scene(Main.main.getPossePane()));
    }

    @FXML
    private void drugDealer() {

        final short drugDealerStartingMoney = 2_000;
        final short drugDealerBonus = 10_000;
        final short drugDealerWage = 500;

        Main.gang.setMoney(Main.gang.getMoney() + drugDealerStartingMoney);
        Main.gang.setScore(Main.gang.getScore() + drugDealerBonus);
        Main.gang.setWage(drugDealerWage);
        Main.gang.gangSetupMethod();
        Main.main.getMainWindow().setScene(new Scene(Main.main.getPossePane()));
    }

    @FXML
    private void thief() {

        final short thiefStartingMoneyMAX = 1_000;
        final short thiefStartingMoneyMIN = 250;
        final short thiefBonus = 15_000;
        final short thiefWageMAX = 1_000;
        final short thiefWageMIN = 250;

        Main.gang.setMoney(Main.gang.getMoney() + (int) (Math.random() * thiefStartingMoneyMAX) + thiefStartingMoneyMIN);
        Main.gang.setScore(Main.gang.getScore() + thiefBonus);
        Main.gang.setWage((int) (Math.random() * thiefWageMAX) + thiefWageMIN);
        Main.gang.gangSetupMethod();
        Main.main.getMainWindow().setScene(new Scene(Main.main.getPossePane()));
    }
}
