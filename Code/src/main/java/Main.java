import java.util.LinkedList;
import java.util.Random;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import static javafx.scene.media.AudioClip.INDEFINITE;

public class Main extends Application{

    static Stage Window;
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
    static Parent midSellStorePane;

    static LinkedList <String> PlayersArray = new LinkedList<>();
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
    static TravelClass trvl = new TravelClass();
    static boolean IsMoving = false;
    static boolean TurnInThief = false;
    static CareerPosse cp = new CareerPosse();
    static String TownList[]={"Cape Cod", "Fort Myers", "ST. Augustine", "Jacksonville", "Tallahassee"};

    public static void main(String args[]) {

        // Starting music task thread
        Thread thread = new Thread(task);
        thread.start();

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

        int Chance = (int)(Math.random()*110+1);

        return Chance == 100;

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane mainAnchor = FXMLLoader.load(Main.class.getResource("StartScene.fxml"));
        AlertBox.alertMenuStart();

        // Start
        Window = primaryStage;
        Window.setTitle("The Trail");
        mainAnchor.setStyle("-fx-background-color: #36454f");
        Window.setScene(new Scene(mainAnchor));
        Window.show();
    }

    // Music task
    private static final Task task = new Task() {

        @Nullable
        @Override
        protected Object call() throws Exception {
            AudioClip audio = new AudioClip(getClass().getResource("Wild_Western_Music_-_Tumbleweed_Town.wav").toExternalForm());
            audio.setVolume(0.5f);
            audio.setCycleCount(INDEFINITE);
            audio.play();
            return null;
        }
    };
}