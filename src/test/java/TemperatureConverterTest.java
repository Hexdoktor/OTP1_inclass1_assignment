import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    private final TemperatureConverter converter =
            new TemperatureConverter();

    @Test
    void fahrenheitToCelsius() {

        assertEquals(
                0,
                converter.fahrenheitToCelsius(32),
                0.0001
        );

        assertEquals(
                100,
                converter.fahrenheitToCelsius(212),
                0.0001
        );

        assertEquals(
                -40,
                converter.fahrenheitToCelsius(-40),
                0.0001
        );
    }

    @Test
    void celsiusToFahrenheit() {

        assertEquals(
                32,
                converter.celsiusToFahrenheit(0),
                0.0001
        );

        assertEquals(
                212,
                converter.celsiusToFahrenheit(100),
                0.0001
        );

        assertEquals(
                -40,
                converter.celsiusToFahrenheit(-40),
                0.0001
        );
    }

    @Test
    void kelvinToCelsius() {

        assertEquals(
                26.85,
                converter.kelvinToCelsius(300),
                0.0001
        );

        assertEquals(
                0,
                converter.kelvinToCelsius(273.15),
                0.0001
        );

        assertEquals(
                -273.15,
                converter.kelvinToCelsius(0),
                0.0001
        );
    }

    @Test
    void celsiusToKelvin() {

        assertEquals(
                273.15,
                converter.celsiusToKelvin(0),
                0.0001
        );

        assertEquals(
                373.15,
                converter.celsiusToKelvin(100),
                0.0001
        );
    }

    @Test
    void fahrenheitToKelvin() {

        assertEquals(
                273.15,
                converter.fahrenheitToKelvin(32),
                0.0001
        );

        assertEquals(
                373.15,
                converter.fahrenheitToKelvin(212),
                0.0001
        );
    }

    @Test
    void kelvinToFahrenheit() {

        assertEquals(
                32,
                converter.kelvinToFahrenheit(273.15),
                0.0001
        );

        assertEquals(
                212,
                converter.kelvinToFahrenheit(373.15),
                0.0001
        );
    }

    @Test
    void isExtremeTemperature() {

        assertTrue(
                converter.isExtremeTemperature(-41)
        );

        assertTrue(
                converter.isExtremeTemperature(51)
        );

        assertFalse(
                converter.isExtremeTemperature(20)
        );

        assertFalse(
                converter.isExtremeTemperature(-10)
        );
    }
}