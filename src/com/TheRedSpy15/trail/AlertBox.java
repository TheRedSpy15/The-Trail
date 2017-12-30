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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;

import java.io.IOException;

/**
 *
 * Contains multiple methods / scenes, that are modal, and are used to alert / notify the user
 *
 * NOTE: this class WILL see MAJOR changes in the near future, it is quite buggy
 *
 */
public class AlertBox {

    private static Parent gameOverPane;

    protected static void alertMenuStart(){

        Main.getAlertWindow().setOnCloseRequest(e -> TravelClass.travelSetup());

        //Block events to other windows
        Main.getAlertWindow().initModality(Modality.APPLICATION_MODAL);
        Main.getAlertWindow().setTitle("Alert");
    }

    AlertBox() { Main.getAlertWindow().setTitle("Alert"); }

    protected void thiefEncounter(){

        Gang.setMoving(false);
        Main.getAlertWindow().setTitle("Thief");

        // shoot out pane
        try {
            Main.setShootOutPane(FXMLLoader.load(Main.class.getResource("ShootOut.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.setShootOutScene(new Scene(Main.getShootOutPane()));

        // Thief menu pane
        try {
            Main.setThiefMenuPane(FXMLLoader.load(Main.class.getResource("ThiefMenu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.setThiefMenuScene(new Scene(Main.getThiefMenuPane()));

        Main.getAlertWindow().setScene(Main.getThiefMenuScene());
        Main.getAlertWindow().showAndWait();
    }

    /**
     *
     * Creates a scene, that has a label with a message, and is modal
     *
     * @param alertMSG String to be turned into a label
     */
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
        Main.getAlertWindow().setScene(scene);

        if (!(Main.getAlertWindow().isShowing())) Main.getAlertWindow().showAndWait();
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
        if (!(Main.getAlertWindow().isShowing())) button.setOnAction(e -> Main.getAlertWindow().close());
        else if (Main.getAlertWindow().getScene() == Main.getDealerScene()) button.setOnAction(e -> Main.getAlertWindow().setScene(Main.getDealerScene()));
        else if (Main.getAlertWindow().getScene() == Main.getGunStoreScene()) button.setOnAction(e -> Main.getAlertWindow().setScene(Main.getGunStoreScene()));
        else if (Main.getAlertWindow().getScene() == Main.getHireScene()) button.setOnAction(e -> Main.getAlertWindow().setScene(Main.getHireScene()));

        Main.getAlertWindow().setScene(scene);
        Main.getAlertWindow().setTitle("Not enough money");
        if (!(Main.getAlertWindow().isShowing())) Main.getAlertWindow().showAndWait();
    }

    protected void cannotSell(String type){

        Button button = new Button("Back");
        Label label = new Label("You cannot sell your current "+ type);
        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: white;");

        VBox stackPane = new VBox();
        stackPane.setStyle("-fx-background-color: #cf1020");
        stackPane.setPadding(new Insets(20,20,20,20));
        stackPane.getChildren().addAll(label,button);

        Scene scene = new Scene(stackPane);

        button.setOnAction(e -> Main.getAlertWindow().setScene(Main.getSellScene()));

        Main.getAlertWindow().setScene(scene);
        if (!(Main.getAlertWindow().isShowing())) Main.getAlertWindow().showAndWait();
    }

    protected void sold(String item, short amount, short profit){

        Main.store.playPurchaseSound();

        Gang.setMoney(Gang.getMoney() + profit);

        Button button = new Button("Back");
        Label label = new Label("You sold "+amount+" "+item+" for $"+profit);
        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: white;");

        VBox stackPane = new VBox();
        stackPane.setStyle("-fx-background-color: #cf1020");
        stackPane.setPadding(new Insets(20,20,20,20));
        stackPane.getChildren().addAll(label,button);

        Scene scene = new Scene(stackPane);

        button.setOnAction(e -> Main.getAlertWindow().setScene(Main.getSellScene()));

        Main.getAlertWindow().setScene(scene);
        if (!(Main.getAlertWindow().isShowing())) Main.getAlertWindow().showAndWait();
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
        if (!(Main.getAlertWindow().isShowing())) button.setOnAction(e -> Main.getAlertWindow().close());
        else if (Main.getAlertWindow().getScene() == Main.getDealerScene()) button.setOnAction(e -> Main.getAlertWindow().setScene(Main.getDealerScene()));
        else if (Main.getAlertWindow().getScene() == Main.getGunStoreScene()) button.setOnAction(e -> Main.getAlertWindow().setScene(Main.getGunStoreScene()));

        Main.getAlertWindow().setScene(scene);
        Main.getAlertWindow().setTitle("PURCHASED");
        if (!(Main.getAlertWindow().isShowing())) Main.getAlertWindow().showAndWait();
    }

    /**
     *
     * Updates the city scene and sets the alert stage to it
     *
     */
    protected void cityEvent() {

        // Settlement menu pane
        try {
            Main.setCityPane(FXMLLoader.load(Main.class.getResource("CityMenu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Main.setHirePane(FXMLLoader.load(Main.class.getResource("HireMenu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.setHireScene(new Scene(Main.getHirePane()));

        TravelController.distanceSinceCity = 0;

        Main.setCityScene(new Scene(Main.getCityPane()));

        //Stop moving
        Gang.setMoving(false);

        Main.getAlertWindow().setTitle("City");
        Main.getAlertWindow().setScene(Main.getCityScene());
        Main.getAlertWindow().show();
    }

    /**
     *
     * Sets the scene to the game over scene, and will close the whole program on exit
     *
     */
    protected static void gameOver(){

        // Game over pane
        try {
            gameOverPane = FXMLLoader.load(Main.class.getResource("GameOver.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // On closure of window, closes main MainWindow also
        Main.getAlertWindow().setOnCloseRequest(e -> {

            Gang.setMoving(false);
            Main.getMainWindow().close();
        });

        Main.getAlertWindow().setScene(new Scene(gameOverPane));

        Main.getAlertWindow().setTitle("GAME-OVER");

        Main.getAlertWindow().showAndWait();
    }
}