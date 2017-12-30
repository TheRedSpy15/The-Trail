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
import javafx.scene.Scene;

public class CareerController extends Main {

    @FXML
    private void hitman() {

        Gang.setMoney(Gang.getMoney() + 3500);
        Gang.setWage(1000);
        gang.gangSetupMethod();
        getMainWindow().setScene(new Scene(getPossePane()));
        checkFullScreen();
    }

    @FXML
    private void drugDealer() {

        Gang.setMoney(Gang.getMoney() + 2000);
        Gang.setScore(Gang.getScore() + 10000);
        Gang.setWage(500);
        gang.gangSetupMethod();
        getMainWindow().setScene(new Scene(getPossePane()));
        checkFullScreen();
    }

    @FXML
    private void thief() {

        Gang.setMoney(Gang.getMoney() + 500);
        Gang.setScore(Gang.getScore() + 15000);
        Gang.setWage((int) (Math.random() * 1000) + 250);
        gang.gangSetupMethod();
        getMainWindow().setScene(new Scene(getPossePane()));
        checkFullScreen();
    }
}