/**
 * This class creates a StackOverflowException class. It signifies when one tries to add to a full stack. The existing code that for this is an error (StackOverflowError), which would signify an issue that would be significantly worse than the one that we are trying to signify.
 * @author Adhitya Thirumala and ChatGPT 3.5
 */
public class StackOverflowException extends RuntimeException{
    /**
     * Default Constructor for Exception. Shows default message.
     */
    public StackOverflowException() {
        super("Stack overflow occurred.");
    }

    /**
     * Allows user to give a custom message.
     * @param message String that the user wants in the exception.
     */
    public StackOverflowException(String message) {
        super(message);
    }
}
