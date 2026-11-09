import javafx.fxml.FXML;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.*;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private Label titleLabel;

    @FXML
    private TextField temperatureField;

    @FXML
    private ComboBox<UnitTranslation> fromUnitBox;

    @FXML
    private ComboBox<UnitTranslation> toUnitBox;

    @FXML
    private ComboBox<String> languageBox;

    @FXML
    private Button convertButton;

    @FXML
    private Label resultLabel;

    @FXML
    private ListView<String> historyList;

    private final TemperatureConverter converter =
            new TemperatureConverter();

    private final TemperatureRecordDAO dao =
            new TemperatureRecordDAO();

    private final UnitTranslationDAO unitDao =
            new UnitTranslationDAO();

    private String currentLanguage = "en";

    @FXML
    public void initialize() {

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

        loadUnits("en");

        updateTexts(
                ResourceBundle.getBundle(
                        "messages",
                        Locale.ENGLISH
                )
        );

        loadHistory();
    }

    private void loadUnits(
            String language
    ) {

        List<UnitTranslation> units =
                unitDao.getUnits(language);

        fromUnitBox.getItems().clear();
        toUnitBox.getItems().clear();

        fromUnitBox.getItems().addAll(units);
        toUnitBox.getItems().addAll(units);

        if (!units.isEmpty()) {

            fromUnitBox.setValue(
                    units.get(0)
            );

            if (units.size() > 1) {

                toUnitBox.setValue(
                        units.get(1)
                );
            }
        }
    }

    @FXML
    private void convert() {

        try {

            double value =
                    Double.parseDouble(
                            temperatureField.getText()
                    );

            UnitTranslation from =
                    fromUnitBox.getValue();

            UnitTranslation to =
                    toUnitBox.getValue();

            double converted =
                    performConversion(
                            value,
                            from.getUnitId(),
                            to.getUnitId()
                    );

            resultLabel.setText(
                    String.format(
                            "%.2f %s",
                            converted,
                            to.getName()
                    )
            );

            dao.save(
                    new TemperatureRecord(
                            value,
                            converted,
                            from.getUnitId(),
                            to.getUnitId(),
                            currentLanguage
                    )
            );

            loadHistory();

        } catch (NumberFormatException ex) {

            resultLabel.setText(
                    "Invalid number"
            );

        } catch (Exception ex) {

            resultLabel.setText(
                    "Conversion failed"
            );

            ex.printStackTrace();
        }
    }

    private double performConversion(
            double value,
            int fromId,
            int toId
    ) {

        if (fromId == toId) {
            return value;
        }

        // Celsius = 1
        // Fahrenheit = 2
        // Kelvin = 3

        if (fromId == 1 && toId == 2)
            return converter.celsiusToFahrenheit(value);

        if (fromId == 2 && toId == 1)
            return converter.fahrenheitToCelsius(value);

        if (fromId == 1 && toId == 3)
            return converter.celsiusToKelvin(value);

        if (fromId == 3 && toId == 1)
            return converter.kelvinToCelsius(value);

        if (fromId == 2 && toId == 3)
            return converter.fahrenheitToKelvin(value);

        if (fromId == 3 && toId == 2)
            return converter.kelvinToFahrenheit(value);

        return value;
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

        currentLanguage =
                locale.getLanguage();

        ResourceBundle bundle =
                ResourceBundle.getBundle(
                        "messages",
                        locale
                );

        updateTexts(bundle);

        loadUnits(currentLanguage);

        updateOrientation(locale);
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

    private void updateOrientation(
            Locale locale
    ) {

        if (titleLabel.getScene() == null) {
            return;
        }

        if ("ar".equals(
                locale.getLanguage()
        )) {

            titleLabel.getScene()
                    .getRoot()
                    .setNodeOrientation(
                            NodeOrientation.RIGHT_TO_LEFT
                    );
        }
        else {

            titleLabel.getScene()
                    .getRoot()
                    .setNodeOrientation(
                            NodeOrientation.LEFT_TO_RIGHT
                    );
        }
    }

    private void loadHistory() {

        historyList.getItems().clear();

        historyList.getItems().addAll(
                dao.getLastFiveConversions()
        );
    }
}