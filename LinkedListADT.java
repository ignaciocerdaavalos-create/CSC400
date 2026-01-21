// CustomLinkedList.java
public class CustomLinkedList {

    // ---------- Node class ----------
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;   // start of the list

    // ---------- Insert ----------
    public void insert(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
    }

    // ---------- Delete first occurrence ----------
    public void delete(int data) {
        if (head == null) return;

        // If the head is the node to delete
        if (head.data == data) {
            head = head.next;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.data != data) {
            current = current.next;
        }

        // Found the node
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    // ---------- Iterator ----------
    public LinkedListIterator iterator() {
        return new LinkedListIterator();
    }

    // ---------- Inner Iterator Class ----------
    public class LinkedListIterator {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public int next() {
            int value = current.data;
            current = current.next;
            return value;
        }
    }

    // ---------- Demo ----------
    public static void main(String[] args) {
        CustomLinkedList list = new CustomLinkedList();

        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.insert(40);

        System.out.println("List contents:");
        CustomLinkedList.LinkedListIterator it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }

        System.out.println("\n\nDeleting 20...");
        list.delete(20);

        System.out.println("List contents after deletion:");
        it = list.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }
}