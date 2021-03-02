
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
    public Main() {
    }

    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(this.getClass().getClassLoader().getResource("Layout.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setTitle("ToDo List");
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, this::closeWindowEvent);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }

    private void closeWindowEvent(WindowEvent event) {
        LayoutController.save(LayoutController.lista);
    }
}
