package edu.postech.csed490h.numbertranslator;

import java.util.Locale;

/**
 * A number translator translates a number to words and vice versa, according
 * to its locale. Each subclass of this class considers a specific locale.
 * In this assignment, you should implement EnglishNumberTranslator and
 * KoreanNumberTranslator.
 */
public interface NumberTranslator {

    /**
     * Convert a number to words, according to the locale of this translator.
     * The number should be in the range [0, Long.MAX_VALUE], and an exception
     * should be thrown if the number is not in the range.
     *
     * @param number a number
     * @return a string of words
     * @throws IllegalArgumentException if the number is not in the range
     */
    String toWords(long number);

    /**
     * Convert words to a number, according to the locale of this translator.
     * The words should represent a number in the range [0, Long.MAX_VALUE].
     * This function is an inverse function of toWords.
     *
     * @param words a string of words
     * @return a number
     * @throws IllegalArgumentException if the words do not represent a number
     *                                  in the range
     */
    long toNumber(String words);

    /**
     * Return the locale of this translator.
     *
     * @return a locale
     */
    Locale getLocale();
}
