import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class GangController extends Main {
    
    @FXML  private TextField membername1;
    @FXML  private TextField membername2;
    @FXML  private TextField membername3;
    @FXML  private TextField membername4;
    @FXML  private TextField membername5;
    @FXML  private TextField membername6;

    @FXML
    private void confirmNamesMethod(){

        // If statement of any of the text fields not being empty
        if (!(membername1.getText().trim().equals("") || membername2.getText().trim().equals("") || membername3.getText().trim().equals("") || membername4.getText().trim().equals("") || membername5.getText().trim().equals("") || membername6.getText().trim().equals(""))){

            // Runs store method
            store.storeMethod();

            // Sets the scene to store pane
            MainWindow.setScene(storeScene);

            // adds the value of the text fields to the linked list
            gang.add(membername1.getText());
            gang.add(membername2.getText());
            gang.add(membername3.getText());
            gang.add(membername4.getText());
            gang.add(membername5.getText());
            gang.add(membername6.getText());
        }
        else {

            // when the if statement is false, run the empty name alert
            alert.alert("Text fields cannot be empty");
        }
    }
}
