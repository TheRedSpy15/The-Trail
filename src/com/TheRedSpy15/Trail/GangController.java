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

import static com.TheRedSpy15.trail.Gang.*;

public class GangController extends Main {
    
    @FXML  private TextField memberName1;
    @FXML  private TextField memberName2;
    @FXML  private TextField memberName3;
    @FXML  private TextField memberName4;
    @FXML  private TextField memberName5;
    @FXML  private TextField memberName6;

    @FXML
    private void confirmNamesMethod(){

        // If statement of any of the text fields not being empty
        if (!(memberName1.getText().trim().equals("") || memberName2.getText().trim().equals("") || memberName3.getText().trim().equals("") || memberName4.getText().trim().equals("") || memberName5.getText().trim().equals("") || memberName6.getText().trim().equals(""))){

            // Runs store method
            store.updateStores();

            // Sets the scene to store pane
            getMainWindow().setScene(getStoreScene());

            // adds the value of the text fields to the linked list
            getGangMembers().add(memberName1.getText());
            getGangMembers().add(memberName2.getText());
            getGangMembers().add(memberName3.getText());
            getGangMembers().add(memberName4.getText());
            getGangMembers().add(memberName5.getText());
            getGangMembers().add(memberName6.getText());
        }
        else {

            // when the if statement is false, run the empty name alert
            alert.alert("Text fields cannot be empty");
        }
    }
}
