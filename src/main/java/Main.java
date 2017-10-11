import java.util.LinkedList;
import java.util.Random;
// import org.apache.logging.log4j.*;
import io.sentry.*;
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

    // Javafx
    static Stage MainWindow;
    private static Scene FoodPortionsScene;
    static Stage MenuWindow;
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
    static Parent settlementPane;
    static Parent midSellStorePane;

    // Sentry
    private static final String dsn = "https://6db11d4c3f864632aa5b1932f6c80c82:6349615319974befbcb63a2459b5fc26@sentry.io/220483";
    // private static Logger logger = LogManager.getLogger(Main.class);

    // Core Java
    static LinkedList <String> gangLinkedList = new LinkedList<>();
    static int HealthConditions = 100;
    static int Distance = 30000;
    static int Days = 0;
    static int Food = 0;
    static int ThiefMoney;
    static boolean ThiefIsAlive;
    static int CitySelector = 4;
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
    static AlertBox alert = new AlertBox();
    static Store store = new Store();
    static boolean IsMoving = false;
    static boolean TurnInThief = false;
    static CareerGang cp = new CareerGang();
    static String CityList[]={"Salem, Oregon", "Denver, Colorado", "Frankfort, Kentucky", "Atlanta, Georgia", "Tallahassee, Florida"};

    /* BUG LIST

    *

     */

    public static void main(String args[]) {

        // Launches sentry with the dsn in the parameters
        Sentry.init(dsn);

        /*
        sends a message to the sentry dsn server
        logger.error("test ");

        works with other methods like: logger.fatal , logger.debug
        of which change the tag on the sentry dsn server
        */

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

        // prevents resizing of stage
        MainWindow.setResizable(false);

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
            AudioClip audio = new AudioClip(getClass().getResource("In_Seoul_Retro_80.wav").toExternalForm());

            // Setting volume
            audio.setVolume(0.3f);

            // Setting loop
            audio.setCycleCount(INDEFINITE);

            // Starting audio
            audio.play();
            return null;
        }
    };
}