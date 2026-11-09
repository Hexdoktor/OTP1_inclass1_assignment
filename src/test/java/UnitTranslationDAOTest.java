import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UnitTranslationDAOTest {

    private final UnitTranslationDAO dao =
            new UnitTranslationDAO();

    @Test
    void shouldLoadEnglishUnits() {

        List<UnitTranslation> units =
                dao.getUnits("en");

        assertNotNull(units);

        assertFalse(units.isEmpty());
    }

    @Test
    void shouldFallbackToEnglish() {

        List<UnitTranslation> units =
                dao.getUnits("does_not_exist");

        assertNotNull(units);

        assertFalse(
                units.isEmpty()
        );
    }
}