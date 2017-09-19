import java.util.LinkedList;
import java.util.Random;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import org.jetbrains.annotations.Nullable;

import static javafx.scene.media.AudioClip.INDEFINITE;

public class Main extends Application{

    private static Button StartGame = new Button();
    static Button Miner = new Button();
    static Button Carpenter = new Button();
    private static VBox layout = new VBox(10);
    private static Label label = new Label();
    static Scene CareerScene;
    static Scene PosseScene;
    static Stage Window;
    static Scene StoreScene;
    static TextField entername1;
    static TextField entername2;
    static TextField entername3;
    static TextField entername4;
    static TextField entername5;
    static TextField entername6;
    static Label FoodAmountLabel;
    static Label WaterAmountLabel;
    static Label AmmoAmountLabel;
    static Label BalanceLabel = new Label();
    static Scene TravelScene;
    static Label DaysLabel;
    static Label DistanceLabel;
    static Label TravelLabel;
    static Scene FoodPortionsScene;
    static Stage MenuWindow;
    static Scene MenuScene;
    static Label ConditionLabel;
    static Scene InventoryScene;
    static Scene SickEventScene;
    Stage window = new Stage();
    static Scene ThiefScene;
    static Scene PaceScene;
    public static Label moneyLabel;
    static Label BountyLbl;
    static Parent careerAnchor;
    static Label EncounterLbl;
    static Label CurrentPosseLbl;
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
    private static boolean EndResult;
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