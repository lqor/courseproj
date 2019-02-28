package HelpClasses;

public class InvalidRequestException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  public InvalidRequestException() {
    super();
  }

  public InvalidRequestException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public InvalidRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public InvalidRequestException(String message) {
    super(message);
  }

  public InvalidRequestException(Throwable cause) {
    super(cause);
  }

}
