package application.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;

public class PatternFiller implements Filler{
	
	private Paint pattern;
	private String imageUrl;
	private MyShape shape; 
	public PatternFiller(String string, MyShape myShape) {
		// TODO Auto-generated constructor stub
		imageUrl = string;
		shape = myShape;
		try {
			pattern = new ImagePattern(new Image(new FileInputStream(string)));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void fill() {
		// TODO Auto-generated method stub
		shape.setFill(pattern);
		shape.updateFields();
	}
	
	@Override
	public boolean isColor() {
		// TODO Auto-generated method stub
		return false;
	}

	public String getImageUrl() {
		// TODO Auto-generated method stub
		return imageUrl;
	}

}
