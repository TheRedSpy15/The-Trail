import javafx.application.Platform;
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
                    if (Distance == 2500 || Distance == 2000 || Distance == 1500 || Distance == 1000 || Distance == 500){

                        alt.alertMenu(1);
                        TownSelector++;
                    }

                    if (HealthEventCooldown >= 10){

                        if (SickEventChance > HealthConditions){

                            System.out.println(SickEventChance);
                            PlayerSelectForEvent = PlayersArray.size();
                            PlayerSelectForEvent-=1;
                            alt.alertMenu(2);
                        }else if (EncounterChance >= 78){

                            AlertBox.thiefEncounter();
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
}
