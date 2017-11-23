import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import static javafx.scene.media.AudioClip.INDEFINITE;

public class Main extends Application{

    // Javafx
    static int distanceSinceCity = 0;
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
    static Scene lootScene;
    static Parent lootPane;
    static Scene deadThiefScene;
    static Parent deadThiefPane;
    static Scene hireScene;
    static Parent hirePane;

    // Core Java
    private static String trailVersion = "1.5.1";
    static LinkedList <String> gang = new LinkedList<>();
    static int HealthConditions = 100;
    static int Distance = 0;
    static String gunSpriteURL = "piq_119368_400x400.png";
    static long Score = 0;
    static String carSpriteURL = "spr_car4_0.png";
    static int Days = 0;
    static int wage = 0;
    static int Food = 0;
    static int Grenades = 0;
    static String gunID = "Glock";
    static int baseAttackDamage = 15;
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
    static LinkedList<String> cities = new LinkedList<>();

    /*

    DO NOT FORGET TO INCLUDE LICENSE IN NEXT COMMIT AND ADD IT TO THE RELEASE ON GITHUB!
    DO NOT FORGET NOTICES EITHER!


    BUG LIST

    * Transition on travel scene rarely stops


    WORKING ON

    * Settings menu in mid game menu
    * stop background music in shootout and play shootout music
    * View gang member scene in mid game menu - IDEA: make a black text area (non-editable), in the middle of scene
      and print to it in a for-each loop
    * Abstraction
    * Removing "GOD" class


    NEEDED FEATURES (Coming)

    * Different cars have different max storage ( eg: green car - 6000 storage [ storage = water + food ] )
    * Body armour
    * System time in mid game menu
    * More cities
    * Personal armoury (gun inventory for selecting different guns with different traits)
    * More guns
    * chance to miss shooting at thief


    BIG FEATURES (updates)

    * Bank heists
    * Final battle against monkeys (END GAME)


    NICE TO HAVES (not likely to be added)

    * Support for resizing stage
    * Individual member control menu
    * Score board
    * Save/load

    */

    public static void main(String args[]) {

        // setting up list of cities
        cities.add("Tallahassee, Florida");
        cities.add("Atlanta, Georgia");
        cities.add("Frankfort, Kentucky");
        cities.add("Denver, Colorado");
        cities.add("Salem, Oregon");

        // setting up description scene, needs to be moved
        try {
            descripitionPane = FXMLLoader.load(Main.class.getResource("DescripitonScene.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Launches sentry with the dsn in the parameters
        //Sentry.init(dsn);

        /*
        sends a error message to the sentry dsn server
        logger.error("test ");

        works with other methods like: logger.fatal / logger.debug / ETC
        of which change the tag on the sentry dsn server
        */

        // Music
        Thread backGroundMusicThread = new Thread(backGroundMusicTask);
        backGroundMusicThread.start();

        launch(args);
    }

    // Getters and Setters
    static Scene getFoodPortionsScene() {
        return FoodPortionsScene;
    }

    static void setFoodPortionsScene(Scene foodPortionsScene) {
        FoodPortionsScene = foodPortionsScene;
    }

    static Scene getPaceScene() {
        return PaceScene;
    }

    static void setPaceScene(Scene paceScene) {
        PaceScene = paceScene;
    }

    static boolean extremeLowChance(){

        int Chance = (int)(Math.random()*100+1);

        // return true if chance is equal to 90
        return Chance == 90;

        // Testing purposes
        //return true;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AlertBox.alertMenuStart();

        Parent mainAnchor = FXMLLoader.load(getClass().getResource("StartScene.fxml"));

        // Start scene
        MainWindow = primaryStage;

        MainWindow.setResizable(false);

        MainWindow.setTitle("The Trail "+trailVersion);

        MainWindow.setScene(new Scene(mainAnchor));

        MainWindow.show();
    }

    // Music task
    private static final Task backGroundMusicTask = new Task() {

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