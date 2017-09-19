import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MidGameMenu extends Main {

    static void MenuMethod(){

        // MENU
        MenuWindow = new Stage();
        MenuWindow.setTitle("MENU");
        MenuWindow.initModality(Modality.APPLICATION_MODAL);
        Button SetPace = new Button("Set pace");
        Button SetMealPortions = new Button("Set diet plan");
        Button ViewInventory = new Button("View Inventory");
        ViewInventory.setOnAction(e -> {
            InventoryMethod();
            MenuWindow.setScene(InventoryScene);
        });
        SetMealPortions.setOnAction(e -> {
            FoodPortionSet();
            MenuWindow.setScene(FoodPortionsScene);
        });
        SetPace.setOnAction(e -> {
            PaceSetterMethod();
            MenuWindow.setScene(PaceScene);
        });
        Button Hunt = new Button("Go hunting");
        VBox MenuLayout = new VBox(10);
        MenuLayout.getChildren().addAll(SetPace,SetMealPortions,Hunt,ViewInventory);
        MenuLayout.setPadding(new Insets(20,20,20,20));
        MenuScene = new Scene(MenuLayout,300,180);
        MenuWindow.setScene(MenuScene);
    }

    private static void InventoryMethod(){

        VBox InventoryLayout = new VBox(5);
        Label Waterlbl = new Label("Water: "+Water);
        Label Foodlbl = new Label("Food: "+Food);
        Label Ammolbl = new Label("Ammo: "+Ammo);
        Button Backbtn = new Button("Back");
        Backbtn.setOnAction(e -> MenuWindow.setScene(MenuScene));
        InventoryLayout.setPadding(new Insets(20,20,20,20));
        InventoryLayout.getChildren().addAll(Waterlbl,Foodlbl,Ammolbl,Backbtn);
        InventoryScene = new Scene(InventoryLayout, 320,200);
    }

    // Used to set food intake
    private static void FoodPortionSet(){

        VBox FoodPortionsLayout = new VBox(10);
        Button ExtremeDietbtn = new Button("Extreme");
        ExtremeDietbtn.setOnAction(e -> {
            FoodIntake = 1;
            MenuWindow.setScene(MenuScene);
        });
        Button ModerateDietbtn = new Button("Moderate");
        ModerateDietbtn.setOnAction(e -> {
            FoodIntake = 2;
            MenuWindow.setScene(MenuScene);
        });
        Button BuffetDietbtn = new Button("Buffet");
        BuffetDietbtn.setOnAction(e -> {
            FoodIntake = 3;
            MenuWindow.setScene(MenuScene);
        });
        Label FoodListlbl = new Label("Select your diet plan");
        FoodPortionsLayout.setPadding(new Insets(20,20,20,20));
        FoodPortionsLayout.getChildren().addAll(FoodListlbl,ExtremeDietbtn,ModerateDietbtn,BuffetDietbtn);
        FoodPortionsScene = new Scene(FoodPortionsLayout,320,200);
    }

    private static void PaceSetterMethod(){

        VBox PaceLayout = new VBox(10);
        Label PaceLbl = new Label("Choose a speed");
        Button Slowbtn = new Button("Slow pace");
        Button ModerateSpeedbtn = new Button("Moderate pace");
        Button Fastbtn = new Button("Fast pace");

        Slowbtn.setOnAction(e -> {
            Pace = 5;
            MenuWindow.setScene(MenuScene);
        });
        ModerateSpeedbtn.setOnAction(e -> {
            Pace = 10;
            MenuWindow.setScene(MenuScene);
        });
        Fastbtn.setOnAction(e -> {
            Pace = 15;
            MenuWindow.setScene(MenuScene);
        });

        PaceLayout.setPadding(new Insets(20,20,20,20));
        PaceLayout.getChildren().addAll(PaceLbl,Slowbtn,ModerateSpeedbtn,Fastbtn);

        PaceScene = new Scene(PaceLayout,320,200);
    }
}