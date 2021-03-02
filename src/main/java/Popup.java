
import java.time.LocalDate;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {
    static LocalDate date;
    static String desc;
    static int id;
    static TextField text1 = new TextField();
    static TextField text2 = new TextField();

    public Popup(LocalDate date, String desc, int id) {
        Popup.id = id;
        Popup.date = date;
        Popup.desc = desc;
    }

    public static void display() {
        Stage popupwindow = new Stage();
        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Edytuj wydarzenie");
        text1.setMaxWidth(200.0D);
        text2.setMaxWidth(200.0D);
        text1.setText(getDateTxt());
        text2.setText(desc);
        Button button2 = new Button("Zapisz i wyjdź");
        button2.setOnAction((e) -> {
            popupwindow.close();
        });
        VBox layout = new VBox(10.0D);
        layout.getChildren().addAll(new Node[]{text1, text2, button2});
        layout.setAlignment(Pos.CENTER);
        Scene scene1 = new Scene(layout, 300.0D, 150.0D);
        popupwindow.setScene(scene1);
        popupwindow.showAndWait();
    }

    public static LEvent get() {
        desc = text2.getText();
        date = setDate();
        LEvent event = new LEvent(date, desc, id);
        return event;
    }

    public static String getDateTxt() {
        return "" + date;
    }

    public static LocalDate setDate() {
        date = LocalDate.parse(text1.getText());
        return date;
    }
}
