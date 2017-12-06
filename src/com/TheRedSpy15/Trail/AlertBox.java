package com.TheRedSpy15.Trail;

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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;

import java.io.IOException;

public class AlertBox extends Main {

    private static Parent gameOverPane;

    protected static void alertMenuStart(){

        //AlertWindow.setOnCloseRequest(e -> TravelClass.travelSetup());

        //Block events to other windows
        getAlertWindow().initModality(Modality.APPLICATION_MODAL);
        getAlertWindow().setTitle("Alert");
    }

    AlertBox() {

        getAlertWindow().setTitle("Alert");
    }

    protected void thiefEncounter(){

        setMoving(false);
        getAlertWindow().setTitle("Thief");

        // shoot out pane
        try {
            shootOutPane = FXMLLoader.load(Main.class.getResource("ShootOut.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        shootOutScene = new Scene(shootOutPane);

        // Thief menu pane
        try {
            thiefMenuPane = FXMLLoader.load(Main.class.getResource("ThiefMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        thiefMenuScene = new Scene(thiefMenuPane);

        getAlertWindow().setScene(thiefMenuScene);
        getAlertWindow().showAndWait();
    }

    protected void alert(String alertMSG){

        Label label = new Label(alertMSG);
        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: white;");
        label.setFont(new Font(20));

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #cf1020");
        stackPane.setPadding(new Insets(20,20,20,20));
        stackPane.getChildren().add(label);

        Scene scene = new Scene(stackPane);
        getAlertWindow().setScene(scene);

        if (!(getAlertWindow().isShowing())) getAlertWindow().showAndWait();
    }

    protected void notEnoughMoney(int amount){

        Button button = new Button("Back");
        Label label = new Label("Amount over: "+ amount);
        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: white;");

        VBox stackPane = new VBox();
        stackPane.setStyle("-fx-background-color: #cf1020");
        stackPane.setPadding(new Insets(20,20,20,20));
        stackPane.getChildren().addAll(label,button);

        Scene scene = new Scene(stackPane);

        // Determining what scene to go back too
        if (!(getAlertWindow().isShowing())) button.setOnAction(e -> getAlertWindow().close());
        else if (getAlertWindow().getScene() == dealerScene) button.setOnAction(e -> getAlertWindow().setScene(dealerScene));
        else if (getAlertWindow().getScene() == getGunStoreScene()) button.setOnAction(e -> getAlertWindow().setScene(getGunStoreScene()));
        else if (getAlertWindow().getScene() == hireScene) button.setOnAction(e -> getAlertWindow().setScene(hireScene));

        getAlertWindow().setScene(scene);
        getAlertWindow().setTitle("Not enough money");
        if (!(getAlertWindow().isShowing())) getAlertWindow().showAndWait();
    }

    protected void specialPurchase(String item){

        Button button = new Button("Ok");
        Label label = new Label("Purchased: " + item);
        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: white;");

        VBox stackPane = new VBox();
        stackPane.setStyle("-fx-background-color: #cf1020");
        stackPane.setPadding(new Insets(20,20,20,20));
        stackPane.getChildren().addAll(label,button);

        Scene scene = new Scene(stackPane);

        // Determining what scene to go back too
        if (!(getAlertWindow().isShowing())) button.setOnAction(e -> getAlertWindow().close());
        else if (getAlertWindow().getScene() == dealerScene) button.setOnAction(e -> getAlertWindow().setScene(dealerScene));
        else if (getAlertWindow().getScene() == getGunStoreScene()) button.setOnAction(e -> getAlertWindow().setScene(getGunStoreScene()));

        getAlertWindow().setScene(scene);
        getAlertWindow().setTitle("PURCHASED");
        if (!(getAlertWindow().isShowing())) getAlertWindow().showAndWait();
    }

    protected void cityEvent() {

        // Settlement menu pane
        try {
            cityPane = FXMLLoader.load(Main.class.getResource("CityMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            hirePane = FXMLLoader.load(Main.class.getResource("HireMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        hireScene = new Scene(hirePane);

        distanceSinceCity = 0;

        cityScene = new Scene(cityPane);

        //Stop moving
        setMoving(false);

        getAlertWindow().setTitle("City");
        getAlertWindow().setScene(cityScene);
        getAlertWindow().show();
    }

    protected static void gameOver(){

        // Game over pane
        try {
            gameOverPane = FXMLLoader.load(Main.class.getResource("GameOver.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // On closure of window, closes main MainWindow also
        getAlertWindow().setOnCloseRequest(e -> getMainWindow().close());

        // makes game over pane a scene and sets the alert window scene to it
        getAlertWindow().setScene(new Scene(gameOverPane));

        // sets alert window title to game over
        getAlertWindow().setTitle("GAME-OVER");

        // shows alert window
        getAlertWindow().showAndWait();
    }
}