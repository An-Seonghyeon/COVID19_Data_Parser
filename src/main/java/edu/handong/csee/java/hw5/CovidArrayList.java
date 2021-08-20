package edu.handong.csee.java.hw5;

/**
 * This program is acted as an linked list. It saves/adds list of countries' information.
 * The entire programs are running with this customized linked list. This customized linked list has 
 * size, add, and get methods only which are frequently used in the entire programs.
 * 
 * @author Seonghyeon An
 * @version 1.0
 * @since 2020-06-06
 */
public class CovidArrayList<E> {
	private ListNode head;
	private ListNode current;
	private ListNode previous;
	
	/**
	 * This is the constructor that sets each node in linked list.
	 * The head node points the first node in the linked list.
	 * The current node is where the current program execution points right now.
	 * The previous node is the node that is positioned right before the current node.
	 * It initializes all three nodes as null.  
	 */
	public CovidArrayList() {
		head = null;
		current = null;
		previous = null;
	}
	
	/**
	 * This is the method to calculate the size of the linked list.
	 * It counts the number of nodes in the linked list and return as the size.
	 * 
	 * @return Size of the linked list or the number of nodes in the linked list.
	 */
	public int size() {
		int count = 0;
		ListNode position = head;
		
		while(position != null) {
			count++;
			position = position.link;
		}
		return count;
	}
	
	/**
	 * This is the method to add node in the linked list. 
	 * It adds each node in order which means that last added node will be placed at the tail of linked list.
	 * 
	 * @param newData A new node to add at the linked list.
	 */
	public void add(E newData) {
		ListNode newNode = new ListNode();
		newNode.data = newData;
		
		if(head == null) {
			addNodeAtHead(newData);
			current = head;
		}
		
		else if(current != null) {
			newNode.link = current.link;
			current.link = newNode;
			current = newNode;
		}
		
		else if(head != null) {
			System.out.println("Inserting when iterator is past all nodes or is not initialized.");
			System.exit(0);
		}
	}
	
	/**
	 * This is the method to get the node at the certain index of linked list.
	 * 
	 * @param index An index number to search the node which is placed in that certain index.
	 * @return A node from certain index in the linked list.
	 */
	public E get(int index) {
		ListNode position = head;
		E result = null;
		
		for(int i = 0; i < index; i++) {
			position = position.link;
		}
		
		if(position != null) {
			result = position.data;
		}
		
		else {
			System.out.println("Getting data when current is not at any node.");
			System.exit(0);
		}
		return result;
	}
	
	private void addNodeAtHead(E newData) {
		head = new ListNode(newData, head);
		
		if((current == head.link) && (current != null)) {
			previous = head;
		}
	}
	
	private class ListNode{
		private E data;
		private ListNode link;
		
		/**
		 * This is the constructor that initializes the link and data of node in the linked list.
		 * It initializes both link and data as null.
		 */
		public ListNode() {
			link = null;
			data = null;
		}
		
		/**
		 * This is the constructor that sets the link and data of node in the linked list.
		 * The user can define the value of data and link of the node.
		 * 
		 * @param newData A data value that new node will consist.
		 * @param linkedNode A link of node.
		 */
		public ListNode(E newData, ListNode linkedNode) {
			data = newData;
			link = linkedNode;
		}
	}
}