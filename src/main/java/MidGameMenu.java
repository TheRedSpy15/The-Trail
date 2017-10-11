import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;

public class MidGameMenu extends Main {

    private static Parent menuPane;
    private static Scene menuScene;

    protected static void menuMethod(){

        // MENU

        // Links menu pane to FXML file
        try {
            menuPane = FXMLLoader.load(Main.class.getResource("MidMenu.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuScene = new Scene(menuPane);

        // Creating new Stage
        MenuWindow = new Stage();

        // Setting title of stage
        MenuWindow.setTitle("MENU");

        // preventing interaction with other windows
        MenuWindow.initModality(Modality.APPLICATION_MODAL);

        // Set MenuWindow's scene
        MenuWindow.setScene(menuScene);
    }

    protected static void inventoryMethod(){

        // Object declaration
        VBox InventoryLayout = new VBox(5);
        Label Waterlbl = new Label("Water: "+Water);
        Label Foodlbl = new Label("Food: "+Food);
        Label Ammolbl = new Label("Ammo: "+Ammo);
        Label Moneylbl = new Label("Money: "+(int)Money);
        Button Backbtn = new Button("Back");

        // Setting the font size and color of labels
        Waterlbl.setFont(new Font(20));
        Waterlbl.setStyle("-fx-text-fill: purple;");
        Foodlbl.setFont(new Font(20));
        Foodlbl.setStyle("-fx-text-fill: purple;");
        Ammolbl.setFont(new Font(20));
        Ammolbl.setStyle("-fx-text-fill: purple;");
        Moneylbl.setFont(new Font(20));
        Moneylbl.setStyle("-fx-text-fill: purple;");

        // Set background color
        InventoryLayout.setStyle("-fx-background-color: black");

        // changing scene back to menu scene
        Backbtn.setOnAction(e -> MenuWindow.setScene(menuScene));

        // Adding padding to layout
        InventoryLayout.setPadding(new Insets(20,20,20,20));

        // Adding objects to layout
        InventoryLayout.getChildren().addAll(Waterlbl,Foodlbl,Ammolbl,Moneylbl,Backbtn);

        // Initializing scene
        InventoryScene = new Scene(InventoryLayout, 320,200);
    }

    // Used to set food intake
    protected static void foodPortionSet(){

        // Object declaration
        Button BuffetDietbtn = new Button("Buffet");
        VBox FoodPortionsLayout = new VBox(10);
        Button ExtremeDietbtn = new Button("Extreme");
        Label label = new Label("Select your meal plan");
        Button ModerateDietbtn = new Button("Moderate");

        // Setting font size of Label and color
        label.setFont(new Font(20));
        label.setStyle("-fx-text-fill: purple;");

        // Setting background color
        FoodPortionsLayout.setStyle("-fx-background-color: black;");

        // Setting food intake value and going back to menu scene
        ExtremeDietbtn.setOnAction(e -> {
            FoodIntake = 1;
            MenuWindow.setScene(menuScene);
        });

        // Setting food intake value and going back to menu scene
        ModerateDietbtn.setOnAction(e -> {
            FoodIntake = 2;
            MenuWindow.setScene(menuScene);
        });

        // Setting food intake value and going back to menu scene
        BuffetDietbtn.setOnAction(e -> {
            FoodIntake = 3;
            MenuWindow.setScene(menuScene);
        });

        // Setting padding to layout
        FoodPortionsLayout.setPadding(new Insets(20,20,20,20));

        // adding objects to layout
        FoodPortionsLayout.getChildren().addAll(label,ExtremeDietbtn,ModerateDietbtn,BuffetDietbtn);

        // initializing scene
        setFoodPortionsScene(new Scene(FoodPortionsLayout,320,200));
    }

    protected static void paceSetterMethod(){

        // Object declaration
        VBox PaceLayout = new VBox(10);
        Label label = new Label("Choose a speed");
        Button Slowbtn = new Button("Slow pace");
        Button ModerateSpeedbtn = new Button("Moderate pace");
        Button Fastbtn = new Button("Fast pace");

        // Setting font size of label and color
        label.setStyle("-fx-text-fill: purple;");
        label.setFont(new Font(20));

        // setting background color
        PaceLayout.setStyle("-fx-background-color: black");

        // Setting pace value and going back to menu scene
        Slowbtn.setOnAction(e -> {
            Pace = 5;
            TravelController.animationSpeed = 20;
            MenuWindow.setScene(menuScene);
        });

        // Setting pace value and going back to menu scene
        ModerateSpeedbtn.setOnAction(e -> {
            Pace = 10;
            TravelController.animationSpeed = 15;
            MenuWindow.setScene(menuScene);
        });

        // Setting pace value and going back to menu scene
        Fastbtn.setOnAction(e -> {
            Pace = 15;
            TravelController.animationSpeed = 10;
            MenuWindow.setScene(menuScene);
        });

        // Setting padding to layout
        PaceLayout.setPadding(new Insets(20,20,20,20));

        // Adding padding to layout
        PaceLayout.getChildren().addAll(label,Slowbtn,ModerateSpeedbtn,Fastbtn);

        // Initializing scene
        setPaceScene(new Scene(PaceLayout,320,200));
    }
}