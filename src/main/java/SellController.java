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

        Food -= foodSlider.getValue();
        Water -= waterSlider.getValue();
        Ammo -= ammoSlider.getValue();

        Money += (waterSlider.getValue() * 0.15) + (foodSlider.getValue() * 0.30) + (ammoSlider.getValue() * 20);

        AlertWindow.setScene(cityScene);
    }

    @FXML
    public void initialize(){

        foodLbl.setText("Food: "+Food);
        waterLbl.setText("Water: "+Water);
        ammoLbl.setText("Ammo: "+Ammo);

        waterSlider.setMax(Water);
        foodSlider.setMax(Food);
        ammoSlider.setMax(Ammo);

        waterSlider.setMin(0);
        ammoSlider.setMin(0);
        foodSlider.setMin(0);
    }
}
