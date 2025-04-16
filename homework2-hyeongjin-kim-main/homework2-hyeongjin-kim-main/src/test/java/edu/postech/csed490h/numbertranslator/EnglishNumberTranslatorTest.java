package edu.postech.csed490h.numbertranslator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnglishNumberTranslatorTest {

    final EnglishNumberTranslator translator = new EnglishNumberTranslator();

    @ParameterizedTest
    @CsvSource({
            "0, zero",
            "1234, one thousand two hundred thirty-four",
            "10000001201, ten billion one thousand two hundred one",
            "1234567890, one billion two hundred thirty-four million five hundred sixty-seven thousand eight hundred ninety",
            "31057642375, thirty-one billion fifty-seven million six hundred forty-two thousand three hundred seventy-five"
    })
    void testToWords(long number, String expected) {
        assertEquals(expected, translator.toWords(number));
    }

    @ParameterizedTest
    @CsvSource({
            "zero, 0",
            "one thousand two hundred thirty-four, 1234",
            "ten billion one thousand two hundred one, 10000001201",
            "one billion two hundred thirty-four million five hundred sixty-seven thousand eight hundred ninety, 1234567890",
            "thirty-one billion fifty-seven million six hundred forty-two thousand three hundred seventy-five, 31057642375"
    })
    void testToNumber(String number, long expected) {
        assertEquals(expected, translator.toNumber(number));
    }
}