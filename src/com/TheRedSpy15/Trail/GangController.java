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
import javafx.scene.control.TextField;

import java.util.Collections;

public class GangController extends Gang {
    
    @FXML private TextField memberName1, memberName2, memberName3, memberName4, memberName5, memberName6;

    @FXML
    private void confirmNamesMethod(){

        if (!(memberName1.getText().trim().equals("") ||
                memberName2.getText().trim().equals("") ||
                memberName3.getText().trim().equals("") ||
                memberName4.getText().trim().equals("") ||
                memberName5.getText().trim().equals("") ||
                memberName6.getText().trim().equals(""))){

            Main.store.updateStores();

            Main.main.getMainWindow().setScene(Main.main.getStoreScene());

            Main.gang.getGangMembers().push(memberName1.getText());
            Main.gang.getGangMembers().push(memberName2.getText());
            Main.gang.getGangMembers().push(memberName3.getText());
            Main.gang.getGangMembers().push(memberName4.getText());
            Main.gang.getGangMembers().push(memberName5.getText());
            Main.gang.getGangMembers().push(memberName6.getText());

            Collections.shuffle(Main.gang.getGangMembers());
        }else {

            Main.alert.alert("Text fields cannot be empty");
        }
    }
}
