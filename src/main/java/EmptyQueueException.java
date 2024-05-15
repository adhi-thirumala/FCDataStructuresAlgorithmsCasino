/**
 * This class creates an exception for use when scenarios related to an empty queue happen.
 * @author Adhitya Thirumala
 */
public class EmptyQueueException extends RuntimeException{
    /**
     * Default constructor with default message.
     */
    public EmptyQueueException(){
        super("The queue is empty");
    }

    /**
     * Constructor that gives custom message.
     * @param s the message for the exception.
     */
    public EmptyQueueException(String s){
        super(s);
    }
}
