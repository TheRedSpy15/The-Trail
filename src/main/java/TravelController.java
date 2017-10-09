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

    // when stop button is pressed
    @FXML
    private void stopMoving(){

        IsMoving = false;
    }

    // when set out button is pressed
    @FXML
    private void setOut(){

        runBackgroundTask();
    }

    // when menu button is pressed
    @FXML
    private void menu(){

        // Runs mid menu
        MidGameMenu.menuMethod();

        IsMoving = false;
        MenuWindow.showAndWait();
    }

    private void runBackgroundTask() {

        new Thread(() -> {

            // Run your background task(s) here
            IsMoving = true;

            while (IsMoving) {

                SickEventChance = rand.nextInt(20)+1;
                EncounterChance = rand.nextInt(100)+1;

                ++Days;
                Distance-=Pace;
                Food -= (PosseLinkedList.size()*FoodIntake);
                Water=Water-(PosseLinkedList.size()*FoodIntake);

                if (Food < 0) Food = 0;
                if (Water < 0) Water = 0;

                // health conditions
                HealthClass.determineHealthCondition();

                Platform.runLater(() -> {
                    // update the JavaFX UI Thread here when the task(s) above are done

                    // updating labels
                    distanceLabel.setText("To go: "+Distance+"Mi");
                    daysLabel.setText("Days: "+Days);
                    statusLabel.setText("Status: Moving");
                    conditionsLabel.setText("Condition: "+HealthConditions);
                    setOutBtn.setText("Speed up");

                    //Thief encounter
                    if (extremeLowChance()) alt.thiefEncounter();

                    // Settlement countdown
                    townCountDown();

                    // check if won
                    alt.ifWon();

                    if (HealthEventCooldown >= 10){

                        if (SickEventChance > HealthConditions){

                            PlayerSelectForEvent = PosseLinkedList.size();
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

        // if statements for how close the new settlement distance is to player distance (triggers within 15 distance)
        if (Distance - 29000 <= 15 && Distance - 29000 >= -15){

            Distance = 28980;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            TownSelector = 4;
        }

        if (Distance - 25000 <= 15 && Distance - 25000 >= -15){

            Distance = 24900;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            TownSelector = 3;
        }

        if (Distance - 20000 <= 15 && Distance - 20000 >= -15){

            Distance = 19980;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            TownSelector = 2;
        }

        if (Distance - 10000 <= 15 && Distance - 10000 >= -15){

            Distance = 9980;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            TownSelector = 1;
        }

        if (Distance - 3500 <= 15 && Distance - 3500 >= -15){

            TownSelector = 0;
            Distance = 3480;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.townEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
        }
    }

    // on initialization of scene
    @FXML
    public void initialize(){

        // updating labels
        distanceLabel.setText("To go: "+Distance+"Mi");
        conditionsLabel.setText("Condition: "+HealthConditions);
        daysLabel.setText("Days: "+Days);
        statusLabel.setText("Status: Resting");
    }
}
