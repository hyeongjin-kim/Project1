package edu.postech.csed490h.numbertranslator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KoreanNumberTranslatorTest {

    final KoreanNumberTranslator translator = new KoreanNumberTranslator();

    @ParameterizedTest
    @CsvSource({
            "0, 영",
            "1234, 천이백삼십사",
            "10000001201, 백억천이백일",
            "1234567890, 십이억삼천사백오십육만칠천팔백구십",
            "31057642375, 삼백십억오천칠백육십사만이천삼백칠십오"
    })
    void testToWords(long number, String expected) {
        assertEquals(expected, translator.toWords(number));
    }

    @ParameterizedTest
    @CsvSource({
            "영, 0",
            "천이백삼십사, 1234",
            "백억천이백일, 10000001201",
            "십이억삼천사백오십육만칠천팔백구십, 1234567890",
            "삼백십억오천칠백육십사만이천삼백칠십오, 31057642375"
    })
    void testToNumber(String number, long expected) {
        assertEquals(expected, translator.toNumber(number));
    }
}