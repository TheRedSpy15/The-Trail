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
import javafx.scene.control.TextField;

public class GangController extends Main {
    
    @FXML  private TextField membername1;
    @FXML  private TextField membername2;
    @FXML  private TextField membername3;
    @FXML  private TextField membername4;
    @FXML  private TextField membername5;
    @FXML  private TextField membername6;

    @FXML
    private void confirmNamesMethod(){

        // If statement of any of the text fields not being empty
        if (!(membername1.getText().trim().equals("") || membername2.getText().trim().equals("") || membername3.getText().trim().equals("") || membername4.getText().trim().equals("") || membername5.getText().trim().equals("") || membername6.getText().trim().equals(""))){

            // Runs store method
            store.storeMethod();

            // Sets the scene to store pane
            getMainWindow().setScene(getStoreScene());

            // adds the value of the text fields to the linked list
            getGang().add(membername1.getText());
            getGang().add(membername2.getText());
            getGang().add(membername3.getText());
            getGang().add(membername4.getText());
            getGang().add(membername5.getText());
            getGang().add(membername6.getText());
        }
        else {

            // when the if statement is false, run the empty name alert
            alert.alert("Text fields cannot be empty");
        }
    }
}
