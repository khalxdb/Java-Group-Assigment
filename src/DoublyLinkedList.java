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

    // Shuffle method

    // FORWARD TRAVERSAL

    // BACKWARD TRAVERSAL
    
}
