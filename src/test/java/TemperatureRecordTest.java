import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureRecordTest {

    @Test
    void constructorWorks() {

        TemperatureRecord record =
                new TemperatureRecord(
                        100,
                        212,
                        1
                );

        assertEquals(
                100,
                record.getInputValue()
        );

        assertEquals(
                212,
                record.getResultValue()
        );

        assertEquals(
                1,
                record.getUnitId()
        );
    }
}