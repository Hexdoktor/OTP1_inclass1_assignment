import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureRecordTest {

    @Test
    void constructorStoresValues() {

        TemperatureRecord record =
                new TemperatureRecord(
                        100,
                        212,
                        1,
                        2,
                        "en"
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
                record.getFromUnitId()
        );

        assertEquals(
                2,
                record.getToUnitId()
        );

        assertEquals(
                "en",
                record.getLanguage()
        );
    }
}