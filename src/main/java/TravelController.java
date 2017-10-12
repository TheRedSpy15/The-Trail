import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class TravelController extends Main {

    private static int payDayCountdown = 0;
    private static int townCountDown = 0;
    static int animationSpeed = 15;
    private TranslateTransition transition;
    @FXML private Button setOutBtn;
    @FXML private Label distanceLabel;
    @FXML private Label conditionsLabel;
    @FXML private Label daysLabel;
    @FXML private Label statusLabel;
    @FXML private AnchorPane sprite;
    @FXML private ImageView spriteImage;
    private int healthEventCooldown = 10;

    // when stop button is pressed
    @FXML
    private void stopMoving(){

        transition.pause();
        IsMoving = false;
    }

    // when set out button is pressed
    @FXML
    private void setOut(){

        transition.play();
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

                Score += 25;

                ++Days;
                Distance-=Pace;
                Food -= (gangLinkedList.size()*FoodIntake);
                Water=Water-(gangLinkedList.size()*FoodIntake);

                if (Food < 0) Food = 0;
                if (Water < 0) Water = 0;
                if (Money > 10000) Money = 10000;

                // health conditions
                HealthClass.determineHealthCondition();

                Platform.runLater(() -> {
                    // update the JavaFX UI Thread here when the task(s) above are done

                    // updating labels
                    distanceLabel.setText("To go: "+Distance+"Mi");
                    daysLabel.setText("Days: "+Days);
                    statusLabel.setText("Status: Moving");
                    conditionsLabel.setText("Condition: "+HealthConditions);
                    setOutBtn.setText("Speedup");

                    //Thief encounter
                    if (extremeLowChance()) alert.thiefEncounter();

                    // Payday countdown
                    payDay();

                    // Settlement countdowns
                    CityCountDown();

                    // check if won
                    alert.ifWon();

                    if (healthEventCooldown >= 10){

                        if (SickEventChance > HealthConditions){

                            PlayerSelectForEvent = gangLinkedList.size();
                            PlayerSelectForEvent-=1;
                            HealthClass.poorHealthEvent();
                        }

                        healthEventCooldown = 0;
                    }
                });

                healthEventCooldown++;

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
                transition.pause();
            });

        }).start();
    }

    private void CityCountDown(){

        // if statements for how close the new settlement distance is to player distance (triggers within 15 distance)
        if (Distance - 2900 <= 15 && Distance - 2900 >= -15){

            Distance = 2880;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alert.cityEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            CitySelector = 4;
        }

        if (Distance - 2500 <= 15 && Distance - 2500 >= -15){

            Distance = 2480;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alert.cityEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            CitySelector = 3;
        }

        if (Distance - 2000 <= 15 && Distance - 2000 >= -15){

            Distance = 1980;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alert.cityEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            CitySelector = 2;
        }

        if (Distance - 1000 <= 15 && Distance - 1000 >= -15){

            Distance = 980;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alert.cityEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
            CitySelector = 1;
        }

        if (Distance - 350 <= 15 && Distance - 350 >= -15){

            CitySelector = 0;
            Distance = 380;
            distanceLabel.setText("To go: "+Distance+"Mi");
            alert.cityEvent();
            distanceLabel.setText("To go: "+Distance+"Mi");
        }
    }

    // on initialization of scene
    @FXML
    public void initialize(){

        // On exit of car dealer stage, update sprite image
        AlertWindow.setOnCloseRequest(e -> spriteImage.setId(spriteURL));

        // updating labels
        distanceLabel.setText("To go: "+Distance+"Mi");
        conditionsLabel.setText("Condition: "+HealthConditions);
        daysLabel.setText("Days: "+Days);
        statusLabel.setText("Status: Resting");

        // setting up how the animation will work
        transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animationSpeed));
        transition.setToX(-850);
        transition.setNode(sprite);
        transition.setCycleCount(Animation.INDEFINITE);

        // fixes the bug with thread not ending when stage was closed
        MainWindow.setOnCloseRequest(e -> IsMoving = false);
    }

    private void payDay(){

        ++payDayCountdown;

        if (payDayCountdown == 30){

            Money += wage;

            payDayCountdown = 0;
        }
    }
}
