import java.util.EmptyStackException;
/**
 * This class implements a index (or array) based stack.
 * @param <E> the type of object within the stack.
 * @author Adhitya Thirumala
 */
public class IndexStack<E> implements Stack<E> {

    /**
     * The array containing the elements of the stack.
     */
    private final E[] arr;

    /**
     * The index of the top of the stack.
     */
    private int top;

    /**
     * The maximum amount of elements that the stack can contain.
     */
    private final int size;

    /**
     * Default constructor for the stack. Initializes an empty array and sets top to -1.
     * @param size Max amount of elements that the stack can contain.
     */
    public IndexStack (int size){
        //noinspection unchecked
        arr = (E[]) new Object[size];
        top = -1;
        this.size = size;
    }

    /**
     * Removes the top element of the stack and returns the removed element.
     * @return the removed element.
     * @throws EmptyStackException if stack is empty and there is nothing to pop.
     */
    @Override
    public E pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return arr[top--];
    }

    /**
     * Returns the top value of the stack.
     * @return top value of stack.
     * @throws EmptyStackException if the stack is empty and there is nothing to peek.
     */
    @Override
    public E peek() throws EmptyStackException{
        if(isEmpty())
            throw new EmptyStackException();
        return arr[top];
    }

    /**
     * Adds value to the front of the stack.
     * @param e the value of the node to be added.
     * @throws StackOverflowException if an element is to be pushed upon to a full array.
     */
    @Override
    public void push(E e) throws StackOverflowException {
        top++;
        if(top >= size)
            throw new StackOverflowException();
        arr[top] = e;
    }

    /**
     * Checks if the stack is empty. The top value will be -1 at initialization or when the top is zero, and then one is subtracted when popping that value.
     * @return True if the stack is empty and False if the stack is not empty.
     */
    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}
