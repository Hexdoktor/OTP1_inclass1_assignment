public class TemperatureRecord {

    private double inputValue;
    private double resultValue;
    private int unitId;

    public TemperatureRecord
            (
                    double inputValue,
                    double resultValue,
                    int unitId
            ) {

        this.inputValue = inputValue;
        this.resultValue = resultValue;
        this.unitId = unitId;
    }

    public double getInputValue() {
        return inputValue;
    }

    public double getResultValue() {
        return resultValue;
    }

    public int getUnitId() {
        return unitId;
    }
}