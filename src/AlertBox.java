import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import java.awt.event.ActionEvent;

public class AlertBox extends Main {

    private HealthClass hlt = new HealthClass();
    private Scene notEnoughMoneyScene;
    private Scene EmptyNameScene;
    private Scene EmptyAlertScene;
    private Scene SettlementScene;

    protected void alertMenu(int SceneSelect){

        IsMoving = false;

        //Block events to other windows
        //window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Event");

        switch (SceneSelect){

            case 1:
                townEvent();
                window.setScene(SettlementScene);
                break;
            case 2:
                hlt.poorHealthEvent();
                window.setScene(SickEventScene);
                break;
            case 3:
                thiefEncounter();
                window.setScene(ThiefScene);
                break;
            case 4:
                notEnoughMoney();
                window.setScene(notEnoughMoneyScene);
                break;
            case 5:
                emptyNames();
                window.setScene(EmptyNameScene);
                break;
            default:
                emptyAlert();
                window.setScene(EmptyAlertScene);
                break;
        }

        window.show();
    }

    private void runBackgroundTask(ActionEvent event){

        new Thread(() -> Platform.runLater(() -> {

            if (Ammo > 0){

                EncounterLbl.setText("You shot a round!");

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                while (Ammo > 0 && ThiefIsAlive) for (int i = 0; i < 5; i++) {

                    if (rand.nextBoolean()) {

                        EncounterLbl.setText("You killed the thief!");
                        ThiefIsAlive = false;

                    }else{

                        EncounterLbl.setText("You missed! Firing again!");
                    }

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

            if (Ammo <= 0){

                EncounterLbl.setText("You have no bullets and the thief got away with $"+ThiefMoney);
                Money-=ThiefMoney;

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                window.close();

            }else{

                EncounterLbl.setText("The thief got away with $"+ThiefMoney);
                Money-=ThiefMoney;

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }

                window.close();
            }
        })).start();
    }

    protected void thiefEncounter(){

        VBox EncounterLayout = new VBox(10);
        EncounterLayout.setPadding(new Insets(40,20,20,20));

        EncounterLbl = new Label("You have encountered a thief!");
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
        });

        Choice2.setOnAction(this::runBackgroundTask);

        Choice3.setOnAction(e -> {

            TurnInThief = true;

            EncounterLbl.setText("At the next settlement you will receive the reward");
        });

        EncounterLayout.getChildren().addAll(EncounterLbl,Choice1,Choice2,Choice3);
        ThiefScene = new Scene(EncounterLayout, 320,300);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        window.close();
    }

    private void townEvent() {

        // Settlement Scene
        VBox SettlementLayout = new VBox(10);
        Label SettlementLbl = new Label("You have come up to the town of "+TownList[TownSelector]);
        Label BountyLbl = new Label("");
        Button UseShop = new Button("Use shop");
        UseShop.setOnAction(e -> window.setScene(new Scene(midStorePane)));
        Button KeepGoing = new Button("Keep going");
        Button ClaimRewardBtn = new Button("Claim Thief Bounty");
        ClaimRewardBtn.setOnAction(e -> {

            if (TurnInThief){

                bountyMethod();
            }else{

                getBountyLbl().setText("Sorry... you haven't caught anyone ( NO BOUNTY 4 U !! )");
            }
        });
        KeepGoing.setOnAction(e -> window.close());
        SettlementLayout.setPadding(new Insets(20,20,20,20));
        SettlementLayout.getChildren().addAll(SettlementLbl,BountyLbl,UseShop,KeepGoing,ClaimRewardBtn);
        SettlementScene = new Scene(SettlementLayout,320,300);
    }

    protected void bountyMethod(){

        int MoneyToClaim;

        MoneyToClaim = rand.nextInt(1000)+500;

        getBountyLbl().setText("You have Claimed: $"+MoneyToClaim);

        Money+=MoneyToClaim;
    }

    private void notEnoughMoney(){

        StackPane ntEnoughMoneyLayout = new StackPane();
        Label label = new Label("Amount over: "+ (int) amountOver);
        ntEnoughMoneyLayout.setPadding(new Insets(20,20,20,20));
        ntEnoughMoneyLayout.getChildren().add(label);
        notEnoughMoneyScene = new Scene(ntEnoughMoneyLayout,300,250);

        amountOver = 0;
    }

    private void emptyNames(){

        StackPane EmptyNameLayout = new StackPane();
        Label label = new Label("Text fields cannot be blank!");
        EmptyNameLayout.setPadding(new Insets(20,20,20,20));
        EmptyNameLayout.getChildren().add(label);
        EmptyNameScene = new Scene(EmptyNameLayout,300,250);
    }

    private void emptyAlert(){

        StackPane EmptyAlertLayout = new StackPane();
        Label label = new Label("ERROR: No alertbox scene selected!");
        EmptyAlertLayout.setPadding(new Insets(20,20,20,20));
        EmptyAlertLayout.getChildren().add(label);
        EmptyAlertScene = new Scene(EmptyAlertLayout,300,250);
    }
}