import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.Formatter;

public class MidGameMenu extends Main {

    private static Formatter formatter;

    static {
        try {
            formatter = new Formatter("SaveGame.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static void menuMethod(){

        // MENU

        // Creating new Stage
        MenuWindow = new Stage();

        // Setting title of stage
        MenuWindow.setTitle("MENU");

        // preventing interaction with other windows
        MenuWindow.initModality(Modality.APPLICATION_MODAL);

        // object declaration
        Button SetPace = new Button("Set pace");
        Button SetMealPortions = new Button("Set diet plan");
        Button ViewInventory = new Button("View Inventory");
        Button SaveGame = new Button("Save game progress");
        Button Hunt = new Button("Go hunting");
        VBox MenuLayout = new VBox(10);

        // View inventory
        ViewInventory.setOnAction(e -> {
            inventoryMethod();
            MenuWindow.setScene(InventoryScene);
        });

        // Set diet
        SetMealPortions.setOnAction(e -> {
            foodPortionSet();
            MenuWindow.setScene(getFoodPortionsScene());
        });

        // Set pace
        SetPace.setOnAction(e -> {
            paceSetterMethod();
            MenuWindow.setScene(getPaceScene());
        });

        // Save progress
        // ERROR: output to file is blank
        SaveGame.setOnAction(e -> formatter.format("%s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s %s", Money," ",Distance," ",Food," ",Water," ",Ammo," ",FoodIntake," ",Pace," ",PlayersArray.get(0)," ",PlayersArray.get(1)," ",PlayersArray.get(2)," ",PlayersArray.get(3)," ",PlayersArray.get(4)," ",PlayersArray.get(5)));

        // Set background color
        MenuLayout.setStyle("-fx-background-color: #36454f");

        // add to layout
        MenuLayout.getChildren().addAll(SetPace,SetMealPortions,Hunt,ViewInventory,SaveGame);

        // Set padding
        MenuLayout.setPadding(new Insets(20,20,20,20));

        // Finalise scene
        setMenuScene(new Scene(MenuLayout,300,200));

        // Set MenuWindow's scene
        MenuWindow.setScene(getMenuScene());
    }

    private static void inventoryMethod(){

        // Object declaration
        VBox InventoryLayout = new VBox(5);
        Label Waterlbl = new Label("Water: "+Water);
        Label Foodlbl = new Label("Food: "+Food);
        Label Ammolbl = new Label("Ammo: "+Ammo);
        Label Moneylbl = new Label("Money: "+(int)Money);
        Button Backbtn = new Button("Back");

        // Set background color
        InventoryLayout.setStyle("-fx-background-color: #36454f");

        // changing scene back to menu scene
        Backbtn.setOnAction(e -> MenuWindow.setScene(getMenuScene()));

        // Adding padding to layout
        InventoryLayout.setPadding(new Insets(20,20,20,20));

        // Adding objects to layout
        InventoryLayout.getChildren().addAll(Waterlbl,Foodlbl,Ammolbl,Moneylbl,Backbtn);

        // Initializing scene
        InventoryScene = new Scene(InventoryLayout, 320,200);
    }

    // Used to set food intake
    private static void foodPortionSet(){

        // Object declaration
        Button BuffetDietbtn = new Button("Buffet");
        VBox FoodPortionsLayout = new VBox(10);
        Button ExtremeDietbtn = new Button("Extreme");
        Label FoodListlbl = new Label("Select your diet plan");
        Button ModerateDietbtn = new Button("Moderate");

        // Setting background color
        FoodPortionsLayout.setStyle("-fx-background-color: #36454f");

        // Setting food intake value and going back to menu scene
        ExtremeDietbtn.setOnAction(e -> {
            FoodIntake = 1;
            MenuWindow.setScene(getMenuScene());
        });

        // Setting food intake value and going back to menu scene
        ModerateDietbtn.setOnAction(e -> {
            FoodIntake = 2;
            MenuWindow.setScene(getMenuScene());
        });

        // Setting food intake value and going back to menu scene
        BuffetDietbtn.setOnAction(e -> {
            FoodIntake = 3;
            MenuWindow.setScene(getMenuScene());
        });

        // Setting padding to layout
        FoodPortionsLayout.setPadding(new Insets(20,20,20,20));

        // adding objects to layout
        FoodPortionsLayout.getChildren().addAll(FoodListlbl,ExtremeDietbtn,ModerateDietbtn,BuffetDietbtn);

        // initializing scene
        setFoodPortionsScene(new Scene(FoodPortionsLayout,320,200));
    }

    private static void paceSetterMethod(){

        // Object declaration
        VBox PaceLayout = new VBox(10);
        PaceLayout.setStyle("-fx-background-color: #36454f");
        Label PaceLbl = new Label("Choose a speed");
        Button Slowbtn = new Button("Slow pace");
        Button ModerateSpeedbtn = new Button("Moderate pace");
        Button Fastbtn = new Button("Fast pace");

        // Setting pace value and going back to menu scene
        Slowbtn.setOnAction(e -> {
            Pace = 5;
            MenuWindow.setScene(getMenuScene());
        });

        // Setting pace value and going back to menu scene
        ModerateSpeedbtn.setOnAction(e -> {
            Pace = 10;
            MenuWindow.setScene(getMenuScene());
        });

        // Setting pace value and going back to menu scene
        Fastbtn.setOnAction(e -> {
            Pace = 15;
            MenuWindow.setScene(getMenuScene());
        });

        // Setting padding to layout
        PaceLayout.setPadding(new Insets(20,20,20,20));

        // Adding padding to layout
        PaceLayout.getChildren().addAll(PaceLbl,Slowbtn,ModerateSpeedbtn,Fastbtn);

        // Initializing scene
        setPaceScene(new Scene(PaceLayout,320,200));
    }
}