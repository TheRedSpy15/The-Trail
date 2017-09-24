import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TravelController extends Main {

    @FXML private Button setOutBtn;
    @FXML private Label distanceLabel;
    @FXML private Label conditionsLabel;
    @FXML private Label daysLabel;
    @FXML private Label statusLabel;
    private int HealthEventCooldown = 10;
    private AlertBox alt = new AlertBox();

    @FXML
    private void stopMoving(){

        IsMoving = false;
    }

    @FXML
    private void setOut(){

        runBackgroundTask();
    }

    @FXML
    private void menu(){

        IsMoving = false;
        MidGameMenu.menuMethod();
        MenuWindow.showAndWait();
    }

    private void runBackgroundTask() {

        new Thread(() -> {

            // Run your background task(s) here
            IsMoving = true;

            while (IsMoving) {

                SickEventChance = rand.nextInt(30)+1;
                EncounterChance = rand.nextInt(100)+1;

                ++Days;
                Distance-=Pace;
                Food -= (PlayersArray.size()*FoodIntake);
                Water=Water-(PlayersArray.size()*FoodIntake);

                if (Food < 0) Food = 0;
                if (Water < 0) Water = 0;

                HealthClass.determineHealthCondition();

                Platform.runLater(() -> {
                    // update the JavaFX UI Thread here when the task(s) above are done
                    distanceLabel.setText("To go: "+Distance+"Mi");
                    daysLabel.setText("Days: "+Days);
                    statusLabel.setText("Status: Moving");
                    conditionsLabel.setText("Condition: "+HealthConditions);
                    setOutBtn.setText("Speed up");

                    //Thief encounter
                    if (extremeLowChance()) alt.thiefEncounter();

                    // Settlement countdown
                    townCountDown();

                    if (HealthEventCooldown >= 10){

                        if (SickEventChance > HealthConditions){

                            System.out.println(SickEventChance);
                            PlayerSelectForEvent = PlayersArray.size();
                            PlayerSelectForEvent-=1;
                            HealthClass.poorHealthEvent();
                        }

                        HealthEventCooldown = 0;
                    }
                });

                HealthEventCooldown++;

                if (!IsMoving) break;

                try {
                    Thread.sleep(2400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            Platform.runLater(() -> {

                statusLabel.setText("Status: Resting");
                setOutBtn.setText("Set out");
            });

        }).start();
    }

    private void townCountDown(){

        if (Distance - 29000 <= 15 && Distance - 2500 >= -15){

            Distance = 28984;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            TownSelector = 4;
        }

        if (Distance - 25000 <= 15 && Distance - 2000 >= -15){

            Distance = 24984;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            TownSelector = 3;
        }

        if (Distance - 20000 <= 15 && Distance - 1500 >= -15){

            Distance = 19984;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            TownSelector = 2;
        }

        if (Distance - 10000 <= 15 && Distance - 1000 >= -15){

            Distance = 9984;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            TownSelector = 1;
        }

        if (Distance - 3500 <= 15 && Distance - 500 >= -15){

            TownSelector = 0;
            Distance = 3484;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
        }
    }

    @FXML
    public void initialize(){

        distanceLabel.setText("To go: "+Distance+"Mi");
        conditionsLabel.setText("Condition: "+HealthConditions);
        daysLabel.setText("Days: "+Days);
        statusLabel.setText("Status: Resting");
    }
}
