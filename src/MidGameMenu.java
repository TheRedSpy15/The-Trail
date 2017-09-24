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

    protected static void menuMethod(){

        // MENU
        MenuWindow = new Stage();
        MenuWindow.setTitle("MENU");
        MenuWindow.initModality(Modality.APPLICATION_MODAL);
        Button SetPace = new Button("Set pace");
        Button SetMealPortions = new Button("Set diet plan");
        Button ViewInventory = new Button("View Inventory");
        Button SaveGame = new Button("Save game progress");
        ViewInventory.setOnAction(e -> {
            inventoryMethod();
            MenuWindow.setScene(InventoryScene);
        });
        SetMealPortions.setOnAction(e -> {
            foodPortionSet();
            MenuWindow.setScene(getFoodPortionsScene());
        });
        SetPace.setOnAction(e -> {
            paceSetterMethod();
            MenuWindow.setScene(getPaceScene());
        });
        SaveGame.setOnAction(e -> {
            try {
                saveGame();
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        Button Hunt = new Button("Go hunting");
        VBox MenuLayout = new VBox(10);
        MenuLayout.setStyle("-fx-background-color: #36454f");
        MenuLayout.getChildren().addAll(SetPace,SetMealPortions,Hunt,ViewInventory,SaveGame);
        MenuLayout.setPadding(new Insets(20,20,20,20));
        setMenuScene(new Scene(MenuLayout,300,180));
        MenuWindow.setScene(getMenuScene());
    }

    private static void inventoryMethod(){

        VBox InventoryLayout = new VBox(5);
        InventoryLayout.setStyle("-fx-background-color: #36454f");
        Label Waterlbl = new Label("Water: "+Water);
        Label Foodlbl = new Label("Food: "+Food);
        Label Ammolbl = new Label("Ammo: "+Ammo);
        Label Moneylbl = new Label("Money: "+(int)Money);
        Button Backbtn = new Button("Back");
        Backbtn.setOnAction(e -> MenuWindow.setScene(getMenuScene()));
        InventoryLayout.setPadding(new Insets(20,20,20,20));
        InventoryLayout.getChildren().addAll(Waterlbl,Foodlbl,Ammolbl,Moneylbl,Backbtn);
        InventoryScene = new Scene(InventoryLayout, 320,200);
    }

    // Used to set food intake
    private static void foodPortionSet(){

        VBox FoodPortionsLayout = new VBox(10);
        FoodPortionsLayout.setStyle("-fx-background-color: #36454f");
        Button ExtremeDietbtn = new Button("Extreme");
        ExtremeDietbtn.setOnAction(e -> {
            FoodIntake = 1;
            MenuWindow.setScene(getMenuScene());
        });
        Button ModerateDietbtn = new Button("Moderate");
        ModerateDietbtn.setOnAction(e -> {
            FoodIntake = 2;
            MenuWindow.setScene(getMenuScene());
        });
        Button BuffetDietbtn = new Button("Buffet");
        BuffetDietbtn.setOnAction(e -> {
            FoodIntake = 3;
            MenuWindow.setScene(getMenuScene());
        });
        Label FoodListlbl = new Label("Select your diet plan");
        FoodPortionsLayout.setPadding(new Insets(20,20,20,20));
        FoodPortionsLayout.getChildren().addAll(FoodListlbl,ExtremeDietbtn,ModerateDietbtn,BuffetDietbtn);
        setFoodPortionsScene(new Scene(FoodPortionsLayout,320,200));
    }

    private static void paceSetterMethod(){

        VBox PaceLayout = new VBox(10);
        PaceLayout.setStyle("-fx-background-color: #36454f");
        Label PaceLbl = new Label("Choose a speed");
        Button Slowbtn = new Button("Slow pace");
        Button ModerateSpeedbtn = new Button("Moderate pace");
        Button Fastbtn = new Button("Fast pace");

        Slowbtn.setOnAction(e -> {
            Pace = 5;
            MenuWindow.setScene(getMenuScene());
        });
        ModerateSpeedbtn.setOnAction(e -> {
            Pace = 10;
            MenuWindow.setScene(getMenuScene());
        });
        Fastbtn.setOnAction(e -> {
            Pace = 15;
            MenuWindow.setScene(getMenuScene());
        });

        PaceLayout.setPadding(new Insets(20,20,20,20));
        PaceLayout.getChildren().addAll(PaceLbl,Slowbtn,ModerateSpeedbtn,Fastbtn);

        setPaceScene(new Scene(PaceLayout,320,200));
    }

    private static void saveGame() throws FileNotFoundException {

        Formatter format = new Formatter("C:\\Users\\Hunter\\IdeaProjects\\The Trail JAVAFX VER\\src\\SaveFile");
    }
}