package edu.postech.csed490h.numbertranslator;

import java.util.Locale;

/**
 * An abstract superclass for number translators, which implements the getLocale method.
 * Note: feel free to add more methods and (package-private) fields, if necessary
 */
public abstract class AbstractNumberTranslator implements NumberTranslator {

    private final Locale locale; // the locale of this translator

    AbstractNumberTranslator(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
