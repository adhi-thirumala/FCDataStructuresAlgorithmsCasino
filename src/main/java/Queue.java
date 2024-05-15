/**
 * This is an interface upon which a queue can be built. A queue is a data structure that operates on a first-in, first-out (FIFO) principle. Similar to a line of people waiting for service, items are added to the rear and removed from the front. Queues are commonly used in programming for tasks like managing tasks, scheduling, and asynchronous communication.
 * @author Adhitya Thirumala
 * @param <E> The type of object that will be stored in the queue.
 */
public interface Queue<E>{
    /**
     * Checks if the queue is empty.
     * @return True if the queue is empty and False if the queue is not.
     */
    boolean isEmpty();

    /**
     * Adds object to the end of the stack.
     * @param e the object to be added.
     */
    void enqueue(E e);

    /**
     * Removes value from the front of the queue and returns it.
     * @return the value that was removed.
     */
    E dequeue();

    /**
     * Shows the first object in the queue.
     * @return the front object in the queue.
     */
    E peek();
}
