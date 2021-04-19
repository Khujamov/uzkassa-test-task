package uz.kassa.test.exception;

/** Author: Khumoyun Khujamov Date: 11/16/20 Time: 12:13 PM */
public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
