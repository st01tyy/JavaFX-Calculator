package core;

public class Node<T>
{
	private T element;
	public Node<T> previous;
	public Node<T> next;
	Node (T input)
	{
		element=input;
		previous=next=null;
	}
	public void setElement(T input)
	{
		element=input;
	}
	public T getElement() {
		return element;
	}
	public Node<T> getPrevious() {
		return previous;
	}
	public void setPrevious(Node<T> previous) {
		this.previous = previous;
	}
	public Node<T> getNext() {
		return next;
	}
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
}
