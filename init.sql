DROP DATABASE IF EXISTS tempconverter;

CREATE DATABASE tempconverter
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE tempconverter;

-- =====================================================
-- Temperature Units
-- =====================================================

CREATE TABLE temperature_unit (

                                  id INT AUTO_INCREMENT PRIMARY KEY,

                                  unit_key VARCHAR(50) NOT NULL UNIQUE
);

-- =====================================================
-- Unit Translations
-- Translation Table Approach
-- =====================================================

CREATE TABLE unit_translation (

                                  id INT AUTO_INCREMENT PRIMARY KEY,

                                  unit_id INT NOT NULL,

                                  lang VARCHAR(5) NOT NULL,

                                  name VARCHAR(100) NOT NULL,

                                  description TEXT,

                                  CONSTRAINT fk_unit_translation
                                      FOREIGN KEY (unit_id)
                                          REFERENCES temperature_unit(id)
                                          ON DELETE CASCADE
);

-- =====================================================
-- Conversion History
-- =====================================================

CREATE TABLE temperature_record (

                                    id INT AUTO_INCREMENT PRIMARY KEY,

                                    input_value DOUBLE NOT NULL,

                                    result_value DOUBLE NOT NULL,

                                    from_unit_id INT NOT NULL,

                                    to_unit_id INT NOT NULL,

                                    language VARCHAR(5) NOT NULL,

                                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

                                    CONSTRAINT fk_from_unit
                                        FOREIGN KEY (from_unit_id)
                                            REFERENCES temperature_unit(id),

                                    CONSTRAINT fk_to_unit
                                        FOREIGN KEY (to_unit_id)
                                            REFERENCES temperature_unit(id)
);

-- =====================================================
-- Insert Temperature Units
-- =====================================================

INSERT INTO temperature_unit (unit_key)
VALUES
    ('celsius'),
    ('fahrenheit'),
    ('kelvin');

-- =====================================================
-- Celsius
-- =====================================================

INSERT INTO unit_translation
(unit_id, lang, name, description)
VALUES

    (1, 'en', 'Celsius',
     'Metric temperature scale'),

    (1, 'fi', 'Celsius',
     'Metrinen lämpötila-asteikko'),

    (1, 'sv', 'Celsius',
     'Metrisk temperaturskala'),

    (1, 'ja', '摂氏',
     '温度の尺度'),

    (1, 'ar', 'سيلسيوس',
     'مقياس درجة الحرارة');

-- =====================================================
-- Fahrenheit
-- =====================================================

INSERT INTO unit_translation
(unit_id, lang, name, description)
VALUES

    (2, 'en', 'Fahrenheit',
     'Imperial temperature scale'),

    (2, 'fi', 'Fahrenheit',
     'Imperiaalinen lämpötila-asteikko'),

    (2, 'sv', 'Fahrenheit',
     'Imperial temperaturskala'),

    (2, 'ja', '華氏',
     '温度の尺度'),

    (2, 'ar', 'فهرنهايت',
     'مقياس درجة الحرارة');

-- =====================================================
-- Kelvin
-- =====================================================

INSERT INTO unit_translation
(unit_id, lang, name, description)
VALUES

    (3, 'en', 'Kelvin',
     'Absolute temperature scale'),

    (3, 'fi', 'Kelvin',
     'Absoluuttinen lämpötila-asteikko'),

    (3, 'sv', 'Kelvin',
     'Absolut temperaturskala'),

    (3, 'ja', 'ケルビン',
     '絶対温度尺度'),

    (3, 'ar', 'كلفن',
     'مقياس الحرارة المطلق');