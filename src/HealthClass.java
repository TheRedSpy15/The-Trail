import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class HealthClass extends Main {

    // Used to determine health condition based upon tiredness, pace, and intake
    static void DetermineHealthCondition(){

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
    void PoorHealthEvent(){

        VBox PoorHealthLayout = new VBox(10);
        PoorHealthLayout.setPadding(new Insets(40,20,20,20));

        Label SickEventLbl = new Label("");

        PoorHealthLayout.getChildren().add(SickEventLbl);
        SickEventScene = new Scene(PoorHealthLayout,320,200);

        if (SickEventChance <= 10){

            SickEventLbl.setText(PlayersArray.get(PlayerSelectForEvent)+" did not wake up...");

            HealthConditions+=60;

            PlayersArray.remove(PlayerSelectForEvent);
        }else{

            switch (SickEventChance){

                case 11:
                    SickEventLbl.setText(PlayersArray.get(PlayerSelectForEvent)+" Got a cold");
                    HealthConditions+=5;
                    break;
                case 12:
                    SickEventLbl.setText(PlayersArray.get(PlayerSelectForEvent)+" Has a Fever");
                    HealthConditions+=10;
                    break;
                case 13:
                    SickEventLbl.setText(PlayersArray.get(PlayerSelectForEvent)+" Broke a leg");
                    HealthConditions+=10;
                    break;
                case 14:
                    SickEventLbl.setText(PlayersArray.get(PlayerSelectForEvent)+" Broke an arm");
                    HealthConditions+=10;
                    break;
                case 15:
                    SickEventLbl.setText(PlayersArray.get(PlayerSelectForEvent)+" Threw up");
                    HealthConditions+=10;
                    break;
                case 16:
                    SickEventLbl.setText(PlayersArray.get(PlayerSelectForEvent)+" Has an infection");
                    HealthConditions+=15;
                    break;
                case 17:
                    SickEventLbl.setText(PlayersArray.get(PlayerSelectForEvent)+" Has the flu");
                    HealthConditions+=15;
                    break;
                default:
                    SickEventLbl.setText(PlayersArray.get(PlayerSelectForEvent)+" Is sick");
                    HealthConditions+=10;
            }
        }
    }
}
