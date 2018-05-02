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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;

import java.io.IOException;

/**
 * Contains multiple methods / scenes, that are
 * modal, and are used to alert / notify the user
 *
 * NOTE: this class WILL see MAJOR changes
 * in the near future, it is quite buggy
 */
public class AlertBox {

    private static Parent gameOverPane;

    protected static void alertMenuStart(){

        Main.getAlertWindow().setOnCloseRequest(e -> Travel.travelSceneSetup());

        //Block events to other windows
        Main.getAlertWindow().initModality(Modality.APPLICATION_MODAL);
        Main.getAlertWindow().setTitle("Alert");
    }

    AlertBox() { Main.getAlertWindow().setTitle("Alert"); }

    /**
     * launches the thief encounter event
     */
    protected void thiefEncounter(){

        Main.gang.setMoving(false);
        Main.getAlertWindow().setTitle("Thief");

        // shoot out pane
        try {
            Main.main.setShootOutPane(FXMLLoader.load(Main.class.getResource("ShootOut.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.main.setShootOutScene(new Scene(Main.main.getShootOutPane()));

        // Thief menu pane
        try {
            Main.main.setThiefMenuPane(FXMLLoader.load(Main.class.getResource("ThiefMenu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.main.setThiefMenuScene(new Scene(Main.main.getThiefMenuPane()));

        Main.getAlertWindow().setScene(Main.main.getThiefMenuScene());
        Main.getAlertWindow().showAndWait();
    }

    /**
     * Creates a scene, that has a label with a message, and is modal
     *
     * @param alertMSG String to be turned into a label
     */
    protected void alert(String alertMSG){

        Label label = new Label(alertMSG);
        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: white;");

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #cf1020");
        stackPane.setPadding(new Insets(20,20,20,20));
        stackPane.getChildren().add(label);

        Scene scene = new Scene(stackPane);
        Main.getAlertWindow().setScene(scene);

        if (!(Main.getAlertWindow().isShowing())) Main.getAlertWindow().showAndWait();
    }

    protected void amountOver(int amount){

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

        else if (Main.getAlertWindow().getScene() == Main.main.getDealerScene()) button.setOnAction(e ->
                Main.getAlertWindow().setScene(Main.main.getDealerScene()));

        else if (Main.getAlertWindow().getScene() == Main.main.getGunStoreScene()) button.setOnAction(e ->
                Main.getAlertWindow().setScene(Main.main.getGunStoreScene()));

        else if (Main.getAlertWindow().getScene() == Main.main.getHireScene()) button.setOnAction(e ->
                Main.getAlertWindow().setScene(Main.main.getHireScene()));

        Main.getAlertWindow().setScene(scene);
        Main.getAlertWindow().setTitle("Not enough money");
        if (!(Main.getAlertWindow().isShowing())) Main.getAlertWindow().showAndWait();
    }

    protected void cannotSell(String item){

        Button button = new Button("Back");
        Label label = new Label("You cannot sell your current "+ item);
        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: white;");

        VBox stackPane = new VBox();
        stackPane.setStyle("-fx-background-color: #cf1020");
        stackPane.setPadding(new Insets(20,20,20,20));
        stackPane.getChildren().addAll(label,button);

        Scene scene = new Scene(stackPane);

        button.setOnAction(e -> Main.getAlertWindow().setScene(Main.main.getSellScene()));

        Main.getAlertWindow().setScene(scene);
        if (!(Main.getAlertWindow().isShowing())) Main.getAlertWindow().showAndWait();
    }

    protected void sold(String item, short amount, short value){

        Main.store.playPurchaseSound();

        Main.gang.setMoney(Main.gang.getMoney() + value);

        Button button = new Button("Back");
        Label label = new Label("You sold "+amount+" "+item+" for $"+value);
        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: white;");

        VBox stackPane = new VBox();
        stackPane.setStyle("-fx-background-color: #cf1020");
        stackPane.setPadding(new Insets(20,20,20,20));
        stackPane.getChildren().addAll(label,button);

        Scene scene = new Scene(stackPane);

        button.setOnAction(e -> Main.getAlertWindow().setScene(Main.main.getSellScene()));

        Main.getAlertWindow().setScene(scene);
        if (!(Main.getAlertWindow().isShowing())) Main.getAlertWindow().showAndWait();
    }

    /**
     * Similar to alert(), except,
     * it uses the string in the parameter
     * to properly notify user of purchase.
     *
     * Different from store.purchase(), as that
     * uses this method for notification.
     *
     * @param item name of item purchased
     */
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

        else if (Main.getAlertWindow().getScene() == Main.main.getDealerScene()) button.setOnAction(e ->
                Main.getAlertWindow().setScene(Main.main.getDealerScene()));

        else if (Main.getAlertWindow().getScene() == Main.main.getGunStoreScene()) button.setOnAction(e ->
                Main.getAlertWindow().setScene(Main.main.getGunStoreScene()));

        Main.getAlertWindow().setScene(scene);
        Main.getAlertWindow().setTitle("PURCHASED");
        if (!(Main.getAlertWindow().isShowing())) Main.getAlertWindow().showAndWait();
    }

    /**
     * Updates the city scene and
     * sets the alert stage to it
     */
    protected void cityEvent() {

        // Settlement menu pane
        try {
            Main.main.setCityPane(FXMLLoader.load(Main.class.getResource("CityMenu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            Main.main.setHirePane(FXMLLoader.load(Main.class.getResource("HireMenu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Main.main.setHireScene(new Scene(Main.main.getHirePane()));

        Main.gang.setDistanceSinceCity((short) 0);

        Main.main.setCityScene(new Scene(Main.main.getCityPane()));

        //Stop moving
        Main.gang.setMoving(false);

        Main.main.getMainWindow().setTitle("City");
        Main.main.getMainWindow().setScene(Main.main.getCityScene());
    }

    /**
     * Sets the scene to the game over scene, and
     * will close the whole program on exit
     */
    protected static void gameOver(){

        // Game over pane
        try {
            gameOverPane = FXMLLoader.load(Main.class.getResource("GameOver.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // On closure of window, closes main MainWindow also
        Main.getAlertWindow().setOnCloseRequest(e -> System.exit(0));

        Main.getAlertWindow().setScene(new Scene(gameOverPane));

        Main.getAlertWindow().setTitle("GAME-OVER");

        if (!Main.getAlertWindow().isShowing()) Main.getAlertWindow().showAndWait();
    }
}