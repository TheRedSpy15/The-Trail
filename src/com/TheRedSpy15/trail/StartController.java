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
import javafx.scene.control.CheckBox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import static com.TheRedSpy15.trail.Gang.*;

public class StartController extends Main{

    @FXML private CheckBox fullScreenCheckBox;
    @FXML private CheckBox modCheckBox;

    @FXML
    private void handleButtonClick() throws IOException {

        if (modCheckBox.isSelected()) runModFile();

        if (fullScreenCheckBox.isSelected()) fullScreen = true;

        career.careerPicker();
        getMainWindow().setScene(new Scene(getDescriptionPane()));
        checkFullScreen();
    }

    private void runModFile(){

        /*
        *
        * Proper mod file example:
        *
        * true (mod car) true (mod gun) [path to car image] [path to gun image] [name of gun] [gun damage]
        *
        * even if false; enter corresponding type:
        *
        * false (mod car) true (mod gun) String [path to gun image] [name of gun] [gun damage]
        *
        * */

        // Path should be relative
        File modFile = new File("C:\\Users\\Hunter\\IdeaProjects\\The Trail JavaFX\\src\\com\\TheRedSpy15\\trail\\ModFile.txt");

        try {
            Scanner sc = new Scanner(modFile);

            Boolean modCar = sc.nextBoolean();
            Boolean modGun = sc.nextBoolean();

            String pathVehicle = sc.next();
            String pathGun = sc.next();
            String pathGID = sc.next();
            int pathDMG = sc.nextInt();

            if (modCar){

                setDefaultCarURL(pathVehicle);
                setCarSpriteURL(pathVehicle);
            }

            if (modGun){

                setGunSpriteURL(pathGun);
                setDefaultGunSpriteURL(pathGun);
                setBaseAttackDamage(pathDMG);
                setDefaultAttackDMG((byte) pathDMG);
                setGunID(pathGID);
                setDefaultGunID(pathGID);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
