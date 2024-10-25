package app.util;
import app.model.*;

public class CircularDoublyLinkedList {
    public Node head;
    public Node tail;
    
    // Constructor for the CircularDoublyLinkedList.
    public CircularDoublyLinkedList(){
        this.head = null;
        this.tail = null;
    }

    /**
     * Adds a new song to the end of the list.
     *
     * @param song The song to add to the list.
     */
    public void addNode(Song song){
        // Create New Node with the given songs
        Node newNode = new Node(song);

        // If the list is empty, initialized head and tail to the new Node;
        if (head == null){
            head = newNode;
            tail = newNode;

            // For a circular linked list, it's next and prev point to itself
            head.next = head; 
            head.prev = head;  
        }
        else{
            /*
             * Insert the new node between the tail and the head to maintain the circular structure.
             * Steps:
             * 1. Set the new node's prev to the current tail.
             * 2. Set the new node's next to the current head.
             * 3. Update the tail's next to the new node.
             * 4. Update the head's prev to the new node.
             * 5. Update the tail reference to point to the new node.
             */
            newNode.prev = tail;
            newNode.next = head;
            tail.next = newNode;   
            head.prev = newNode;    
            tail = newNode;     
        }
    }

    // Print out lists of songs.
    public void printList(){
        if (head == null) { // Check if Lists is empty
            System.out.println("List is empty.");
            return;
        }
        /*
         * To traverse a circular linked lists we must going next until we return back to the head
         * this is done with the do while loop.
         * We performed the action until we reach the head again.
         */
        Node curNode = head; // Make a references copy of the head for comparison late
        do {
            System.out.print(curNode.song.title + " -> ");
            curNode = curNode.next;
        } 
        while (curNode != head); // Stop when we loop back to the head
        System.out.println();
    }

    /**
     * Retrieves the song at the specified index.
     *
     * @param index The index of the song to retrieve.
     * @return The song at the specified index, or null if index is invalid.
     */
    public Song get(int index) {
        if (index < 0 || head == null) { 
            return null;  // Invalid index or empty list
        }

        Node curNode = head;
        int curIndex = 0;

        do {
            if (curIndex == index) {
                return curNode.song;
            }
            curIndex++;
            curNode = curNode.next;
        } 
        while (curNode != head && curIndex <= index);  // Stop if looped back to head or found index
        return null;  // Index is out of bounds
    }

    /**
     * Gets the number of songs in the list.
     * @return The size of the list.
     */
    public int size() {
        if (head == null) {
            return 0; // Empty list check
        }

        Node curNode = head;
        int size = 0;

        do {
            size++;
            curNode = curNode.next;
        } 
        while (curNode != head);  // Stop when we loop back to the head
        return size;
    }

    /**
     * Checks if the list contains a specific song.
     * @param song The song to search for in the list.
     * @return True if the song is found, false otherwise.
     */
    public boolean contains(Song song) {
        if (head == null) {
            return false;  // Empty list
        }

        Node curNode = head;

        do {
            if (curNode.song.equals(song)) {  // Use equals method to compare songs
                return true;  // Song found in the list
            }
            curNode = curNode.next;
        } 
        while (curNode != head);  // Stop when we loop back to the head
        return false;  // Song not found after traversing the list
    }

    /**
     * Given a song remove it from the doubly linked list
     * @param Song
     * @return true if the song was removed successfully, false otherwise
     */
    public void removeNode(Song Node){
        //TODO: removeNode from the Circular Doubly Linked List
    }
}
