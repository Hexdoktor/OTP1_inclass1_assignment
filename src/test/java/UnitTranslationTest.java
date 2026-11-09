import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitTranslationTest {

    @Test
    void shouldStoreValuesCorrectly() {

        UnitTranslation unit =
                new UnitTranslation(
                        1,
                        "Celsius",
                        "Metric temperature scale"
                );

        assertEquals(
                1,
                unit.getUnitId()
        );

        assertEquals(
                "Celsius",
                unit.getName()
        );

        assertEquals(
                "Metric temperature scale",
                unit.getDescription()
        );
    }

    @Test
    void toStringShouldReturnTranslatedName() {

        UnitTranslation unit =
                new UnitTranslation(
                        3,
                        "Kelvin",
                        "Absolute temperature scale"
                );

        assertEquals(
                "Kelvin",
                unit.toString()
        );
    }

    @Test
    void shouldSupportNonAsciiCharacters() {

        UnitTranslation japanese =
                new UnitTranslation(
                        1,
                        "摂氏",
                        "温度の尺度"
                );

        UnitTranslation arabic =
                new UnitTranslation(
                        1,
                        "سيلسيوس",
                        "مقياس درجة الحرارة"
                );

        assertEquals(
                "摂氏",
                japanese.getName()
        );

        assertEquals(
                "温度の尺度",
                japanese.getDescription()
        );

        assertEquals(
                "سيلسيوس",
                arabic.getName()
        );

        assertEquals(
                "مقياس درجة الحرارة",
                arabic.getDescription()
        );
    }
}