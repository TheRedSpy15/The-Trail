import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

public class AlertBox extends Main {

    private Store str = new Store();
    private HealthClass hlt = new HealthClass();
    private Scene SettlementScene;
    private Scene notEnoughMoneyScene;
    private Scene EmptyNameScene;

    void AlertMenu(int SceneSelect){

        IsMoving = false;

        //Block events to other windows
        //window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Event");

        switch (SceneSelect){

            case 1:
                TownEvent();
                window.setScene(SettlementScene);
                break;
            case 2:
                hlt.PoorHealthEvent();
                window.setScene(SickEventScene);
                break;
            case 3:
                ThiefEncounter();
                window.setScene(ThiefScene);
                break;
            case 4:
                NotEnoughMoney();
                window.setScene(notEnoughMoneyScene);
                break;
            case 5:
                EmptyNames();
                window.setScene(EmptyNameScene);
                break;
        }

        window.show();
    }

    private void TownEvent() {

        // Settlement Scene
        VBox SettlementLayout = new VBox(10);
        Label SettlementLbl = new Label("You have come up to the town of "+TownList[TownSelector]);
        Label BountyLbl = new Label("");
        Button UseShop = new Button("Use shop");
        UseShop.setOnAction(e -> {
            window.setScene(new Scene(storePane));
        });
        Button KeepGoing = new Button("Keep going");
        Button ClaimRewardBtn = new Button("Claim Thief Bounty");
        ClaimRewardBtn.setOnAction(e -> BountyMethod());
        KeepGoing.setOnAction(e -> window.close());
        SettlementLayout.setPadding(new Insets(20,20,20,20));
        SettlementLayout.getChildren().addAll(SettlementLbl,BountyLbl,UseShop,KeepGoing,ClaimRewardBtn);
        SettlementScene = new Scene(SettlementLayout,320,300);
    }

    // Encounter events
    static void ThiefEncounter(){

        VBox EncounterLayout = new VBox(10);
        EncounterLayout.setPadding(new Insets(40,20,20,20));

        EncounterLbl = new Label("");
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

        Choice2.setOnAction(e -> ThiefShootout());

        Choice3.setOnAction(e -> {

            TurnInThief = true;

            EncounterLbl.setText("At the next settlement you will receive the reward");
        });

        EncounterLayout.getChildren().addAll(EncounterLbl,Choice1,Choice2,Choice3);
        ThiefScene = new Scene(EncounterLayout, 320,300);
    }

    // Triggered with Choice2 Button
    private static void ThiefShootout(){

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
        }
    }

    private void BountyMethod(){

         int MoneyToClaim;

        if (BountyClaimable){

            MoneyToClaim = rand.nextInt(1000)+500;

            BountyLbl.setText("You have Claimed: $"+MoneyToClaim);

            Money+=MoneyToClaim;

        }else {

            BountyLbl.setText("Sorry... you haven't caught anyone ( NO BOUNTY 4 U !! )");
        }
    }

    private void NotEnoughMoney(){

        StackPane ntEnoughMoneyLayout = new StackPane();
        Label label = new Label("Amount over: "+ (int) amountOver);
        ntEnoughMoneyLayout.setPadding(new Insets(20,20,20,20));
        ntEnoughMoneyLayout.getChildren().add(label);
        notEnoughMoneyScene = new Scene(ntEnoughMoneyLayout,300,250);

        amountOver = 0;
    }

    private void EmptyNames(){

        StackPane EmptyNameLayout = new StackPane();
        Label label = new Label("Text fields cannot be blank!");
        EmptyNameLayout.setPadding(new Insets(20,20,20,20));
        EmptyNameLayout.getChildren().add(label);
        EmptyNameScene = new Scene(EmptyNameLayout,300,250);
    }
}