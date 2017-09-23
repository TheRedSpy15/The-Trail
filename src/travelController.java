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
                        }else if (EncounterChance >= 78){

                            alt.thiefEncounter();
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

        if (Distance - 2500 <= 15){

            Distance = 2485;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            TownSelector++;
        }

        if (Distance - 2000 <= 15){

            Distance = 1945;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            TownSelector++;
        }

        if (Distance - 1500 <= 15){

            Distance = 1485;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            TownSelector++;
        }

        if (Distance - 1000 <= 15){

            Distance = 985;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            TownSelector++;
        }

        if (Distance - 500 <= 15){

            Distance = 485;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
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
