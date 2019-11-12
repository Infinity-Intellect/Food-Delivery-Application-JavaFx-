package application;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class DialogBoxController{
    @FXML
    Label dialogBoxMessage;
    @FXML
    public void handleCloseClick(ActionEvent event)
    {
        Stage stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    public void setText(String text)
    {
        dialogBoxMessage.setText(text);
    }
    
}