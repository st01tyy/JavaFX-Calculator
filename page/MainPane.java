package page;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainPane extends BorderPane
{
	private VBox showArea;
	private TextField up,down;
	
	private GridPane buttonsArea;
	private ArrayList<Button> numbers=new ArrayList<Button>();
	private ArrayList<Button> operators=new ArrayList<Button>();
	private Button button_point,button_reset,button_backspace,button_run;
	
	private String[] sample={"+","-","¡Á","¡Â","(",")"};
	
	public MainPane()
	{
		super();
		this.setPrefSize(316, 437);
		
		showArea=new VBox();
		showArea.setPrefHeight(100);
		up=new TextField();
		up.setPrefHeight(50);
		up.setEditable(false);
		up.setFont(new Font("Cambria",18));
		up.setAlignment(Pos.CENTER_RIGHT);
		up.setFocusTraversable(false);
		down=new TextField();
		down.setPrefHeight(up.getPrefHeight());
		down.setEditable(false);
		down.setFont(new Font("Cambria",24));
		down.setAlignment(Pos.CENTER_RIGHT);
		down.setFocusTraversable(false);
		showArea.getChildren().addAll(up,down);
		
		buttonsArea=new GridPane();
		buttonsArea.setPrefSize(284, 231);
		
		Integer a,b;
		for(a=0;a<=9;a++)
		{
			Button temp=new Button(a.toString());
			temp.setPrefSize(79, 64);
			temp.setAlignment(Pos.CENTER);
			temp.setFont(new Font("Cambria",24));
			temp.setStyle("-fx-background-color:white");
			temp.setOnMouseClicked(e -> EventHandlers.onNormalButtonsClicked(temp, up));
			numbers.add(temp);
		}
		for(a=0;a<6;a++)
		{
			Button temp=new Button(sample[a]);
			temp.setPrefSize(100, 100);
			temp.setAlignment(Pos.CENTER);
			temp.setFont(new Font("Cambria",36));
			temp.setStyle("-fx-background-color:white");
			temp.setOnMouseClicked(e -> EventHandlers.onNormalButtonsClicked(temp, up));
			operators.add(temp);
		}
		button_point=new Button(".");
		button_point.setPrefSize(100,100);
		button_point.setAlignment(Pos.CENTER);
		button_point.setFont(new Font("Cambria",24));
		button_point.setStyle("-fx-background-color:white");
		button_point.setOnMouseClicked(e -> EventHandlers.onNormalButtonsClicked(button_point, up));
		buttonsArea.add(button_point, 2, 4);
		
		button_reset=new Button("C");
		button_reset.setPrefSize(100,100);
		button_reset.setAlignment(Pos.CENTER);
		button_reset.setFont(new Font("Cambria",24));
		button_reset.setStyle("-fx-background-color:white");
		button_reset.setOnMouseClicked(e -> EventHandlers.onResetClicked(button_reset,up,down));
		buttonsArea.add(button_reset, 0, 0);
		
		button_backspace=new Button("¡û");
		button_backspace.setPrefSize(100,100);
		button_backspace.setAlignment(Pos.CENTER);
		button_backspace.setFont(new Font("Cambria",36));
		button_backspace.setStyle("-fx-background-color:white");
		button_backspace.setOnMouseClicked(e -> EventHandlers.onBackSpaceClicked(button_backspace,up));
		buttonsArea.add(button_backspace, 3, 0);
		
		button_run=new Button("=");
		button_run.setPrefSize(100,100);
		button_run.setAlignment(Pos.CENTER);
		button_run.setFont(new Font("Cambria",36));
		button_run.setOnMouseClicked(e -> EventHandlers.onRunClicked(button_run,up, down));
		button_run.setStyle("-fx-background-color:white");
		buttonsArea.add(button_run, 3, 3);
		
		buttonsArea.add(numbers.get(0), 1, 4);
		buttonsArea.add(operators.get(4), 0, 4);
		buttonsArea.add(operators.get(5), 3, 4);
		int index=1;
		for(a=3;a>=1;a--)
		{
			for(b=0;b<3;b++)
			{
				buttonsArea.add(numbers.get(index), b, a);
				index++;
			}
		}
		
		buttonsArea.add(operators.get(0), 3, 2);
		buttonsArea.add(operators.get(1), 3, 1);
		buttonsArea.add(operators.get(2), 1, 0);
		buttonsArea.add(operators.get(3), 2, 0);
		
		buttonsArea.setStyle("-fx-background-color:white");
		buttonsArea.setGridLinesVisible(true);
		
		this.setTop(showArea);
		this.setCenter(buttonsArea);
	}
	
}
