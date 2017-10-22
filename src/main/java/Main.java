import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
// import org.apache.logging.log4j.*;
import io.sentry.*;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    static Scene SickEventScene;
    static Stage AlertWindow = new Stage();
    private static Scene PaceScene;
    static Parent careerAnchor;
    static Parent midStorePane;
    static Parent storePane;
    static Parent possePane;
    static Parent travelPane;
    static Parent gameOverPane;
    static Parent gameWonPane;
    static Parent cityPane;
    static Parent midSellStorePane;
    static Scene cityScene;
    static Scene storeScene;
    static Parent dealerPane;
    static Scene dealerScene;
    static Parent gunStorePane;
    static Scene gunStoreScene;
    static Parent inventoryPane;
    static Scene inventoryScene;
    static Parent shootOutPane;
    static Scene shootOutScene;
    static Parent thiefMenuPane;
    static Scene thiefMenuScene;
    static Parent descripitionPane;

    // Sentry
    private static final String dsn = "https://6db11d4c3f864632aa5b1932f6c80c82:6349615319974befbcb63a2459b5fc26@sentry.io/220483";
    // private static Logger logger = LogManager.getLogger(Main.class);

    // Core Java
    static LinkedList <String> gang = new LinkedList<>();
    static int HealthConditions = 100;
    static int Distance = 5000;
    static String gunSpriteURL = "piq_119368_400x400.png";
    static int Score = 0;
    static String carSpriteURL = "spr_car4_0.png";
    static int Days = 0;
    static int wage = 0;
    static int Food = 0;
    static int Grenades = 0;
    static String gunID = "Glock";
    static int Attack = 15;
    static int citySelector = 4;
    static int Ammo = 0;
    static int Water = 0;
    static double Money = 0;
    static int Pace = 10;
    static int FoodIntake = 2;
    static Random rand = new Random();
    static int SickEventChance;
    static int memberSelect;
    static int EncounterChance;
    static TravelClass travel = new TravelClass();
    static AlertBox alert = new AlertBox();
    static Store store = new Store();
    static boolean IsMoving = false;
    static int capturedThieves = 0;
    static CareerGang cp = new CareerGang();
    static String cityList[]={"Salem, Oregon", "Denver, Colorado", "Frankfort, Kentucky", "Atlanta, Georgia", "Tallahassee, Florida"};

    /*

    BUG LIST

    *


    NEEDED FEATURES

    * different cars have different max storage ( eg: green car - 6000 storage [ storage = water + food ] )


    NICE TO HAVES

    * Support for resizing stage

    */

    public static void main(String args[]) {

        // setting up description scene, needs to be moved
        try {
            descripitionPane = FXMLLoader.load(Main.class.getResource("DescripitonScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Launches sentry with the dsn in the parameters
        Sentry.init(dsn);

        /*
        sends a error message to the sentry dsn server
        logger.error("test ");

        works with other methods like: logger.fatal / logger.debug / ETC
        of which change the tag on the sentry dsn server
        */

        // Music
        Thread audioThread = new Thread(musicTask);
        audioThread.start();

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

        int Chance = (int)(Math.random()*100+1);

        // returns true if
        // chance is equal to 100
        return Chance == 90;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AlertBox.alertMenuStart();

        Parent mainAnchor = FXMLLoader.load(getClass().getResource("StartScene.fxml"));

        // Start scene
        MainWindow = primaryStage;

        MainWindow.setResizable(false);

        MainWindow.setTitle("The Trail");

        MainWindow.setScene(new Scene(mainAnchor));

        MainWindow.show();
    }

    // Music task
    private static final Task musicTask = new Task() {

        @Nullable
        @Override
        protected Object call() throws Exception {

            // audio file NEEDS to be .wav
            AudioClip audio = new AudioClip(getClass().getResource("In_Seoul_Retro_80.wav").toExternalForm());

            audio.setVolume(0.3f);

            audio.setCycleCount(INDEFINITE);

            audio.play();
            return null;
        }
    };
}