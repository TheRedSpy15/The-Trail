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
import javafx.scene.control.TextField;

public class HireMenuController extends Store {

    @FXML private TextField nameField;
    @FXML private Label emptyLbl;

    @FXML private void setHireBtn(){

        if (Gang.getMoney() < 5000){

            amountOver = (short) (Gang.getMoney() - 5000);

            Main.alert.notEnoughMoney(amountOver); // Back button support in alert box class needs added
        }else if (nameField.getText().trim().equals("")){

            emptyLbl.setText("Text field CANNOT be empty!");
        }else {

            Gang.getGangMembers().push(nameField.getText());
            Main.getAlertWindow().setScene(Main.getCityScene());
        }
    }

    @FXML private void setBackBtn(){

        Main.getAlertWindow().setScene(Main.getCityScene());
    }

    @FXML private void initialize(){

        emptyLbl.setText("");
        nameField.setText("");

        Main.checkFullScreen();
    }
}