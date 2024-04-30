package jan.comic.Helper;

import javafx.scene.control.Alert;
import org.springframework.stereotype.Component;

@Component
public class WarningHelper {

    public void deleteWarning(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Achtung");
        alert.setHeaderText(null);
        alert.setContentText("Die sind Sie Sicher Box wurde nicht angehackt");

        alert.showAndWait();
    }
}
