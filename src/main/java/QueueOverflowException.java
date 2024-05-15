/**
 * Class for queue overflow exception since that doesn't exist.
 */
public class QueueOverflowException extends RuntimeException{
    /**
     * Default constructor.
     */
    public QueueOverflowException(){
        super("Tried to queue onto a full queue.");
    }

    /**
     * Constructor with custom message.
     * @param s message for the exception.
     */
    public QueueOverflowException(String s){
        super(s);
    }
}
