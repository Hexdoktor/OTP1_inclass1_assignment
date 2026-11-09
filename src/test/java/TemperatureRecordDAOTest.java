import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureRecordDAOTest {

    @Test
    void shouldSaveRecord() {

        TemperatureRecordDAO dao =
                new TemperatureRecordDAO();

        TemperatureRecord record =
                new TemperatureRecord(
                        100,
                        212,
                        1,
                        2,
                        "en"
                );

        assertDoesNotThrow(
                () -> dao.save(record)
        );
    }

    @Test
    void shouldLoadHistory() {

        TemperatureRecordDAO dao =
                new TemperatureRecordDAO();

        assertNotNull(
                dao.getLastFiveConversions()
        );
    }
}