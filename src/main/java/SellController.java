import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class SellController extends AlertBox {


    @FXML private Label foodLbl;
    @FXML private Label waterLbl;
    @FXML private Label ammoLbl;
    @FXML private Slider waterSlider;
    @FXML private Slider foodSlider;
    @FXML private Slider ammoSlider;

    @FXML
    private void sell(){

        Food-=foodSlider.getValue();
        Water-=waterSlider.getValue();
        Ammo-=ammoSlider.getValue();

        AlertWindow.setScene(CityScene);
    }

    @FXML
    public void initialize(){

        foodLbl.setText("Food: "+Food);
        waterLbl.setText("Water: "+Water);
        ammoLbl.setText("Ammo: "+Ammo);
        waterSlider.setMax(Water);
        ammoSlider.setMax(Ammo);
        foodSlider.setMax(Food);
    }
}
