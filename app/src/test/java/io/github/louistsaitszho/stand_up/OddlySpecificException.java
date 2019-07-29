package io.github.louistsaitszho.stand_up;

/**
 * Just a placeholder-esq Exception, so that "is the exception being propagated" can be check easily
 */
public class OddlySpecificException extends Exception {
    public OddlySpecificException() {
    }

    public OddlySpecificException(String message) {
        super(message);
    }
}
