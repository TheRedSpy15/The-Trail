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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class ThiefKilledController extends ShootOutController {

    @FXML
    private void setLootBtn(){

        try {
            Main.setLootPane(FXMLLoader.load(Main.class.getResource("LootMenu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.setLootScene(new Scene(Main.getLootPane()));

        Main.getAlertWindow().setScene(Main.getLootScene());
    }
}