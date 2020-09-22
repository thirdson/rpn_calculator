package com.aaron.rpn.exception;

/**
 * wrapper calculator math exception
 */
public class MathException extends Exception {

  public MathException() {
    super();
  }

  public MathException(String message) {
    super(message);
  }

  public MathException(String message, Throwable cause) {
    super(message, cause);
  }

  public MathException(Throwable cause) {
    super(cause);
  }

  protected MathException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
