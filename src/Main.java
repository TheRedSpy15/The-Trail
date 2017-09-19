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
import org.jetbrains.annotations.Nullable;

import static javafx.scene.media.AudioClip.INDEFINITE;

public class Main extends Application{

    static Stage Window;
    private static Scene FoodPortionsScene;
    static Stage MenuWindow;
    private static Scene MenuScene;
    static Scene InventoryScene;
    static Scene SickEventScene;
    Stage window = new Stage();
    static Scene ThiefScene;
    private static Scene PaceScene;
    private static Label BountyLbl;
    static Parent careerAnchor;
    static Label EncounterLbl;
    static Parent storePane;
    static Parent possePane;
    static Parent travelPane;

    static LinkedList <String> PlayersArray = new LinkedList<>();
    static int HealthConditions = 100;
    static int Distance = 3000;
    static int Days = 0;
    static int Food = 0;
    static int ThiefMoney;
    static int ArraySize = 6;
    static int ArraySize2 = 6;
    static boolean ThiefIsAlive;
    static int TownSelector = 0;
    static int Ammo = 0;
    static int Water = 0;
    static double amountOver = 0;
    static double Money = 0;
    static boolean BountyClaimable = false;
    static int Pace = 10;
    static int FoodIntake = 2;
    static Random rand = new Random();
    static int SickEventChance;
    static int PlayerSelectForEvent;
    static int EncounterChance;
    static TravelClass trvl = new TravelClass();
    static boolean IsMoving = false;
    static boolean TurnInThief = false;
    static AlertBox alt = new AlertBox();
    static CareerPosse cp = new CareerPosse();
    static String TownList[]={"Cape Cod", "Fort Myers", "ST. Augustine", "Jacksonville", "Tallahassee"};

    public static void main(String args[]) {

        Thread thread = new Thread(task);
        thread.start();
        launch(args);
    }

    public static Scene getFoodPortionsScene() {
        return FoodPortionsScene;
    }

    public static void setFoodPortionsScene(Scene foodPortionsScene) {
        FoodPortionsScene = foodPortionsScene;
    }

    public static Scene getMenuScene() {
        return MenuScene;
    }

    public static void setMenuScene(Scene menuScene) {
        MenuScene = menuScene;
    }

    public static Scene getPaceScene() {
        return PaceScene;
    }

    public static void setPaceScene(Scene paceScene) {
        PaceScene = paceScene;
    }

    public static Label getBountyLbl() {
        return BountyLbl;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        AnchorPane mainAnchor = FXMLLoader.load(Main.class.getResource("StartScene.fxml"));

        // Start
        Window = primaryStage;
        Window.setTitle("The Trail");
        Window.setScene(new Scene(mainAnchor));
        Window.show();
    }

    // Music
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