import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class TravelController extends Main {

    public Button menuBtn;
    public Button stopBtn;
    public Button setOutBtn;
    public Label distanceLabel;
    public Label conditionsLabel;
    public Label daysLabel;
    public Label statusLabel;
    private int HealthEventCooldown = 10;
    private AlertBox alt = new AlertBox();

    public void stopMoving(){

        IsMoving = false;
    }

    public void setout(){

        runBackgroundTask();
    }

    public void menu(){

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
                Food=Food-(PlayersArray.size()*FoodIntake);
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

                    // Settlement countdown
                    townCountDown();

                    if (HealthEventCooldown >= 10){

                        if (SickEventChance > HealthConditions){

                            System.out.println(SickEventChance);
                            PlayerSelectForEvent = PlayersArray.size();
                            PlayerSelectForEvent-=1;
                            alt.alertMenu(2);
                        }else if (EncounterChance >= 78){

                            alt.thiefEncounter();
                        }

                        HealthEventCooldown = 0;
                    }
                });

                HealthEventCooldown++;

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
            alt.alertMenu(1);
            TownSelector++;
        }

        if (Distance - 2000 <= 15){

            Distance = 1945;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.alertMenu(1);
            TownSelector++;
        }

        if (Distance - 1500 <= 15){

            Distance = 1485;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.alertMenu(1);
            TownSelector++;
        }

        if (Distance - 1000 <= 15){

            Distance = 985;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.alertMenu(1);
            TownSelector++;
        }

        if (Distance - 500 <= 15){

            Distance = 485;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alt.alertMenu(1);
            TownSelector++;
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
