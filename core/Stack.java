package core;

public class Stack<T>
{
	private Node<T> pointer;
	private int length;
	Stack()
	{
		pointer=null;
		length=0;
	}
	public void push(Node<T> input)
	{
		if(pointer==null)
		{
			pointer=input;
			length++;
		}
		else
		{
			pointer.next=input;
			input.previous=pointer;
			pointer=input;
			length++;
		}
	}
	public Node<T> pop()
	{
		if(pointer.previous!=null)
		{
			pointer=pointer.previous;
			length--;
			return pointer.next;
		}
		else
		{
			Node<T> temp=pointer;
			pointer=null;
			length--;
			return temp;
		}
		
	}
	public int getLength() {
		return length;
	}
	public Node<T> getPointer() {
		return pointer;
	}
	
}
