package it.sevenbits.formatter.exception;

/**
 * enum for error codes for Formatter exc-s*/
public enum FormatterErrorCode {
    /**
     * file reading error code*/
    ERROR_READING_FILE("error reading file"),
    /**
     * no more tokens*/
    NO_TOKENS("no more tokens for lexer");



    private String errorString;
    /**
     * errorcode constructor
     * @param errorString - error code message*/
    FormatterErrorCode(final String errorString) {
        this.errorString = errorString;
    }

    public String getErrorCode() {
        return errorString;
    }
}
