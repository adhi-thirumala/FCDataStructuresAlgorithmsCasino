/**
 * This class implements an array (or index) based queue.
 * @author Adhitya Thirumala
 * @param <E> type of object in the queue
 */
public class IndexQueue<E> implements Queue<E>{
    /**
     * int representing maximum amount of objects in array.
     */
    private final int size;
    /**
     * array to hold items.
     */
    private final E[] items;
    /**
     * index of the front of the queue.
     */
    private int front;
    /**
     * index of the back of the queue.
     */
    private int back;
    /**
     * amount of objects within the queue.
     */
    private int count;

    /**
     * Default constructor
     * @param size max amount of objects in queue.
     */
    public IndexQueue(int size){
        this.size = size;
        //noinspection unchecked
        items = (E[])new Object[size];
        front = 0;
        back = size - 1;
        count = 0;
    }

    /**
     * @return True if the list is empty and False if the list is not empty.
     */
    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    /**
     * Checks if list is full.
     * @return True if the list is full and False if the list is not full.
     */
    public boolean isFull(){
        return count == size;
    }

    /**
     * @param e the object to be added.
     * @throws QueueOverflowException if object is tried to be added to full queue.
     */
    @Override
    public void enqueue(E e) throws QueueOverflowException{
        if(!isFull()){
            back = (back + 1) % size;
            items[back] = e;
            count++;
        }
        else
            throw new QueueOverflowException();
    }

    /**
     * @return the object that was removed from the queue.
     * @throws EmptyQueueException if object is tried to be removed from an empty queue.
     */
    @Override
    public E dequeue() throws EmptyQueueException{
        if(!isEmpty()){
            E queueFront = items[front];
            front = (front + 1) % (size);
            count--;
            return queueFront;
        }
        throw new EmptyQueueException();
    }

    /**
     * @return the object that is at the front of the queue..
     * @throws EmptyQueueException if empty queue tries to be accessed.
     */
    @Override
    public E peek() throws EmptyQueueException{
        if(!isEmpty())
            return items[front];
        else
            throw new EmptyQueueException();
    }
}
