package pro.sky.HomeWork2_16.exception;

public class BadIndexException extends RuntimeException {
    public BadIndexException() {
    }

    public BadIndexException(String message) {
        super(message);
    }

    public BadIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadIndexException(Throwable cause) {
        super(cause);
    }

    public BadIndexException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
