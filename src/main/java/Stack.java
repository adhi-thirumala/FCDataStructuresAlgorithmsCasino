/**
 * This is an interface upon which one can build a stack. A stack in coding is a data structure that follows a last-in, first-out (LIFO) principle, akin to a stack of plates. It's used for managing temporary data and is crucial in various algorithms and applications, such as managing function calls and undo mechanisms.
 * @author Adhitya Thirumala.
 * @param <E> The type of object that will be contained in the stack.
 */
public interface Stack<E>{
    /**
     * Makes an object the new top of the stack.
     * @param e the object to be added.
     */
    void push(E e);

    /**
     * Removes the top object of the stack and returns the value that was removed.
     * @return the previous top object of the stack.
     */
    E pop();

    /**
     * Checks if the stack is empty.
     * @return True if the stack is empty and False if the stack is not empty.
     */
    boolean isEmpty();

    /**
     * Returns top object in the stack.
     * @return top object in the stack.
     */
    E peek();
}
