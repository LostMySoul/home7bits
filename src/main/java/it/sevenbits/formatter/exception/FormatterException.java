package it.sevenbits.formatter.exception;

/**
 * Exception class for Formatter*/
public class FormatterException extends Exception {
    private FormatterErrorCode errorCode;

    /**
     * constructor #1 for excp-s with throwable cause
     * @param code - error message
     * @param cause - cause throwable*/
    public FormatterException(final FormatterErrorCode code, final Throwable cause) {
        super(code.getErrorCode(), cause);
        this.errorCode = code;
    }

    /**
     * constructor #1 for excp-s w/out throwable cause
     * @param code - error message*/
    public FormatterException(final FormatterErrorCode code) {
        super(code.getErrorCode());
        this.errorCode = code;
    }


    public FormatterErrorCode getErrorCode() {
        return errorCode;
    }

}
