import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class StoreController extends Main {

    @FXML private Slider waterSlider;
    @FXML private Slider foodSlider;
    @FXML private Label moneyLabel;
    @FXML private Label currentWater;
    @FXML private Label currentFood;

    @FXML
    private void purchase(){

        double cartValue;

        cartValue = (waterSlider.getValue() * 0.25) + (foodSlider.getValue() * 0.50);

        if (Money >= cartValue){

            Money -= cartValue;

            Food += (int) foodSlider.getValue();

            Water += (int) waterSlider.getValue();

            travel.travelSetup();

            // determines if the scene is being used in alert window or main window and changes depending on that
            if (MainWindow.getScene().equals(storeScene)){

                // setting window scene to travel pane
                MainWindow.setScene(new Scene(travelPane));
            }else{

                // Changing alert window scene to settlement scene
                AlertWindow.setScene(cityScene);
            }

        // show not enough money scene in alert window with the amount over
        }else{

            int amountOver = (int) (Money - cartValue);

            alert.notEnoughMoney(amountOver);
        }
    }

    @FXML
    private void gunStoreBtn(){

        // determines if the scene is being used in alert window or main window and changes depending on that
        if (MainWindow.getScene().equals(storeScene)){

            // setting window scene to travel pane
            MainWindow.setScene(gunStoreScene);
        }else{

            // Changing alert window scene to settlement scene
            AlertWindow.setScene(gunStoreScene);
        }
    }

    // code run on initialization of scene
    @FXML
    public void initialize(){

        // On starting class, update labels and max value of sliders
        moneyLabel.setText("Money: $"+(int) Money);
        currentFood.setText("FOOD: "+Food);
        currentWater.setText("WATER: "+Water);

        // random store inventory
        waterSlider.setMax((int) (Math.random() * 1500) + 1000);
        foodSlider.setMax((int) (Math.random() * 1500) + 1000);
    }
}
