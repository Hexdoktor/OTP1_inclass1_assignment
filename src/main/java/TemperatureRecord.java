public class TemperatureRecord {

    private double inputValue;
    private double resultValue;
    private int fromUnitId;
    private int toUnitId;
    private String language;

    public TemperatureRecord
            (
                    double inputValue,
                    double resultValue,
                    int fromUnitId,
                    int toUnitId,
                    String language) {

        this.inputValue = inputValue;
        this.resultValue = resultValue;
        this.fromUnitId = fromUnitId;
        this.toUnitId = toUnitId;
        this.language = language;
    }

    public double getInputValue() {
        return inputValue;
    }

    public double getResultValue() {
        return resultValue;
    }

    public int getFromUnitId() {
        return fromUnitId;
    }
    public int getToUnitId(){return toUnitId;}

    public String getLanguage(){
        return language;
    }
}