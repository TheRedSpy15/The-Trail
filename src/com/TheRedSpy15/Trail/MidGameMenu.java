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

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MidGameMenu {

    private static Parent menuPane, deceasedListPane, gangListPane;
    private static Scene menuScene;

    protected static void menuMethod(){

        // MENU
        try {
            menuPane = FXMLLoader.load(Main.class.getResource("MidMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuScene = new Scene(menuPane);

        // INVENTORY SCENE
        try {
            Main.main.setInventoryPane(FXMLLoader.load(Main.class.getResource("Inventory.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.main.setInventoryScene(new Scene(Main.main.getInventoryPane()));

        Main.main.setMenuWindow(new Stage());

        Main.main.getMenuWindow().setTitle("MENU");

        // preventing interaction with other windows
        Main.main.getMenuWindow().initModality(Modality.APPLICATION_MODAL);

        Main.main.getMenuWindow().setScene(menuScene);
    }

    /**
     * Sets the menu stage's scene to the inventory scene
     */
    static void inventoryMethod(){

        Main.main.getMenuWindow().setScene(Main.main.getInventoryScene());
    }

    /**
     * Sets the menu stage's scene to the gang list scene,
     * as well as updating it.
     */
    static void gangMethod(){

        // Gang list
        try {
            gangListPane = FXMLLoader.load(Main.class.getResource("GangList.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene gangListScene = new Scene(gangListPane);

        Main.main.getMenuWindow().setScene(gangListScene);
    }

    /**
     * Sets the menu stage's scene to the deceased list scene,
     * as well as updating it.
     */
    static void deceasedMethod(){

        // Gang list
        try {
            deceasedListPane = FXMLLoader.load(Main.class.getResource("DeceasedList.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene deceasedListScene = new Scene(deceasedListPane);

        Main.main.getMenuWindow().setScene(deceasedListScene);
    }

    /**
     * Creates, and sets the scene to one that
     * allows the player(s) to set the food portions
     */
    static void foodPortionSet(){

        final byte smallIntake = 1;
        final byte mediumIntake = 2;
        final byte largeIntake = 3;

        Button BuffetDietBtn = new Button("Buffet");
        VBox FoodPortionsLayout = new VBox(10);
        Button ExtremeDietBtn = new Button("Bugs for dinner");
        Label label = new Label("Select your meal plan");
        Button ModerateDietBtn = new Button("Weight Watchers");

        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: purple;");
        FoodPortionsLayout.setStyle("-fx-background-color: black;");

        ExtremeDietBtn.setOnAction(e -> {
            Main.gang.setFoodIntake(smallIntake);
            back();
        });

        ModerateDietBtn.setOnAction(e -> {
            Main.gang.setFoodIntake(mediumIntake);
            back();
        });

        BuffetDietBtn.setOnAction(e -> {
            Main.gang.setFoodIntake(largeIntake);
            back();
        });

        FoodPortionsLayout.setPadding(new Insets(20,20,20,20));
        FoodPortionsLayout.getChildren().addAll(label,ExtremeDietBtn,ModerateDietBtn,BuffetDietBtn);

        Main.main.setFoodPortionsScene(new Scene(FoodPortionsLayout,320,200));
    }

    /**
     * Creates, and sets the scene to one that
     * allows the player(s) to set the braking
     * frequency
     */
    static void brakeFrequencySetterMethod(){

        final byte slowSpeed = 5;
        final byte moderateSpeed = 10;
        final byte fastSpeed = 15;

        VBox PaceLayout = new VBox(10);
        Label label = new Label("Choose a brake frequency");
        Button SlowBtn = new Button("Constantly");
        Button ModerateSpeedBtn = new Button("Time to time");
        Button FastBtn = new Button("Rarely");

        label.setStyle("-fx-text-fill: purple;");
        label.setFont(new Font(20));
        PaceLayout.setStyle("-fx-background-color: black");

        SlowBtn.setOnAction(e -> {
            Main.gang.setBrakeFrequency(slowSpeed);
            TravelController.animationDuration = 30;
            back();
        });

        ModerateSpeedBtn.setOnAction(e -> {
            Main.gang.setBrakeFrequency(moderateSpeed);
            TravelController.animationDuration = 15;
            back();
        });

        FastBtn.setOnAction(e -> {
            Main.gang.setBrakeFrequency(fastSpeed);
            TravelController.animationDuration = 10;
            back();
        });

        PaceLayout.setPadding(new Insets(20,20,20,20));
        PaceLayout.getChildren().addAll(label,SlowBtn,ModerateSpeedBtn,FastBtn);

        Main.main.setPaceScene(new Scene(PaceLayout,320,200));
    }


    /**
     * A more simple way for scenes that extend mid game menu,
     * can go back to the menu itself.
     */
    protected static void back(){

        Main.main.getMenuWindow().setScene(menuScene);
    }
}