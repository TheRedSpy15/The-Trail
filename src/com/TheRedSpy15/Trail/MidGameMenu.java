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

import static com.TheRedSpy15.trail.Gang.*;

public class MidGameMenu extends Main {

    private static Parent menuPane;
    protected static Scene menuScene;

    protected static void menuMethod(){

        // MENU

        // Links menu pane to FXML file
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

        // Creating new Stage
        setMenuWindow(new Stage());

        // Setting title of stage
        getMenuWindow().setTitle("MENU");

        // preventing interaction with other windows
        getMenuWindow().initModality(Modality.APPLICATION_MODAL);

        // Set MenuWindow's scene
        getMenuWindow().setScene(menuScene);
    }

    protected static void inventoryMethod(){

        getMenuWindow().setScene(getInventoryScene());
    }

    // Used to set food intake
    protected static void foodPortionSet(){

        // Object declaration
        Button BuffetDietbtn = new Button("Buffet");
        VBox FoodPortionsLayout = new VBox(10);
        Button ExtremeDietbtn = new Button("Extreme");
        Label label = new Label("Select your meal plan");
        Button ModerateDietbtn = new Button("Moderate");

        // Setting font size of Label and color
        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: purple;");

        // Setting background color
        FoodPortionsLayout.setStyle("-fx-background-color: black;");

        // Setting food intake value and going back to menu scene
        ExtremeDietbtn.setOnAction(e -> {
            setFoodIntake(1);
            getMenuWindow().setScene(menuScene);
        });

        // Setting food intake value and going back to menu scene
        ModerateDietbtn.setOnAction(e -> {
            setFoodIntake(2);
            getMenuWindow().setScene(menuScene);
        });

        // Setting food intake value and going back to menu scene
        BuffetDietbtn.setOnAction(e -> {
            setFoodIntake(3);
            getMenuWindow().setScene(menuScene);
        });

        // Setting padding to layout
        FoodPortionsLayout.setPadding(new Insets(20,20,20,20));

        // adding objects to layout
        FoodPortionsLayout.getChildren().addAll(label,ExtremeDietbtn,ModerateDietbtn,BuffetDietbtn);

        // initializing scene
        setFoodPortionsScene(new Scene(FoodPortionsLayout,320,200));
    }

    protected static void paceSetterMethod(){

        // Object declaration
        VBox PaceLayout = new VBox(10);
        Label label = new Label("Choose a speed");
        Button Slowbtn = new Button("Slow pace");
        Button ModerateSpeedbtn = new Button("Moderate pace");
        Button Fastbtn = new Button("Fast pace");

        // Setting font size of label and color
        label.setStyle("-fx-text-fill: purple;");
        label.setFont(new Font(20));

        // setting background color
        PaceLayout.setStyle("-fx-background-color: black");

        // Setting pace value and going back to menu scene
        Slowbtn.setOnAction(e -> {
            setPace(5);
            TravelController.animationDuration = 30;
            getMenuWindow().setScene(menuScene);
        });

        // Setting pace value and going back to menu scene
        ModerateSpeedbtn.setOnAction(e -> {
            setPace(10);
            TravelController.animationDuration = 15;
            getMenuWindow().setScene(menuScene);
        });

        // Setting pace value and going back to menu scene
        Fastbtn.setOnAction(e -> {
            setPace(15);
            TravelController.animationDuration = 10;
            getMenuWindow().setScene(menuScene);
        });

        // Setting padding to layout
        PaceLayout.setPadding(new Insets(20,20,20,20));

        // Adding padding to layout
        PaceLayout.getChildren().addAll(label,Slowbtn,ModerateSpeedbtn,Fastbtn);

        // Initializing scene
        setPaceScene(new Scene(PaceLayout,320,200));
    }
}