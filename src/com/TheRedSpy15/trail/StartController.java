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

import com.jfoenix.controls.JFXCheckBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class StartController extends Main{

    @FXML private JFXCheckBox modCheckBox, loadCheckBox, autoSaveCheckBox;

    @FXML
    private void handleButtonClick() throws IOException {

        if (modCheckBox.isSelected()) runModFile();

        autoSave = autoSaveCheckBox.isSelected();

        if (loadCheckBox.isSelected()){

            try {
                Gang.loadData();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            TravelClass.travelSetup();

            getMainWindow().setScene(new Scene(getTravelPane()));
        }else{

            // setting up description scene
            try {
                setDescriptionPane(FXMLLoader.load(Main.class.getResource("DescriptionScene.fxml")));
            } catch (IOException e) {
                e.printStackTrace();
            }

            getMainWindow().setScene(new Scene(getDescriptionPane()));
        }
    }

    @FXML
    private void GitHubLink(){

        getHostServices().showDocument("https://github.com/TheRedSpy15/The-Trail");
    }

    private void runModFile(){

        /*
        *
        * Proper mod file example:
        *
        * [path to gun image] [name of gun] [gun damage]
        *
        * String [path to gun image] String [name of gun] int [gun damage]
        *
        * */

        // NEEDS TESTING !!!
        File modFile = new File("ModFile.txt");

        try {
            Scanner sc = new Scanner(modFile);

            String pathGun = sc.next();
            String pathGID = sc.next();
            int pathDMG = sc.nextInt();

            Main.gang.setGunSpriteURL(pathGun);
            Main.gang.setDefaultGunSpriteURL(pathGun);
            Main.gang.setBaseAttackDamage(pathDMG);
            Main.gang.setDefaultAttackDMG((byte) pathDMG);
            Main.gang.setGunID(pathGID);
            Main.gang.setDefaultGunID(pathGID);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            alert.alert("Mod file not found!");
        } catch (Exception e){

            alert.alert("ERROR READING MOD FILE - might not be setup correctly");
        }
    }
}
