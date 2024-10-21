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
        }
        else{
            /*
             * Set the current tails' next to newNode,
             * connect the NewNode previous to the current tail,
             * update the tail
             */
            tail.next = newNode; 
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Print out Node
    public void printList(){
        Node current = head;
        while (current != null) {
            System.out.print(current.song.getTitle() + " ");
            current = current.next;
        }
        System.out.println();
    }

    public Song get(int index) {
        // Check if index is negative
        if (index < 0) { 
            return null; 
        }
    
        Node current = head;
        int count = 0;
    
        // Traverse the list until we reach the desired index
        while (current != null) {
            if (count == index) {
                return current.song;
            }
            count++;
            current = current.next;
        }
    
        return null; // If index is out of bounds, return null
    }
    

    //Method: get the size of doubly linked list: 
    public int size(){
        return getSizeRecursive(head);
    }

    // Helper function to get size
    public int getSizeRecursive(Node node) {
        // Base case: if the node is null
        if (node == null) {
            return 0;
        }
        // Recursive case: return 1
        return 1 + getSizeRecursive(node.next);
    }

    // TODO: Method for checking if the list contains a specific song
    public boolean contains(Song song) {
        Node current = head;
        while (current != null) {
            if (current.song.equals(song)) { // Use equals method to compare songs
                return true; // Song found in the list
            }
            current = current.next; // Move to the next node
        }
        return false; // Song not found after traversing the list
    }

    // public boolean contains(){
    //     Node current = head;
    //     while ( current != null){
    //         if (current.song == this.song)
    //         current = current.next;
    //     }
    // }

    // Shuffle method

    // FORWARD TRAVERSAL

    // BACKWARD TRAVERSAL
    
}
