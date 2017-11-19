import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;

import java.io.IOException;

public class AlertBox extends Main {

    protected static void alertMenuStart(){

        AlertWindow.setOnCloseRequest(e -> TravelClass.travelSetup());

        //Block events to other windows
        AlertWindow.initModality(Modality.APPLICATION_MODAL);
        AlertWindow.setTitle("Alert");
    }

    AlertBox() {

        AlertWindow.setTitle("Alert");
    }

    protected void thiefEncounter(){

        IsMoving = false;
        AlertWindow.setTitle("Thief");

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

        AlertWindow.setScene(thiefMenuScene);
        AlertWindow.showAndWait();
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
        AlertWindow.setScene(scene);

        if (!(AlertWindow.isShowing())) AlertWindow.showAndWait();
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
        if (!(AlertWindow.isShowing())) button.setOnAction(e -> AlertWindow.close());
        else if (AlertWindow.getScene() == dealerScene) button.setOnAction(e -> AlertWindow.setScene(dealerScene));
        else if (AlertWindow.getScene() == gunStoreScene) button.setOnAction(e -> AlertWindow.setScene(gunStoreScene));
        else if (AlertWindow.getScene() == hireScene) button.setOnAction(e -> AlertWindow.setScene(hireScene));

        AlertWindow.setScene(scene);
        AlertWindow.setTitle("Not enough money");
        if (!(AlertWindow.isShowing())) AlertWindow.showAndWait();
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
        if (!(AlertWindow.isShowing())) button.setOnAction(e -> AlertWindow.close());
        else if (AlertWindow.getScene() == dealerScene) button.setOnAction(e -> AlertWindow.setScene(dealerScene));
        else if (AlertWindow.getScene() == gunStoreScene) button.setOnAction(e -> AlertWindow.setScene(gunStoreScene));

        AlertWindow.setScene(scene);
        AlertWindow.setTitle("PURCHASED");
        if (!(AlertWindow.isShowing())) AlertWindow.showAndWait();
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
        IsMoving = false;

        AlertWindow.setTitle("City");
        AlertWindow.setScene(cityScene);
        AlertWindow.show();
    }

    protected static void gameOver(){

        // Game over pane
        try {
            gameOverPane = FXMLLoader.load(Main.class.getResource("GameOver.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // On closure of window, closes main MainWindow also
        AlertWindow.setOnCloseRequest(e -> MainWindow.close());

        // makes game over pane a scene and sets the alert window scene to it
        AlertWindow.setScene(new Scene(gameOverPane));

        // sets alert window title to game over
        AlertWindow.setTitle("GAME-OVER");

        // shows alert window
        AlertWindow.showAndWait();
    }
}