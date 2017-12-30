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

public class MidGameMenu extends Main {

    private static Parent menuPane;
    static Scene menuScene;

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
            setInventoryPane(FXMLLoader.load(Main.class.getResource("Inventory.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setInventoryScene(new Scene(getInventoryPane()));

        setMenuWindow(new Stage());

        getMenuWindow().setTitle("MENU");

        // preventing interaction with other windows
        getMenuWindow().initModality(Modality.APPLICATION_MODAL);

        getMenuWindow().setScene(menuScene);
    }

    /**
     *
     * Sets the menu stage's scene to the inventory scene
     *
     */
    protected static void inventoryMethod(){

        getMenuWindow().setScene(getInventoryScene());
    }

    /**
     *
     * Creates, and sets the scene to one that allows the player(s) to set the food portions
     *
     */
    protected static void foodPortionSet(){

        Button BuffetDietbtn = new Button("Buffet");
        VBox FoodPortionsLayout = new VBox(10);
        Button ExtremeDietbtn = new Button("Extreme");
        Label label = new Label("Select your meal plan");
        Button ModerateDietbtn = new Button("Moderate");

        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: purple;");
        FoodPortionsLayout.setStyle("-fx-background-color: black;");

        ExtremeDietbtn.setOnAction(e -> {
            Gang.setFoodIntake(1);
            getMenuWindow().setScene(menuScene);
        });

        ModerateDietbtn.setOnAction(e -> {
            Gang.setFoodIntake(2);
            getMenuWindow().setScene(menuScene);
        });

        BuffetDietbtn.setOnAction(e -> {
            Gang.setFoodIntake(3);
            getMenuWindow().setScene(menuScene);
        });

        FoodPortionsLayout.setPadding(new Insets(20,20,20,20));
        FoodPortionsLayout.getChildren().addAll(label,ExtremeDietbtn,ModerateDietbtn,BuffetDietbtn);

        setFoodPortionsScene(new Scene(FoodPortionsLayout,320,200));
    }

    /**
     *
     * Creates, and sets the scene to one that allows the player(s) to set the pace
     *
     */
    protected static void paceSetterMethod(){

        VBox PaceLayout = new VBox(10);
        Label label = new Label("Choose a speed");
        Button Slowbtn = new Button("Slow pace");
        Button ModerateSpeedbtn = new Button("Moderate pace");
        Button Fastbtn = new Button("Fast pace");

        label.setStyle("-fx-text-fill: purple;");
        label.setFont(new Font(20));
        PaceLayout.setStyle("-fx-background-color: black");

        Slowbtn.setOnAction(e -> {
            Gang.setPace(5);
            TravelController.animationDuration = 30;
            getMenuWindow().setScene(menuScene);
        });

        ModerateSpeedbtn.setOnAction(e -> {
            Gang.setPace(10);
            TravelController.animationDuration = 15;
            getMenuWindow().setScene(menuScene);
        });

        Fastbtn.setOnAction(e -> {
            Gang.setPace(15);
            TravelController.animationDuration = 10;
            getMenuWindow().setScene(menuScene);
        });

        PaceLayout.setPadding(new Insets(20,20,20,20));
        PaceLayout.getChildren().addAll(label,Slowbtn,ModerateSpeedbtn,Fastbtn);

        setPaceScene(new Scene(PaceLayout,320,200));
    }
}