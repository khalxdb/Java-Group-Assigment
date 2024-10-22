public class DoublyLinkedList {
    Node head, tail;
    
    public DoublyLinkedList(){
        this.head = null;
        this.tail = null;
    }

    /*
     * add a new node to a list
     */

    public void addNode(Song song){
        // Create a new node 
        Node newNode = new Node(song);
        // if list is empty than the new Node become the head and the tail
        if (head == null){
            head = newNode;
            tail = newNode;
            head.next = head;  // Circular link for single node
            head.prev = head;  // Circular link for single node
        }
        else{
            /*
             * Set the current tails' next to newNode,
             * connect the NewNode previous to the current tail,
             * update the tail
             */
            tail.next = newNode;  // Add the new node at the end
            newNode.prev = tail;
            tail = newNode;      // Update the tail
            tail.next = head;    // Circular link from tail to head
            head.prev = tail;    // Circular link from head to tail
        }
    }

    // Print out Node
    public void printList(){
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node current = head;
        do {
            System.out.print(current.song.getTitle() + " ");
            current = current.next;
        } while (current != head); // Stop when we loop back to the head
        System.out.println();
    }

    public Song get(int index) {
        if (index < 0 || head == null) { 
            return null;  // Invalid index or empty list
        }

        Node current = head;
        int count = 0;

        do {
            if (count == index) {
                return current.song;
            }
            count++;
            current = current.next;
        } while (current != head && count <= index);  // Stop if looped back to head or found index

        return null;  // If index is out of bounds, return null
    }

    // Method: Get the size of the doubly linked list
    public int size() {
        if (head == null) {
            return 0; // Empty list
        }

        Node current = head;
        int size = 0;

        do {
            size++;
            current = current.next;
        } while (current != head);  // Stop when we loop back to the head

        return size;
    }

    // TODO: Method for checking if the list contains a specific song
    public boolean contains(Song song) {
        if (head == null) {
            return false;  // Empty list, return false
        }

        Node current = head;

        do {
            if (current.song.equals(song)) {  // Use equals method to compare songs
                return true;  // Song found in the list
            }
            current = current.next;
        } while (current != head);  // Stop when we loop back to the head

        return false;  // Song not found after traversing the list
    }

    public boolean isEmpty() {
        return head == null;  // If head is null, the list is empty
    }



    // FORWARD TRAVERSAL

    // BACKWARD TRAVERSAL
    
}
