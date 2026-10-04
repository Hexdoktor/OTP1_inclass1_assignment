import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private final TemperatureConverter converter =
            new TemperatureConverter();

    private final TemperatureRecordDAO dao =
            new TemperatureRecordDAO();

    @Override
    public void start(Stage stage) {

        TextField input = new TextField();
        input.setPromptText("Enter temperature");

        ComboBox<String> conversionBox =
                new ComboBox<>();

        conversionBox.getItems().addAll(
                "Celsius to Fahrenheit",
                "Fahrenheit to Celsius",
                "Kelvin to Celsius"
        );

        conversionBox.setValue(
                "Celsius to Fahrenheit"
        );

        Button convertButton =
                new Button("Convert");

        Label result =
                new Label("Result will appear here");

        convertButton.setOnAction(event -> {

            try {

                double value =
                        Double.parseDouble(
                                input.getText()
                        );

                double converted;
                int unitId;

                String conversion =
                        conversionBox.getValue();

                switch (conversion) {

                    case "Fahrenheit to Celsius":

                        converted =
                                converter.fahrenheitToCelsius(
                                        value
                                );

                        result.setText(
                                String.format(
                                        "%.2f °C",
                                        converted
                                )
                        );

                        unitId = 2;
                        break;

                    case "Kelvin to Celsius":

                        converted =
                                converter.kelvinToCelsius(
                                        value
                                );

                        result.setText(
                                String.format(
                                        "%.2f °C",
                                        converted
                                )
                        );

                        unitId = 3;
                        break;

                    default:

                        converted =
                                converter.celsiusToFahrenheit(
                                        value
                                );

                        result.setText(
                                String.format(
                                        "%.2f °F",
                                        converted
                                )
                        );

                        unitId = 1;
                        break;
                }

                dao.save(
                        new TemperatureRecord(
                                value,
                                converted,
                                unitId
                        )
                );

            } catch (NumberFormatException ex) {

                result.setText(
                        "Please enter a valid number."
                );

            } catch (Exception ex) {

                result.setText(
                        "Error saving to database."
                );

                ex.printStackTrace();
            }
        });

        VBox root =
                new VBox(
                        10,
                        input,
                        conversionBox,
                        convertButton,
                        result
                );

        root.setStyle(
                "-fx-padding: 20;"
        );

        Scene scene =
                new Scene(
                        root,
                        400,
                        250
                );

        stage.setTitle(
                "Temperature Converter"
        );

        stage.setScene(scene);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}