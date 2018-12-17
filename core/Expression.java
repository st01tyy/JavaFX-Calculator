package core;

public class Expression
{
	private String expression;
	private String prefix;
	private Double value;
	private int errorjudge;
	public Expression(String expression)
	{
		this.expression=new String(expression);
		prefix="";
		value=0.0;
		errorjudge=0;
	}
	public void transfer()
	{
		for(int a=0;a<expression.length();a++)
		{
			if(expression.charAt(a)=='-')
			{
				if(a-1>=0)
				{
					if(expression.charAt(a-1)=='+'||expression.charAt(a-1)=='-'||expression.charAt(a-1)=='¡Á'||expression.charAt(a-1)=='¡Â')
					{
						this.errorjudge=1;
						break;
					}
				}
			}
		}
		Node<Integer> status=new Node<Integer>(1);
		Stack<String> stack=new Stack<String>();
		Node<Integer> a=new Node<Integer>(0);
		while(a.getElement()<expression.length())
		{
			Integer index=a.getElement();
			String ch=expression.substring(index, index+1);
			String type=getType(ch,status);
			if(type.equals("number"))
				getNumber(a,status);
			else if(type.equals("symbol"))
			{
				status.setElement(1);;
				if(stack.getPointer()==null)
					stack.push(new Node<String>(new String(ch)));
				else
				{
					while(stack.getLength()>0)
					{
						String stackTop=stack.getPointer().getElement();
						if(stackTop!=null&&compare(stackTop,ch))
						{
							prefix+=stack.pop().getElement();
							prefix+=" ";
						}
						else
						{
							stack.push(new Node<String>(ch));
							break;
						}
					}
					if(stack.getLength()==0)
						stack.push(new Node<String>(ch));
				}
			}
			else
			{
				while(stack.getPointer().getElement().equals("(")==false)
				{
					prefix+=stack.pop().getElement();
					prefix+=" ";
				}
				stack.pop();
			}
			a.setElement(a.getElement()+1);
		}
		while(stack.getLength()>0)
		{
			prefix+=stack.pop().getElement();
			prefix+=" ";
		}
		if(stack.getPointer()!=null)
			errorjudge=1;
	}
	public void getNumber(Node<Integer> a,Node<Integer> status)
	{
		Integer index=a.getElement();
		prefix+=expression.substring(index, index+1);
		if(status.getElement()==1)
			status.setElement(0);;
		if(index+1<expression.length())
		{
			if(getType(expression.substring(index+1, index+2),status).equals("number"))
			{
				a.setElement(a.getElement()+1);
				getNumber(a,status);
			}
			else
				prefix+=" ";
		}
		else
			prefix+=" ";
	}
	public String getType(String sample,Node<Integer> status)
	{
		String result;
		char ch=sample.charAt(0);
		if(ch>='0'&&ch<='9'||ch=='.')
			result="number";
		else if(status.getElement()==1&&ch=='-')
			result="number";
		else if(ch=='+'||ch=='-'||ch=='¡Á'||ch=='¡Â'||ch=='(')
			result="symbol";
		else
			result="bracket";
		return result;
	}
	public boolean compare(String alpha,String beta)
	{
		boolean result;
		int a=StackPriority.getPriority(alpha.charAt(0));
		int b=InfixPriority.getPriority(beta.charAt(0));
		if(a>b)
			result=true;
		else
			result=false;
		return result;
	}
	public void calculate()
	{
		//System.out.println(prefix);
		Stack<Double> stack=new Stack<Double>();
		String[] temp=prefix.split(" ");
		int a;
		Node<Integer> status=new Node<Integer>(1);
		for(a=0;a<temp.length;a++)
		{
			if(temp[a].length()>1)
				status.setElement(1);
			else
				status.setElement(0);
			String type=getType(temp[a],status);
			if(type.equals("number"))
				stack.push(new Node<Double>(new Double(temp[a])));
			else
			{
				Double B=stack.pop().getElement();
				Double A=stack.pop().getElement();
				//System.out.println(A+" "+B+" "+temp[a]);
				if(temp[a].equals("+"))
				{
					A+=B;
					stack.push(new Node<Double>(A));
				}
				else if(temp[a].equals("-"))
				{
					A-=B;
					stack.push(new Node<Double>(A));
				}
				else if(temp[a].equals("¡Á"))
				{
					A*=B;
					stack.push(new Node<Double>(A));
				}
				else
				{
					if(B==0)
						errorjudge=1;
					A/=B;
					stack.push(new Node<Double>(A));
				}
			}
		}
		value+=stack.pop().getElement();
		if(stack.getPointer()!=null)
			errorjudge=1;
	}
	public Double getValue()
	{
		return value;
	}
	public int getErrorjudge()
	{
		return this.errorjudge;
	}
	
}
class InfixPriority
{
	private static String[] symbols={"+-","¡Á¡Â","("};
	public static int getPriority(char ch)
	{
		int a,b,result;
		result=-1;
		for(a=0;a<symbols.length;a++)
		{
			for(b=0;b<symbols[a].length();b++)
			{
				if(symbols[a].charAt(b)==ch)
				{
					result=a;
					a=symbols.length;
					break;
				}
			}
		}
		return result;
	}
	
}
class StackPriority
{
	private static String[] symbols={"(","+-","¡Á¡Â"};
	public static int getPriority(char ch)
	{
		int a,b,result;
		result=-1;
		for(a=0;a<symbols.length;a++)
		{
			for(b=0;b<symbols[a].length();b++)
			{
				if(symbols[a].charAt(b)==ch)
				{
					result=a;
					a=symbols.length;
					break;
				}
			}
		}
		return result;
	}
}
