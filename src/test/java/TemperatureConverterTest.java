import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    TemperatureConverter converter = new TemperatureConverter();

    @org.junit.jupiter.api.Test
    void fahrenheitToCelsius() {
        assertEquals(0, converter.fahrenheitToCelsius(32), 0.0001);
        assertEquals(100, converter.fahrenheitToCelsius(212), 0.0001);
        assertEquals(-40, converter.fahrenheitToCelsius(-40), 0.0001);
    }

    @org.junit.jupiter.api.Test
    void celsiusToFahrenheit() {
        assertEquals(32, converter.celsiusToFahrenheit(0), 0.0001);
        assertEquals(212, converter.celsiusToFahrenheit(100), 0.0001);
        assertEquals(-40, converter.celsiusToFahrenheit(-40), 0.0001);
    }

    @org.junit.jupiter.api.Test
    void isExtremeTemperature() {
        assertTrue(converter.isExtremeTemperature(-41));
        assertTrue(converter.isExtremeTemperature(51));
        assertFalse(converter.isExtremeTemperature(20));
        assertFalse(converter.isExtremeTemperature(-10));
    }
}