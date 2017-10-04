import java.util.LinkedList;
import java.util.Random;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import static javafx.scene.media.AudioClip.INDEFINITE;

public class Main extends Application{

    static Stage MainWindow;
    private static Scene FoodPortionsScene;
    static Stage MenuWindow;
    private static Scene MenuScene;
    static Scene InventoryScene;
    static Scene SickEventScene;
    static Stage AlertWindow = new Stage();
    static Scene ThiefScene;
    private static Scene PaceScene;
    static Parent careerAnchor;
    static Parent midStorePane;
    static Label EncounterLbl;
    static Parent storePane;
    static Parent possePane;
    static Parent travelPane;
    static Parent gameOverPane;
    static Parent gameWonPane;
    static Parent midSellStorePane;

    static LinkedList <String> PosseLinkedList = new LinkedList<>();
    static int HealthConditions = 100;
    static int Distance = 30000;
    static int Days = 0;
    static int Food = 0;
    static int ThiefMoney;
    static boolean ThiefIsAlive;
    static int TownSelector = 4;
    static int Ammo = 0;
    static int Water = 0;
    static double amountOver = 0;
    static double Money = 0;
    static int Pace = 10;
    static int FoodIntake = 2;
    static Random rand = new Random();
    static int SickEventChance;
    static int PlayerSelectForEvent;
    static int EncounterChance;
    static TravelClass travel = new TravelClass();
    static boolean IsMoving = false;
    static boolean TurnInThief = false;
    static CareerPosse cp = new CareerPosse();
    static String TownList[]={"Salem, Oregon", "Denver, Colorado", "Frankfort, Kentucky", "Atlanta, Georgia", "Tallahassee, Florida"};

    public static void main(String args[]) {

        // Making audio thread object
        Thread audioThread = new Thread(musicTask);

        // Starting the audio thread
        audioThread.start();

        // Launching Javafx thread
        launch(args);
    }

    // Getters and Setters
    @Contract(pure = true)
    static Scene getFoodPortionsScene() {
        return FoodPortionsScene;
    }

    static void setFoodPortionsScene(Scene foodPortionsScene) {
        FoodPortionsScene = foodPortionsScene;
    }

    @Contract(pure = true)
    static Scene getMenuScene() {
        return MenuScene;
    }

    static void setMenuScene(Scene menuScene) {
        MenuScene = menuScene;
    }

    @Contract(pure = true)
    static Scene getPaceScene() {
        return PaceScene;
    }

    static void setPaceScene(Scene paceScene) {
        PaceScene = paceScene;
    }

    static boolean extremeLowChance(){

        // adds a random value to chance between 200 and 1
        int Chance = (int)(Math.random()*200+1);

        // returns true if chance is equal to 100
        return Chance == 100;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // assigns main anchor object to start scene fxml file
        Parent mainAnchor = FXMLLoader.load(getClass().getResource("StartScene.fxml"));

        // runs the alert menu start method via alert box object
        AlertBox.alertMenuStart();

        // Start
        // Initializing MainWindow as primaryStage object;
        MainWindow = primaryStage;

        // Setting title of MainWindow
        MainWindow.setTitle("The Trail");

        // Setting the scene as the start scene via mainAnchor object
        MainWindow.setScene(new Scene(mainAnchor));

        // Showing the window
        MainWindow.show();
    }

    // Music task
    private static final Task musicTask = new Task() {

        @Nullable
        @Override
        protected Object call() throws Exception {

            // making object for specified .wav file
            AudioClip audio = new AudioClip(getClass().getResource("Wild_Western_Music_-_Tumbleweed_Town.wav").toExternalForm());

            // Setting volume
            audio.setVolume(0.5f);

            // Setting loop
            audio.setCycleCount(INDEFINITE);

            // Starting audio
            audio.play();
            return null;
        }
    };
}