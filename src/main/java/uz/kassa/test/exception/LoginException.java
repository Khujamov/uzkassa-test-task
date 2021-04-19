package uz.kassa.test.exception;

/** Author: Khumoyun Khujamov Date: 11/14/20 Time: 2:34 AM */
public class LoginException extends RuntimeException {
  public LoginException(String message) {
    super(message);
  }

  public LoginException(String message, Throwable cause) {
    super(message, cause);
  }
}
