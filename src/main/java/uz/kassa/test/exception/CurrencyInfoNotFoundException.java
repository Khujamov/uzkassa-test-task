package uz.kassa.test.exception;

public class CurrencyInfoNotFoundException extends RuntimeException {
    public CurrencyInfoNotFoundException(String message) {
        super(message);
    }

    public CurrencyInfoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
