import java.util.EmptyStackException;

/**
 * This class implements a reference-based (linked list).
 * @author Adhitya Thirumala
 * @param <E> the type of object being placed in the stack.
 */
public class ReferenceStack<E> implements Stack<E>{
    /**
     * Field containing pointer to the first element of the stack.
     */
    private Node<E> top;

    /**
     * Constructor for the stack, initializes the top as null.
     */
    public ReferenceStack(){
        top = null;
    }

    /**
     * Sets the next value of the created node to the current top node.
     * @param e the value of the node to be added.
     */
    @Override
    public void push(E e) {
        top = new Node<E>(e, top);
    }

    /**
     * Shows the top value of the stack.
     * @return the top value of the stack.
     * @throws EmptyStackException if the stack is empty and there is nothing to peek.
     */
    @Override
    public E peek() throws EmptyStackException{
        if(isEmpty())
            throw new EmptyStackException();
        return top.getData();
    }

    /**
     * Returns the top value of the stack and then sets the top value of the stack to the top's next node.
     * @return the previous top node in the stack.
     * @throws EmptyStackException if the stack is empty and there is nothing to pop.
     */
    @Override
    public E pop() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        E e = top.getData();
        top = top.getNext();
        return e;
    }

    /**
     * Checks if the top value of the stack is null.
     * @return True if stack is empty and False if stack is not empty.
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

}
