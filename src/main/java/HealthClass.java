import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class HealthClass extends Main {

    // Used to determine health condition based upon tiredness, pace, and intake
    protected static void determineHealthCondition(){

        switch (Pace){

            case 15:
                HealthConditions-=3;
                break;
            case 10:
                HealthConditions+=1;
                break;
            case 1:
                HealthConditions+=2;
                break;
        }

        switch (FoodIntake){

            case 1:
                if (Food != 0) HealthConditions -= 5;
                break;
            case 2:
                if (Food != 0) HealthConditions+=1;
                break;
            case 3:
                if (Food != 0) HealthConditions += 5;
                break;
        }

        if (Food == 0){

            if (Water == 0){

                HealthConditions -= 10;
            }

            HealthConditions -= 10;
        }

        if (HealthConditions > 100) HealthConditions = 100;
        if (HealthConditions < 0) HealthConditions = 0;
    }

    // Events to happen with poor health conditions
    protected static void poorHealthEvent(){

        IsMoving = false;

        VBox PoorHealthLayout = new VBox(10);
        PoorHealthLayout.setPadding(new Insets(40,20,20,20));

        Label SickEventLbl = new Label("");
        SickEventLbl.setStyle("-fx-text-fill: purple;");
        SickEventLbl.setFont(new Font(20));

        PoorHealthLayout.setStyle("-fx-background-color: BLACK;");
        PoorHealthLayout.getChildren().add(SickEventLbl);
        SickEventScene = new Scene(PoorHealthLayout,320,200);
        AlertWindow.setScene(SickEventScene);

        if (SickEventChance <= 10){

            SickEventLbl.setText(gangLinkedList.get(PlayerSelectForEvent)+" Passed away...");

            HealthConditions+=60;

            gangLinkedList.remove(PlayerSelectForEvent);

            AlertWindow.showAndWait();
        }else{

            switch (SickEventChance){

                case 11:
                    SickEventLbl.setText(gangLinkedList.get(PlayerSelectForEvent)+" Got a cold");
                    HealthConditions-=5;
                    AlertWindow.showAndWait();
                    break;
                case 12:
                    SickEventLbl.setText(gangLinkedList.get(PlayerSelectForEvent)+" Has a Fever");
                    HealthConditions-=10;
                    AlertWindow.showAndWait();
                    break;
                case 13:
                    SickEventLbl.setText(gangLinkedList.get(PlayerSelectForEvent)+" Broke a leg");
                    HealthConditions-=10;
                    AlertWindow.showAndWait();
                    break;
                case 14:
                    SickEventLbl.setText(gangLinkedList.get(PlayerSelectForEvent)+" Broke an arm");
                    HealthConditions-=10;
                    AlertWindow.showAndWait();
                    break;
                case 15:
                    SickEventLbl.setText(gangLinkedList.get(PlayerSelectForEvent)+" Threw up");
                    HealthConditions-=10;
                    AlertWindow.showAndWait();
                    break;
                case 16:
                    SickEventLbl.setText(gangLinkedList.get(PlayerSelectForEvent)+" Has an infection");
                    HealthConditions-=15;
                    AlertWindow.showAndWait();
                    break;
                case 17:
                    SickEventLbl.setText(gangLinkedList.get(PlayerSelectForEvent)+" Has the flu");
                    HealthConditions-=15;
                    AlertWindow.showAndWait();
                    break;
                default:
                    SickEventLbl.setText(gangLinkedList.get(PlayerSelectForEvent)+" Is sick");
                    HealthConditions-=10;
                    AlertWindow.showAndWait();
                    break;
            }
        }

        // Game over condition
        if (gangLinkedList.size() <= 0){

            IsMoving = false;

            AlertBox.gameOver();
        }
    }
}
