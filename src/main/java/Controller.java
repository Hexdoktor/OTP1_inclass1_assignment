import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private Label titleLabel;

    @FXML
    private TextField temperatureField;

    @FXML
    private ComboBox<String> fromUnitBox;

    @FXML
    private ComboBox<String> toUnitBox;

    @FXML
    private ComboBox<String> languageBox;

    @FXML
    private Button convertButton;

    @FXML
    private Label resultLabel;

    private final TemperatureConverter converter =
            new TemperatureConverter();

    private final TemperatureRecordDAO dao =
            new TemperatureRecordDAO();

    @FXML
    public void initialize() {

        fromUnitBox.getItems().addAll(
                "Celsius",
                "Fahrenheit",
                "Kelvin"
        );

        toUnitBox.getItems().addAll(
                "Celsius",
                "Fahrenheit",
                "Kelvin"
        );

        fromUnitBox.setValue("Celsius");
        toUnitBox.setValue("Fahrenheit");

        languageBox.getItems().addAll(
                "English",
                "Finnish",
                "Swedish",
                "Japanese",
                "Arabic"
        );

        languageBox.setValue("English");

        languageBox.setOnAction(
                e -> changeLanguage()
        );

        updateTexts(
                ResourceBundle.getBundle(
                        "messages",
                        Locale.ENGLISH
                )
        );
    }

    @FXML
    private void convert() {

        try {

            double value =
                    Double.parseDouble(
                            temperatureField.getText()
                    );

            String from =
                    fromUnitBox.getValue();

            String to =
                    toUnitBox.getValue();

            double converted = value;

            if (from.equals("Celsius")
                    && to.equals("Fahrenheit")) {

                converted =
                        converter.celsiusToFahrenheit(
                                value
                        );

            } else if (from.equals("Fahrenheit")
                    && to.equals("Celsius")) {

                converted =
                        converter.fahrenheitToCelsius(
                                value
                        );

            } else if (from.equals("Kelvin")
                    && to.equals("Celsius")) {

                converted =
                        converter.kelvinToCelsius(
                                value
                        );

            } else if (from.equals("Celsius")
                    && to.equals("Kelvin")) {

                converted =
                        converter.celsiusToKelvin(
                                value
                        );

            } else if (from.equals("Fahrenheit")
                    && to.equals("Kelvin")) {

                converted =
                        converter.fahrenheitToKelvin(
                                value
                        );

            } else if (from.equals("Kelvin")
                    && to.equals("Fahrenheit")) {

                converted =
                        converter.kelvinToFahrenheit(
                                value
                        );
            }

            resultLabel.setText(
                    String.format(
                            "%.2f %s",
                            converted,
                            getUnitSymbol(to)
                    )
            );

            dao.save(
                    new TemperatureRecord(
                            value,
                            converted,
                            getUnitId(to)
                    )
            );

        } catch (NumberFormatException ex) {

            resultLabel.setText(
                    "Invalid number"
            );

        } catch (Exception ex) {

            resultLabel.setText(
                    "Database error"
            );

            ex.printStackTrace();
        }
    }

    private void changeLanguage() {

        Locale locale;

        switch (languageBox.getValue()) {

            case "Finnish":
                locale = new Locale("fi");
                break;

            case "Swedish":
                locale = new Locale("sv");
                break;

            case "Japanese":
                locale = new Locale("ja");
                break;

            case "Arabic":
                locale = new Locale("ar");
                break;

            default:
                locale = Locale.ENGLISH;
        }

        ResourceBundle bundle =
                ResourceBundle.getBundle(
                        "messages",
                        locale
                );

        updateTexts(bundle);
    }

    private void updateTexts(
            ResourceBundle bundle
    ) {

        titleLabel.setText(
                bundle.getString("title")
        );

        convertButton.setText(
                bundle.getString("convert")
        );

        temperatureField.setPromptText(
                bundle.getString("input")
        );
    }

    private String getUnitSymbol(
            String unit
    ) {

        switch (unit) {

            case "Celsius":
                return "°C";

            case "Fahrenheit":
                return "°F";

            case "Kelvin":
                return "K";

            default:
                return "";
        }
    }

    private int getUnitId(
            String unit
    ) {

        switch (unit) {

            case "Celsius":
                return 1;

            case "Fahrenheit":
                return 2;

            case "Kelvin":
                return 3;

            default:
                return 1;
        }
    }
}