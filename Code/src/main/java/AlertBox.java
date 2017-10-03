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
    protected Scene SettlementScene;
    private Label bountyLbl;

    protected static void alertMenuStart(){

        // Creating fxml scene objects
        // Game over pane
        try {
            gameOverPane = FXMLLoader.load(Main.class.getResource("GameOver.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameOverPane.setStyle("-fx-background-color: #cf1020");

        // Game won pane
        try {
            gameWonPane = FXMLLoader.load(Main.class.getResource("GameWon.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameWonPane.setStyle("-fx-background-color: #cf1020");

        //Block events to other windows
        AlertWindow.initModality(Modality.APPLICATION_MODAL);
        AlertWindow.setTitle("Alert");
    }

    private void runBackgroundTask2(){

        StackPane pane = new StackPane();
        Label label = new Label("");
        pane.setPadding(new Insets(20,20,20,20));
        pane.getChildren().add(label);
        Scene scene = new Scene(pane,300,250);
        AlertWindow.setScene(scene);

        new Thread(() -> Platform.runLater(() -> {

            if (Ammo > 0){

                label.setText("You shot a round!");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (Ammo > 0 && ThiefIsAlive) for (int i = 0; i < 5; i++) {

                    if (rand.nextBoolean()) {

                        label.setText("You killed the thief!");
                        ThiefIsAlive = false;

                    }else{

                        label.setText("You missed! Firing again!");
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

            if (Ammo <= 0){

                label.setText("You have no bullets and the thief got away with $"+ThiefMoney);
                Money-=ThiefMoney;

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                AlertWindow.close();

            }else{

                label.setText("The thief got away with $"+ThiefMoney);
                Money-=ThiefMoney;

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                AlertWindow.close();
            }
        })).start();
    }

    protected void thiefEncounter(){

        IsMoving = false;

        VBox EncounterLayout = new VBox(10);
        EncounterLayout.setPadding(new Insets(40,20,20,20));

        EncounterLbl = new Label("You have encountered a thief!");
        EncounterLbl.setFont(new Font(20));
        Button Choice1 = new Button("Let them go");
        Button Choice2 = new Button("Shoot them");
        Button Choice3 = new Button("Turn them in for a reward");

        ThiefMoney = rand.nextInt(500)+15;
        ThiefIsAlive = true;

        Choice1.setOnAction(e -> {

            EncounterLbl.setText("The thief gave you some money in return");
            Money+=ThiefMoney;
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            AlertWindow.close();
        });

        Choice2.setOnAction(event -> runBackgroundTask2());

        Choice3.setOnAction(e -> {

            TurnInThief = true;

            EncounterLbl.setText("At the next settlement you will receive the reward");

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }

            AlertWindow.close();
        });

        EncounterLayout.getChildren().addAll(EncounterLbl,Choice1,Choice2,Choice3);
        ThiefScene = new Scene(EncounterLayout, 320,300);
        AlertWindow.setScene(ThiefScene);
        AlertWindow.setTitle("Thief encounter");
        AlertWindow.showAndWait();
    }

    protected void townEvent() {

        //Stop moving
        IsMoving = false;

        // Settlement Scene
        VBox SettlementLayout = new VBox(10);
        Label SettlementLbl = new Label("You have come up to the town of "+TownList[TownSelector]);
        bountyLbl = new Label("");
        Button UseShop = new Button("Use shop");
        Button SellItems = new Button("Sell items");
        UseShop.setOnAction(e -> AlertWindow.setScene(new Scene(midStorePane)));
        Button KeepGoing = new Button("Keep going");
        Button ClaimRewardBtn = new Button("Claim Thief Bounty");
        ClaimRewardBtn.setOnAction(e -> {

            if (TurnInThief){

                bountyMethod();
            }else{

                bountyLbl.setText("Sorry... you haven't caught anyone ( NO BOUNTY 4 U !! )");
            }
        });
        KeepGoing.setOnAction(e -> {

            Distance -= 25;
            AlertWindow.close();
        });
        SellItems.setOnAction(e -> AlertWindow.setScene(new Scene(midSellStorePane)));
        SettlementLayout.setPadding(new Insets(20,20,20,20));
        SettlementLayout.getChildren().addAll(SettlementLbl,bountyLbl,UseShop,KeepGoing,ClaimRewardBtn);
        SettlementScene = new Scene(SettlementLayout,320,300);
        AlertWindow.setScene(SettlementScene);
        AlertWindow.setTitle("Settlement");
        AlertWindow.showAndWait();
    }

    protected void bountyMethod(){

        int MoneyToClaim;

        MoneyToClaim = rand.nextInt(1000)+500;

        bountyLbl.setText("You have Claimed: $"+MoneyToClaim);

        Money+=MoneyToClaim;

        TurnInThief = false;
    }

    protected void notEnoughMoney(){

        StackPane ntEnoughMoneyLayout = new StackPane();
        Label label = new Label("Amount over: "+ (int) amountOver);
        label.setFont(new Font(20));
        ntEnoughMoneyLayout.setPadding(new Insets(20,20,20,20));
        ntEnoughMoneyLayout.getChildren().add(label);
        notEnoughMoneyScene = new Scene(ntEnoughMoneyLayout,300,250);
        AlertWindow.setScene(notEnoughMoneyScene);
        AlertWindow.setTitle("Not enough money");
        AlertWindow.show();

        amountOver = 0;
    }

    protected void emptyNames(){

        StackPane EmptyNameLayout = new StackPane();
        EmptyNameLayout.setStyle("-fx-background-color: #cf1020");
        Label label = new Label("Text fields cannot be blank!");
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