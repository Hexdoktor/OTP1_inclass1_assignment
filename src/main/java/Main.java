import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        ResourceBundle bundle =
                ResourceBundle.getBundle(
                        "messages",
                        Locale.ENGLISH
                );

        FXMLLoader loader =
                new FXMLLoader(
                        getClass().getResource(
                                "/main.fxml"
                        ),
                        bundle
                );

        Parent root =
                loader.load();

        Scene scene =
                new Scene(
                        root,
                        400,
                        250
                );

        stage.setTitle(
                bundle.getString("title")
        );

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
