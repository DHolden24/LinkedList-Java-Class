/**
 * A linked list class with test cases
 * 
 * @author 	Darren Holden
 * @date 	10/02/2017
 *
 */
public class TestListReferenceBased {
	private static Node head;		// Variable to hold the front of the list
	
	/**
	 * main function to test various cases
	 */
	public static void main(String[] args) {
		head = null;
		add(0, new Node(18));
		add(0, new Node(25));
		add(0, new Node(3));
		add(0, new Node(12));
		printList();
		add(0, new Node(13));
		printList();
		add(2, new Node(17));
		printList();
		remove(4);
		printList();
	}
	
	/**
	 * size		Returns the size of the linked list
	 * 
	 * @return	The size of the linked list
	 */
	public static int size() {
		int i = 0;			// Initialize a counter
		Node curr = head;	// Initialize a pointer to the head node
		
		// While the current pointer is not null
		while (curr != null) {
			i ++;			// Increment the counter
			curr = curr.getNext();		// Move to the next node
		}
		
		return i;			// Return the size of the list
	}
	
	/**
	 * isEmpty	Returns whether the list is empty or not
	 * 
	 * @return	True if the list is empty, false otherwise
	 */
	public static boolean isEmpty() {
		
		// If the head of the list is null
		if (head == null) {
			return true;	// The list is empty
		}
		
		return false;		// The list is not empty
	}
	
	/**
	 * add				Adds a node at the specified index, or to the end if the index exceeds the size of the list
	 * 
	 * @param index		The index where the node is inserted
	 * @param node		The node to be inserted
	 */
	public static void add(int index, Node node) {
		
		// If the list is empty
		if (size() == 0) {
			head = node;			// The head points to the new node
			node.setNext(null);		// The new node is the end of the list
			
		// Else if the node is inserted at the front
		} else if (index == 0) {
			node.setNext(head);		// The node points to the current head node
			head = node;			// The node becomes the new head node
			
		// The node is not being inserted at the front
		} else {
			Node curr = head.getNext();		// Set the current pointer to the node at index 1
			Node prev = head;				// Set the previous pointer to the head node (index 0)
			int i = 1;						// Set the counter to the index of the current node
			
			// While the index is below the desired insertion location, and the end of the list is not reached
			while (i < index && curr.getNext() != null) {
				prev = curr;				// Set the previous pointer to the current pointer
				curr = curr.getNext();		// Step the current pointer forward
				i += 1;						// Increment the counter
			}
			
			node.setNext(curr);				// Set the inserted node's pointer to the current pointer
			prev.setNext(node);				// Set the previous node's pointer to the inserted node
		}
	}
	
	/**
	 * remove			Removes the node at the specified index. Does nothing if the index exceeds the size of the list.
	 * 
	 * @param index		The index of the node to be removed
	 */
	public static void remove(int index) {
		Node curr = head.getNext();		// Set the current pointer to the node at index 1
		Node prev = head;				// Set the previous pointer to the head node (index 0)
		
		// If the node to be removed is the head node
		if (index == 0) {
			head.setNext(null);			// Set the head node's pointer to null
			prev = null;				// Set prev to null
			head = curr;				// The current node is the new head node
			
		// If the node to be removed is not the head node
		} else {
			
			// If the index of the node to be removed is valid
			if (index < size()) {
				
				// For each index before the index of the node to be removed
				for (int i = 1; i < index; i ++) {
					prev = curr;			// The previous pointer points to the current node
					curr = curr.getNext();	// Step the current pointer forward
				}
				
				prev.setNext(curr.getNext());	// Set the previous node's pointer to the node after the current pointer
				curr.setNext(null);				// Set the current node's pointer to null
				curr = prev.getNext();			// Set the current pointer to the node after the removed node
			}
		}
	}
	
	/*
	 * removeAll	Clears the linked list
	 */
	public static void removeAll() {
		Node curr = head.getNext();		// Set the current pointer to the node at index 1
		Node prev = head;				// Set the previous pointer to the head node
		head = null;					// Set the head pointer to null
		
		// While the current pointer is not null
		while (curr != null) {
			prev.setNext(null);			// Set the previous node's pointer to null
			prev = curr;				// Set the previous pointer to the current node
			curr = curr.getNext();		// Step the current pointer forward
		}
		
		prev = null;					// Set the previous pointer to null
	}
	
	/**
	 * get				Returns the node at the give index
	 * 
	 * @param index		The index of the node to be retrieved
	 * @return			The retrieved node
	 */
	public static Node get(int index) {
		Node curr = head;			// Set the current pointer to the head node
		
		// For each node before the desired node
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();	// Step the current pointer forward
		}
		
		return curr;				// Return the desired node
	}
	
	/**
	 * printList		Prints the contents of each node in the linked list, assuming the contents are printable
	 */
	public static void printList() {
		
		// If the list is empty
		if (isEmpty()) {
			System.out.println("0 Items in the linked list");			// Print empty message
			
		// If the list is not empty
		} else {
			Node curr = head;		// Set the current pointer to the head node
			System.out.print(size() + " Items in the linked list: ");	// Print the size of the list
			
			// While current is not the last node
			while (curr.getNext() != null) {
				System.out.print(curr.getItem() + ", ");	// Print the current node's object
				curr = curr.getNext();						// Step the current pointer forward
			}
			
			System.out.println(curr.getItem());				// Print the last node's object
		}
	}
	
	/**
	 * Node class, used to create and modify the linked list's nodes
	 *
	 */
	public static class Node {
		private Object item;	// The node's object
		private Node next;		// The pointer to the next node in the list
		
		/**
		 * Constructor with no arguments
		 */
		public Node() {
			this(null);
		}
		
		/**
		 * Constructor with Object argument
		 * 
		 * @param item		The new node's object
		 */
		public Node(Object item) {
			this(item, null);
		}
		
		/**
		 * Constructor with Object and Node arguments
		 * 
		 * @param item		The new node's object
		 * @param next		The next node in the list
		 */
		public Node(Object item, Node next) {
			this.item = item;
			this.next = next;
		}
		
		/**
		 * getItem		Returns the node's object
		 * 
		 * @return		The node's object
		 */
		public Object getItem() {
			return item;
		}
		
		/**
		 * getNext		Returns the next node in the list
		 * 
		 * @return		The next node in the list
		 */
		public Node getNext() {
			return next;
		}
		
		/**
		 * setItem			Changes the node's object
		 * 
		 * @param item		The new object
		 */
		public void setItem(Object item) {
			this.item = item;
		}
		
		/**
		 * setNext			Changes the next node in the list
		 * 
		 * @param next		The new next node
		 */
		public void setNext(Node next) {
			this.next = next;
		}
	}

}
