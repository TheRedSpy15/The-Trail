import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class TravelController extends Main {

    private static int payDayCountdown = 0;
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
        transition.pause();
    }

    // when set out button is pressed
    @FXML
    private void setOut(){

        updateTransition();
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

                distanceSinceCity += Pace;

                // Payday countdown
                payDay();

                // resource consumption
                ++Days;
                Distance += Pace;
                Food -= (gang.size()*FoodIntake);
                Water -= (gang.size()*FoodIntake);

                if (Food < 0) Food = 0;
                if (Water < 0) Water = 0;
                if (Money > 10000) Money = 10000;
                if (Money < 0) Money = 0;

                // health conditions
                HealthClass.determineHealthCondition();

                Platform.runLater(() -> {
                    // update the JavaFX UI Thread here when the task(s) above are done

                    // city count down
                    if (distanceSinceCity > 500) alert.cityEvent();

                    // updating labels
                    distanceLabel.setText("Distance: "+Distance+"Mi");
                    daysLabel.setText("Days: "+Days);
                    statusLabel.setText("Status: Moving");
                    conditionsLabel.setText("Condition: "+HealthConditions);
                    setOutBtn.setText("Speedup");

                    //Thief encounter
                    if (extremeLowChance()) alert.thiefEncounter();

                    // health events
                    if (healthEventCooldown >= 10){

                        if (SickEventChance > HealthConditions){

                            memberSelect = gang.size();
                            memberSelect -=1;
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

    @FXML
    public void initialize(){

        AlertWindow.setOnCloseRequest(e -> spriteImage.setImage(new Image(carSpriteURL)));

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

    private void updateTransition(){

        spriteImage.setImage(new Image(carSpriteURL));

        // setting up how the animation will work
        transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(animationSpeed));
        transition.setToX(-850);
        transition.setNode(sprite);
        transition.setCycleCount(Animation.INDEFINITE);
    }
}
