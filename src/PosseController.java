import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

public class PosseController extends Main {

    private Store str = new Store();
    private AlertBox alt = new AlertBox();
    
    @FXML  private TextField membername1;
    @FXML  private TextField membername2;
    @FXML  private TextField membername3;
    @FXML  private TextField membername4;
    @FXML  private TextField membername5;
    @FXML  private TextField membername6;

    @FXML
    private void confirmNamesMethod(){

        if (!(membername1.getText().trim().equals("") || membername2.getText().trim().equals("") || membername3.getText().trim().equals("") || membername4.getText().trim().equals("") || membername5.getText().trim().equals("") || membername6.getText().trim().equals(""))){

            str.storeMethod();
            Window.setScene(new Scene(storePane));

            PlayersArray.add(membername1.getText());
            PlayersArray.add(membername2.getText());
            PlayersArray.add(membername3.getText());
            PlayersArray.add(membername4.getText());
            PlayersArray.add(membername5.getText());
            PlayersArray.add(membername6.getText());
        }
        else {

            alt.emptyNames();
        }
    }
}
