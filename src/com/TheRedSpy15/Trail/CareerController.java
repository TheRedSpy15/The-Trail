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

    // runs posse setup method, sets money to 1500, and sets scene to posse pane
    @FXML
    private void hitman() {

        setMoney(3500);
        setWage(1000);
        cp.posseSetupMethod();
        getMainWindow().setScene(new Scene(getPossePane()));
    }

    // runs posse setup method, sets money to 1000, and sets scene to posse pane
    @FXML
    private void drugDealer() {

        setMoney(2000);
        setScore(getScore() + 10000);
        setWage(500);
        cp.posseSetupMethod();
        getMainWindow().setScene(new Scene(getPossePane()));
    }

    // runs posse setup method, sets money to 500, and sets scene to posse pane
    @FXML
    private void thief() {

        setMoney(500);
        setScore(getScore() + 15000);
        setWage((int) (Math.random() * 1000) + 250);
        cp.posseSetupMethod();
        getMainWindow().setScene(new Scene(getPossePane()));
    }
}
