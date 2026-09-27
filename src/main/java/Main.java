public class Main {
    public static void main(String[] args) {
        TemperatureConverter converter = new TemperatureConverter();

        double f = 100;
        double c = converter.fahrenheitToCelsius(f);
        System.out.println(f + "°F = " + c + "°C");

        double celsius = 0;
        double fahrenheit = converter.celsiusToFahrenheit(celsius);
        System.out.println(celsius + "°C = " + fahrenheit + "°F");

        double kelvin = 300;
        double celsiusFromKelvin = converter.kelvinToCelsius(kelvin);
        System.out.println(kelvin + "K = " + celsiusFromKelvin + "°C");

        System.out.println("Is 51°C extreme? " + converter.isExtremeTemperature(51));
        System.out.println("Is 20°C extreme? " + converter.isExtremeTemperature(20));
    }
}