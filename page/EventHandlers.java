package page;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import core.Expression;

public class EventHandlers 
{
	private static int a=0;
	private static Timeline timeline;
	public static void onNormalButtonsClicked(Button button,TextField textfield)
	{
		timeline=new Timeline(new KeyFrame(Duration.millis(50),e -> onClickedAnimation(button)));
		timeline.setCycleCount(2);
		timeline.play();
		textfield.setText(textfield.getText()+button.getText());
	}
	public static void onResetClicked(Button button,TextField up,TextField down)
	{
		timeline=new Timeline(new KeyFrame(Duration.millis(50),e -> onClickedAnimation(button)));
		timeline.setCycleCount(2);
		timeline.play();
		up.setText(new String());
		down.setText(new String());
	}
	public static void onBackSpaceClicked(Button button,TextField textfield)
	{
		timeline=new Timeline(new KeyFrame(Duration.millis(50),e -> onClickedAnimation(button)));
		timeline.setCycleCount(2);
		timeline.play();
		StringBuffer str=new StringBuffer(textfield.getText());
		if(str.length()>0)
			str.deleteCharAt(str.length()-1);
		textfield.setText(str.toString());
	}
	public static void onRunClicked(Button button,TextField up,TextField down)
	{
		timeline=new Timeline(new KeyFrame(Duration.millis(50),e -> onClickedAnimation(button)));
		timeline.setCycleCount(2);
		timeline.play();
		String str;
		if(judge(up.getText()))
		{
			Expression expression=new Expression(up.getText());
			try
			{
				expression.transfer();
				expression.calculate();
				if(expression.getErrorjudge()==0)
					str=expression.getValue().toString();
				else
					str="error";
			}
			catch(NullPointerException e)
			{
				str="error";
			}
			catch(ArithmeticException e)
			{
				str="error";
			}
		}
		else
			str="error";
		down.setText(str);
	}
	public static boolean judge(String str)
	{
		boolean result;
		int i=0;
		char[] input=str.toCharArray();
		for(int a=0;a<input.length;a++)
		{
			if(input[a]=='(')
				i++;
			else if(input[a]==')')
				i--;
			if(i<0)
				break;
		}
		if(i!=0)
			result=false;
		else
			result=true;
		return result;
	}
	public static void onClickedAnimation(Button button)
	{
		if(a==0)
		{
			button.setStyle("-fx-background-color:pink");
			a=1;
		}
		else
		{
			button.setStyle("-fx-background-color:white");
			a=0;
		}
	}

}
