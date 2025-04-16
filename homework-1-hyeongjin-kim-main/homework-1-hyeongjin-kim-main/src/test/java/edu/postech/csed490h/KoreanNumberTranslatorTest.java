package edu.postech.csed490h;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class KoreanNumberTranslatorTest {

    KoreanNumberTranslator translator = new KoreanNumberTranslator();

    @ParameterizedTest
    @CsvSource({
            "0, 영",
            "1234, 천이백삼십사",
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
            "십이억삼천사백오십육만칠천팔백구십, 1234567890",
            "삼백십억오천칠백육십사만이천삼백칠십오, 31057642375"
    })
    void testToNumber(String number, long expected) {
        assertEquals(expected, translator.toNumber(number));
    }
}