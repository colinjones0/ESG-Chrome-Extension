package edu.brown.cs.student.esg;

/**
 * Generic exception that allows for tossing up and simply printing error message.
 */
public class UserFriendlyException extends Exception {
  /**
   * Simply a wrapper for the super exception's constructor.
   * @param message Error message.
   */
  public UserFriendlyException(String message) {
    super(message);
  }
}
