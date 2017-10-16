import javafx.application.Platform;
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

    protected Scene notEnoughMoneyScene;
    protected Scene EmptyNameScene;

    protected static void alertMenuStart(){

        // Creating fxml scene objects

        // Game over pane
        try {
            gameOverPane = FXMLLoader.load(Main.class.getResource("GameOver.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Game won pane
        try {
            gameWonPane = FXMLLoader.load(Main.class.getResource("GameWon.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Block events to other windows
        AlertWindow.initModality(Modality.APPLICATION_MODAL);
        AlertWindow.setTitle("Alert");
    }

    private void shootoutThread(){


    }

    protected void thiefEncounter(){

        IsMoving = false;

        VBox EncounterLayout = new VBox(10);
        EncounterLayout.setStyle("-fx-background-color: black");
        EncounterLayout.setPadding(new Insets(40,20,20,20));

        EncounterLbl = new Label("You have encountered a thief!");
        EncounterLbl.setFont(new Font(20));
        EncounterLbl.setStyle("-fx-text-fill: red;");
        EncounterLbl.setFont(new Font(20));
        Button Choice1 = new Button("Let them go");
        Button Choice2 = new Button("Shoot them");
        Button Choice3 = new Button("Turn them in for a reward");

        ThiefMoney = rand.nextInt(500)+15;
        ThiefIsAlive = true;

        Choice1.setOnAction(e -> AlertWindow.close());

        Choice2.setOnAction(event -> shootoutThread());

        Choice3.setOnAction(e -> {

            TurnInThief = true;

            AlertWindow.close();
        });

        EncounterLayout.getChildren().addAll(EncounterLbl,Choice1,Choice2,Choice3);
        ThiefScene = new Scene(EncounterLayout, 320,320);
        AlertWindow.setScene(ThiefScene);
        AlertWindow.setTitle("Thief encounter");
        AlertWindow.showAndWait();
    }

    protected void purchased(String item){

        StackPane stackPane = new StackPane();
        stackPane.setStyle("-fx-background-color: #cf1020");
        stackPane.setPadding(new Insets(20,20,20,20));
        Label label = new Label("PURCHASED: "+item);
        label.setStyle("-fx-text-fill: white;");
        label.setFont(new Font(20));
        stackPane.getChildren().add(label);
        Scene scene = new Scene(stackPane);
        AlertWindow.setScene(scene);
    }

    protected void cityEvent() {

        // Settlement menu pane
        try {
            cityPane = FXMLLoader.load(Main.class.getResource("CityMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        CityScene = new Scene(cityPane);

        //Stop moving
        IsMoving = false;

        AlertWindow.setTitle("City");
        AlertWindow.setScene(CityScene);
        AlertWindow.show();
    }

    protected void notEnoughMoney(int amountOver){

        StackPane ntEnoughMoneyLayout = new StackPane();
        ntEnoughMoneyLayout.setStyle("-fx-background-color: #cf1020");
        Label label = new Label("Amount over: "+ amountOver);
        label.setStyle("-fx-text-fill: white;");
        label.setFont(new Font(20));
        ntEnoughMoneyLayout.setPadding(new Insets(20,20,20,20));
        ntEnoughMoneyLayout.getChildren().add(label);
        notEnoughMoneyScene = new Scene(ntEnoughMoneyLayout,300,250);
        AlertWindow.setScene(notEnoughMoneyScene);
        AlertWindow.setTitle("Not enough money");
        AlertWindow.show();
    }

    protected void emptyNames(){

        StackPane EmptyNameLayout = new StackPane();
        EmptyNameLayout.setStyle("-fx-background-color: #cf1020");
        Label label = new Label("Text fields cannot be blank!");
        label.setStyle("-fx-text-fill: white;");
        label.setFont(new Font(20));
        EmptyNameLayout.setPadding(new Insets(20,20,20,20));
        EmptyNameLayout.getChildren().add(label);
        EmptyNameScene = new Scene(EmptyNameLayout,300,250);
        AlertWindow.setScene(EmptyNameScene);
        AlertWindow.setTitle("empty name(s)");
        AlertWindow.show();
    }

    protected static void gameOver(){

        // On closure of window, closes main MainWindow also
        AlertWindow.setOnCloseRequest(e -> MainWindow.close());

        // makes game over pane a scene and sets the alert window scene to it
        AlertWindow.setScene(new Scene(gameOverPane));

        // sets alert window title to game over
        AlertWindow.setTitle("GAME-OVER");

        // shows alert window
        AlertWindow.show();
    }

    protected static void gameWon(){

        // On closure of window, closes main MainWindow also
        AlertWindow.setOnCloseRequest(e -> MainWindow.close());

        // makes game over pane a scene and sets the alert window scene to it
        AlertWindow.setScene(new Scene(gameWonPane));

        // sets alert window title to game over
        AlertWindow.setTitle("You win!!!");

        // shows alert window
        AlertWindow.show();
    }

    protected void ifWon(){

        if (Distance <= 0 ) gameWon();
    }
}