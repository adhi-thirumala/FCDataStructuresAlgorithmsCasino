/**
 * This class implements one node of a linked list.
 * @author Adhitya Thirumala
 * @param <E> type of object in the node
 */
public class Node<E>{
    /**
     * This is the field containing the data of the node.
     */
    private E data;

    /**
     * This is the field containing the pointer of the next node in the linked list.
     */
    private Node<E> next;


    /**
     * This is the constructor for the node.
     * @param newData representing the data for the node.
     * @param newNext the pointer to the next node.
     */
    public Node(E newData, Node<E> newNext){
        data = newData;
        next = newNext;
    }

    /**
     * This is a getter for the data of the node.
     * @return data of the node.
     */
    public E getData(){
        return data;
    }

    /**
     * This is a getter for the pointer to the next node.
     * @return the next node.
     */
    public Node<E> getNext(){
        return next;
    }

    /**
     * This is a setter for the data of the node.
     * @param newData representing the data to be set.
     */
    public void setData(E newData){
        data = newData;
    }

    /**
     * This is a method that sets the next node that the current node points to.
     * @param newNext pointer of the next node.
     */
    public void setNext(Node<E> newNext){
        next = newNext;
    }


    /**
     * This is a toString for the node. It returns the toString for the data of the node.
     * @return data's toString output.
     */
    public String toString(){
        return data.toString();
    }
}