/**
 * This program implements a reference-based (linked list) queue.
 * @param <E> the type of object to be held in the queue.
 * @author Adhitya Thirumala
 */
public class ReferenceQueue<E> implements Queue<E>{
    /**
     * Pointer to the front of the queue.
     */
    private Node<E> front;

    /**
     * Pointed to the back of the queue.
     */
    private Node<E> back;

    /**
     * Default constructor for the queue, initializes front and back as null.
     */
    public ReferenceQueue(){
        front = null;
        back = null;
    }

    /**
     * Checks if queue is empty. If the front of the list is null, the list is empty.
     * @return True if the list is empty and False if the list is empty.
     */
    @Override
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Adds node to font of the queue.
     * @param e the data in the node to be added.
     */
    @Override
    public void enqueue(E e) {
        if (back != null) {
            back.setNext(new Node<>(e, null));
            back = back.getNext();
        }
        else{
            back = new Node<>(e, null);
            front = back;
        }
    }

    /**
     * Removes front node from queue and returns the value in the removed node.
     * @return the value from the removed node.
     * @throws EmptyQueueException if one tries to remove an object from an empty queue.
     */
    @Override
    public E dequeue() throws EmptyQueueException{
        if(isEmpty())
            throw new EmptyQueueException();
        E e = front.getData();
        front = front.getNext();
        if(isEmpty())
            back = null;
        return e;
    }

    /**
     * Returns value in the front node of the queue.
     * @return value in the front node of the queue.
     * @throws EmptyQueueException if one tries to look at the front node of an empty queue.
     */
    @Override
    public E peek() throws EmptyQueueException {
        if(isEmpty())
            throw new EmptyQueueException();
        return front.getData();
    }
}
